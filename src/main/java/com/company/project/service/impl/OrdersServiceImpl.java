package com.company.project.service.impl;

import com.company.project.core.ServiceException;
import com.company.project.dao.OrdersMapper;
import com.company.project.model.Orders;
import com.company.project.service.OrdersService;
import com.company.project.core.AbstractService;
import com.company.project.service.TableService;
import com.company.project.util.DateTools;
import com.company.project.util.OrderNumberGenerator;
import com.company.project.util.TableTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Convee on 2018/03/08.
 */
@Service
@Transactional
public class OrdersServiceImpl extends AbstractService<Orders> implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private TableService tableService;
    @Override
    public String addOrder(Integer userId, Integer goodsId) {
        //默认订单类型为宝石订单
        Integer orderType = 1;
        tableService.initTable();
        TableTools.iap_store_config tableConfig = TableTools.Get_iap_store_config_byid(goodsId);
        if (tableConfig == null) {
            throw new ServiceException("商品不存在");
        }
        Float price = tableConfig.price;

        Integer createTime = (int) DateTools.timeStamp();
        Integer updateTime = (int) DateTools.timeStamp();
        String orderNumber = OrderNumberGenerator.gen(orderType, userId);
        Orders order = new Orders();
        order.setCreateTime(createTime);
        order.setUpdateTime(updateTime);
        order.setOrderAmount(price);
        order.setOrderNumber(orderNumber);
        order.setOrderType(orderType);
        order.setUserId(userId);
        order.setGoodsId(goodsId);
        if (!"-1".equals(tableConfig.name)) {
            order.setBrief(tableConfig.name);
        }
        Integer ret = ordersMapper.insertSelective(order);
        if (ret > 0) {
            return orderNumber;
        }
        return null;
    }

    @Override
    public Integer updateOrder(Orders orders) {
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

}
