package com.company.project.web;

import com.alibaba.fastjson.JSONObject;
import com.company.project.authorization.manager.TokenManager;
import com.company.project.cache.PlayerCache;
import com.company.project.core.ResultCode;
import com.company.project.core.Ret;
import com.company.project.core.ServiceException;
import com.company.project.dto.PlayerDTO;
import com.company.project.model.Orders;
import com.company.project.service.OrdersService;
import com.company.project.service.TableService;
import com.company.project.util.BeanMapper;
import com.company.project.util.DateTools;
import com.company.project.util.HttpAPIService;
import com.company.project.util.TableTools;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

/**
 21000	App Store不能读取你提供的JSON对象
 21002	receipt-data域的数据有问题
 21003	receipt无法通过验证
 21004	提供的shared secret不匹配你账号中的shared secret
 21005	receipt服务器当前不可用
 21006	receipt合法，但是订阅已过期。服务器接收到这个状态码时，receipt数据仍然会解码并一起发送
 21007	receipt是Sandbox receipt，但却发送至生产系统的验证服务
 21008	receipt是生产receipt，但却发送至Sandbox环境的验证服务
 */
@RestController
@RequestMapping("/pay")
@Configuration
public class PayController {
    @Resource
    private HttpAPIService httpAPIService;
    @Resource
    private TokenManager tokenManager;
    @Resource
    private OrdersService ordersService;
    @Resource
    private TableService tableService;
    @Resource
    private PlayerCache playerCache;

    private static final Logger LOGGER = LoggerFactory.getLogger(PayController.class);
    @Value("${pay.apple.receipt}")
    private String receiptUrl;

    @PostMapping("/verifyReceipt")
    public Object verifyReceipt(@RequestParam String receipt, @RequestParam String token, @RequestParam Integer userId, @RequestParam String orderNumber) {
        Ret result = new Ret();
        //登录态验证
        tokenManager.checkLogin(token, userId);
        //订单号验证
        Orders orders = ordersService.findBy("orderNumber", orderNumber);
        if (orders == null) {
            LOGGER.info("verifyReceipt:orders null, orderNumber" + orderNumber);
            return result.setOption("code", ResultCode.ORDER_NOT_EXISTS.code).getResult();
        }
        //用户与订单是否匹配
        if (!userId.equals(orders.getUserId())) {
            LOGGER.info("verifyReceipt:orders not match userId, orderNumber" + orderNumber + ",userId:" + userId);
            return result.setOption("code", ResultCode.ORDER_NOT_EXISTS.code).getResult();
        }
        //苹果二次验证POST
        JSONObject data = new JSONObject();
        data.put("receipt-data", receipt);
        JSONObject ret = httpAPIService.doPost(this.receiptUrl, data);
        if (ret == null) {
            LOGGER.info("verifyReceipt post error");
            return result.setOption("code", ResultCode.ORDER_PAY_FAILED.code).getResult();
        }
        if (ret.getInteger("status") != 0) {
            LOGGER.info("verifyReceipt:" + ret.toJSONString());
            return result.setOption("code", ResultCode.ORDER_PAY_FAILED.code).getResult();
        }
        //仅第一次请求时更新订单信息和玩家数据
        if (orders.getPayStatus() <= 0) {
            Orders ordersUpdatePrams = new Orders();
            ordersUpdatePrams.setOrderId(orders.getOrderId());
            ordersUpdatePrams.setPayStatus(1);
            ordersUpdatePrams.setUpdateTime((int)DateTools.timeStamp());
            Integer updateRet = ordersService.updateOrder(ordersUpdatePrams);
            if (updateRet <= 0) {
                return result.setOption("code", ResultCode.ORDER_PAY_FAILED.code).getResult();
            }
            tableService.initTable();
            TableTools.iap_store_config tableConfig = TableTools.Get_iap_store_config_byid(orders.getGoodsId());
            if (tableConfig == null) {
                throw new ServiceException("商品不存在");
            }
            //更新玩家vip等级
            if (tableConfig.reward_vip_level > 0) {
                playerCache.incrPlayerVipLevel(userId, tableConfig.reward_vip_level);
            }
            //更新玩家宝石
            if (tableConfig.reward_gem_num > 0) {
                playerCache.incrPlayerGem(userId, tableConfig.reward_gem_num);
            }
            //更新玩家体力
            if (tableConfig.reward_energy_num > 0) {
                playerCache.incrPayerEnergy(userId, tableConfig.reward_energy_num);
            }
            //更新玩家金币
            if (tableConfig.reward_coin_num > 0) {
                playerCache.incrPlayerCoin(userId, tableConfig.reward_coin_num);
            }
        }
        PlayerDTO player = BeanMapper.map(playerCache.getPlayer(userId), PlayerDTO.class);
        result.setOption("playerVipLevel", player.getPlayerVipLevel());
        result.setOption("playerGem", player.getPlayerGem());
        result.setOption("playerEnergy", player.getPlayerEnergy());
        result.setOption("playerCoin", player.getPlayerCoin());
        return result.setOption("productId", orders.getGoodsId()).getResult();
    }

}
