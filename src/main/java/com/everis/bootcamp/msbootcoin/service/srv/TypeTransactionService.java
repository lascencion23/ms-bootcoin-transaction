package com.everis.bootcamp.msbootcoin.service.srv;

import com.everis.bootcamp.msbootcoin.domain.dto.TypeTransactionDto;
import com.everis.bootcamp.msbootcoin.domain.entity.TypeTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypeTransactionService {

    Flux<TypeTransactionDto> findAll();

    Mono<TypeTransactionDto> findById(String id);

    Mono<TypeTransactionDto> save(TypeTransaction typeTransaction);

    Mono<TypeTransaction> getTypeTransaction(TypeTransactionDto typeTransactionDto);

    Mono<TypeTransactionDto> getTypeTransactionDto(TypeTransaction typeTransaction);

}
