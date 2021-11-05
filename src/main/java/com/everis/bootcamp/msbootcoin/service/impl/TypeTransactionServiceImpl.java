package com.everis.bootcamp.msbootcoin.service.impl;

import com.everis.bootcamp.msbootcoin.domain.dto.TypeTransactionDto;
import com.everis.bootcamp.msbootcoin.domain.entity.TypeTransaction;
import com.everis.bootcamp.msbootcoin.domain.repository.TypeTransactionRepository;
import com.everis.bootcamp.msbootcoin.service.srv.TypeTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TypeTransactionServiceImpl implements TypeTransactionService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TypeTransactionRepository repository;

    @Override
    public Flux<TypeTransactionDto> findAll() {
        return repository.findAll().flatMap(this::getTypeTransactionDto);
    }

    @Override
    public Mono<TypeTransactionDto> findById(String id) {
        return repository.findById(id).flatMap(this::getTypeTransactionDto);
    }

    @Override
    public Mono<TypeTransactionDto> save(TypeTransaction typeTransaction) {
        return repository.save(typeTransaction).flatMap(this::getTypeTransactionDto);
    }


    @Override
    public Mono<TypeTransaction> getTypeTransaction(TypeTransactionDto typeTransactionDto) {
        return Mono.just(objectMapper.convertValue(typeTransactionDto, TypeTransaction.class));
    }

    @Override
    public Mono<TypeTransactionDto> getTypeTransactionDto(TypeTransaction typeTransaction) {
        return Mono.just(objectMapper.convertValue(typeTransaction, TypeTransactionDto.class));
    }


}
