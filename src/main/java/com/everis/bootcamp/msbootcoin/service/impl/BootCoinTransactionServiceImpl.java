package com.everis.bootcamp.msbootcoin.service.impl;

import com.everis.bootcamp.msbootcoin.domain.dto.BootCoinTransactionDto;
import com.everis.bootcamp.msbootcoin.domain.entity.BootCoinTransaction;
import com.everis.bootcamp.msbootcoin.domain.repository.BootCoinTransactionRepository;
import com.everis.bootcamp.msbootcoin.service.srv.BootCoinTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinTransactionServiceImpl implements BootCoinTransactionService {

    @Autowired
    private BootCoinTransactionRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Flux<BootCoinTransactionDto> findAll() {
        return repository.findAll().flatMap(this::getBootCoinTransactionDto);
    }

    @Override
    public Mono<BootCoinTransactionDto> findById(String id) {
        return repository.findById(id).flatMap(this::getBootCoinTransactionDto);
    }

    @Override
    public Mono<BootCoinTransactionDto> save(BootCoinTransaction bootCoinAccount) {
        return repository.save(bootCoinAccount).flatMap(this::getBootCoinTransactionDto);
    }

    @Override
    public BootCoinTransactionDto getBootTransactionDto(BootCoinTransaction bootCoinTransaction){
        return objectMapper.convertValue(bootCoinTransaction, BootCoinTransactionDto.class);
    }

    @Override
    public BootCoinTransaction getBootTransaction(BootCoinTransactionDto bootCoinTransactionDto) {
        return objectMapper.convertValue(bootCoinTransactionDto, BootCoinTransaction.class);
    };


    @Override
    public Mono<BootCoinTransactionDto> getBootCoinTransactionDto(BootCoinTransaction bootCoinTransaction) {
        return Mono.just(objectMapper.convertValue(bootCoinTransaction, BootCoinTransactionDto.class));
    }

    @Override
    public Mono<BootCoinTransaction> getBootCoinTransaction(BootCoinTransactionDto bootCoinTransactionDto) {
        return Mono.just(objectMapper.convertValue(bootCoinTransactionDto, BootCoinTransaction.class));
    }


}
