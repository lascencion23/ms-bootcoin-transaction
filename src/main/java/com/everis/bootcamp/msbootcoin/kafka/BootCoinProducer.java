package com.everis.bootcamp.msbootcoin.kafka;

import com.everis.bootcamp.msbootcoin.domain.dto.BootCoinTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BootCoinProducer {

    @Value("${custom.kafka.topic-name-bootcoin-account}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, BootCoinTransactionDto> bootCoinKafkaTemplate;

    public void producer(BootCoinTransactionDto bootCoinAutorizacionMessageDTO) {
        bootCoinKafkaTemplate.send(topicName, bootCoinAutorizacionMessageDTO);
    }
}
