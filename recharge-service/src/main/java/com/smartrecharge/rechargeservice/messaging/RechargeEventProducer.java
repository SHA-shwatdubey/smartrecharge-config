package com.smartrecharge.rechargeservice.messaging;

import com.smartrecharge.rechargeservice.dto.RechargeEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RechargeEventProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public void publishRechargeEvent(RechargeEventDto eventDto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, eventDto);
        log.info("Published recharge event for rechargeId={}", eventDto.getRechargeId());
    }
}

