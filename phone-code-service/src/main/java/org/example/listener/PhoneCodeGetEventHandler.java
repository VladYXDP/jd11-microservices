package org.example.listener;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.PhoneCodeResponseDto;
import org.example.service.PhoneCodeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PhoneCodeGetEventHandler {

    private final PhoneCodeService phoneCodeService;
    private final KafkaTemplate<String, Set<PhoneCodeResponseDto>> kafkaTemplate;

    @KafkaListener(topics = "phone_code_get_event_topic", containerFactory = "createKafkaListenerContainer")
    public Set<PhoneCodeResponseDto> handleGetPhoneCodeEvent(Message<Set<Integer>> message) {
        Set<Integer> phoneCodeIds = message.getPayload();
        String replyTopic = new String(Objects.requireNonNull(message.getHeaders().get(KafkaHeaders.REPLY_TOPIC, byte[].class)));
        if (!phoneCodeIds.isEmpty()) {
            Set<PhoneCodeResponseDto> result = new HashSet<>();
            phoneCodeIds.forEach(it -> result.add(phoneCodeService.getPhoneCode(it)));
            kafkaTemplate.send(replyTopic, result);
        }
        return new HashSet<>();
    }
}
