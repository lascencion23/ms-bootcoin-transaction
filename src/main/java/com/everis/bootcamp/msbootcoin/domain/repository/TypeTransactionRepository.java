package com.everis.bootcamp.msbootcoin.domain.repository;

import com.everis.bootcamp.msbootcoin.domain.entity.TypeTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TypeTransactionRepository extends ReactiveMongoRepository<TypeTransaction, String> {
}
