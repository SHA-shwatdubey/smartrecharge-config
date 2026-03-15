package com.smartrecharge.processingservice.messaging;

import com.smartrecharge.processingservice.dto.RechargeEventDto;
import com.smartrecharge.processingservice.service.ProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RechargeEventConsumer {

    private final ProcessingService processingService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consumeRechargeEvent(RechargeEventDto eventDto) {
        log.info("Received recharge event for rechargeId={}", eventDto.getRechargeId());
        processingService.processRechargeEvent(eventDto);
    }
}
