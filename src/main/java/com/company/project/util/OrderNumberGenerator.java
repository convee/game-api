package com.company.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumberGenerator {
    /**
     * 时间+订单类型+用户ID+随机数
     * @param orderType
     * @param userId
     * @return
     */
    public static String gen(Integer orderType, Integer userId) {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Integer random = new Random().nextInt(1000);
        return date + orderType + userId + random;
    }
}
