package com.everis.bootcamp.msbootcoin.service.srv;

import com.everis.bootcamp.msbootcoin.domain.dto.BootCoinAccountDto;
import com.everis.bootcamp.msbootcoin.domain.dto.BootCoinTransactionDto;
import com.everis.bootcamp.msbootcoin.domain.entity.BootCoinAccount;
import com.everis.bootcamp.msbootcoin.domain.entity.BootCoinTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootCoinTransactionService {

    Flux<BootCoinTransactionDto> findAll();

    Mono<BootCoinTransactionDto> findById(String id);

    Mono<BootCoinTransactionDto> save(BootCoinTransaction bootCoinTransaction);

    Mono<BootCoinTransactionDto> getBootCoinTransactionDto(BootCoinTransaction bootCoinTransaction);

    Mono<BootCoinTransaction> getBootCoinTransaction(BootCoinTransactionDto bootCoinTransactionDto);

    BootCoinTransactionDto getBootTransactionDto(BootCoinTransaction bootCoinTransaction);

    BootCoinTransaction getBootTransaction(BootCoinTransactionDto bootCoinTransactionDto);

}
