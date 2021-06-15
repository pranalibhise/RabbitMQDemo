package com.rabbitmq.demo.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.demo.model.Order;
import com.rabbitmq.demo.model.OrderStatus;

@RestController
public class OrderProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${message.routingkey}")
	private String routingKey;
	
	@Value("${message.exchange}")
	private String exchange;

	@PostMapping(value = "/order/{restaurantName}")
	public String createOrder(@RequestBody Order order, @PathVariable String restaurantName) {

		order.setOrderId(UUID.randomUUID().toString());

		OrderStatus orderStatus = new OrderStatus(order, "INPROCESS", "Order placed successfully in " + restaurantName);
		rabbitTemplate.convertAndSend(exchange, routingKey, orderStatus);

		return "Success";

	}

}
