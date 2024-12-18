package org.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.api.dto.PhoneCodeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;
import java.util.Objects;

@Configuration
public class KafkaConfig {

    @Autowired
    Environment env;

    @Bean
    ProducerFactory<String, Object> createProducerFactory() {
        Map<String, Object> config = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Objects.requireNonNull(env.getProperty("spring.kafka.producer.bootstrap-servers")),
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
        );
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public ReplyingKafkaTemplate<String, Object, Object> createReplyKafkaTemplate(
            ProducerFactory<String, Object> producerFactory, ConcurrentMessageListenerContainer<String, Object> listenerContainer
    ) {
        return new ReplyingKafkaTemplate<>(producerFactory, listenerContainer);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, Object> createListenerContainer(
            ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory) {
        ConcurrentMessageListenerContainer<String, Object> container = containerFactory.createContainer("phone_code_get_reply_topic");
        container.setAutoStartup(false);
        return container;
    }

    @Bean
    ConsumerFactory<String, Object> createConsumerFactory() {
        Map<String, Object> config = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Objects.requireNonNull(env.getProperty("spring.kafka.consumer.bootstrap-servers")),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                JsonDeserializer.TRUSTED_PACKAGES, Objects.requireNonNull(env.getProperty("spring.kafka.consumer.properties.spring.json.trusted.packages")));
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> createKafkaListenerContainer() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createConsumerFactory());
        return factory;
    }

//    @Bean
//    NewTopic createReplyTopic() {
//        return TopicBuilder.name("phone_code_get_reply_topic")
//                .partitions(3)
//                .replicas(3)
//                .configs(Map.of("min.insync.replicas", "2"))
//                .build();
//    }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("phone_code_get_event_topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}
