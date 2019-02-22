package com.company.project.web;
import com.company.project.authorization.manager.TokenManager;
import com.company.project.core.ResultCode;
import com.company.project.core.Ret;
import com.company.project.service.OrdersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by Convee on 2018/03/07.
*/
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrdersService ordersService;
    @Resource
    private TokenManager tokenManager;

    @PostMapping("/add")
    public Object add(@RequestParam String token, @RequestParam Integer userId, @RequestParam Integer goodsId) {
        tokenManager.checkLogin(token, userId);
        String orderNumber = ordersService.addOrder(userId, goodsId);
        if (orderNumber == null) {
            return (new Ret())
                    .setOption("code", ResultCode.ORDER_CREATE_FAILED.code)
                    .getResult();
        }
        return (new Ret())
                .setOption("orderNumber", orderNumber)
                .getResult();
    }

}
