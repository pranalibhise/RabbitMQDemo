package com.rabbitmq.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.demo.model.OrderStatus;

@Component
public class User {
	
	@RabbitListener(queues = "${message.queue}")
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Order staus object consumed " + orderStatus.toString() );
		
	}
}
