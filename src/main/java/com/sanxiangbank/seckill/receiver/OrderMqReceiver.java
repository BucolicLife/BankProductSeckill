package com.sanxiangbank.seckill.receiver;

import com.alibaba.fastjson.JSONObject;
import com.sanxiangbank.seckill.service.OrderService;
import com.sanxiangbank.seckill.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "orderQueue")
public class OrderMqReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderMqReceiver.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void process(String message) {
        LOGGER.info("OrderMqReceiver收到消息开始用户下单流程: " + message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        try {
            orderService.createOrderByMq(jsonObject.getInteger("sid"),jsonObject.getInteger("userId"));
        } catch (Exception e) {
            LOGGER.error("消息处理异常：", e);
        }
    }
}
