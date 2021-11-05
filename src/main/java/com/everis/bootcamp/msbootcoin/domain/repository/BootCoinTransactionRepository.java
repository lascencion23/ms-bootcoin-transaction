package com.everis.bootcamp.msbootcoin.domain.repository;

import com.everis.bootcamp.msbootcoin.domain.entity.BootCoinTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BootCoinTransactionRepository extends ReactiveMongoRepository<BootCoinTransaction, String> {
}
