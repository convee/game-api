package com.company.project.service;
import com.company.project.model.Orders;
import com.company.project.core.Service;


/**
 * Created by Convee on 2018/03/08.
 */
public interface OrdersService extends Service<Orders> {
    String addOrder(Integer userId, Integer goodsId);
    Integer updateOrder(Orders orders);

}
