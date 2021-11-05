package com.everis.bootcamp.msbootcoin.domain.dto;

import com.everis.bootcamp.msbootcoin.domain.entity.BootCoinAccount;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class BootCoinTransactionDto {

    @Id
    private String id;

    private String solicitanteId;

    private TypeTransactionDto typeTransaction;

    private Double amountSoles;

    private Double amountCoin;

    private String vendedorId;

    private LocalDateTime createAt;

    private Status status;

    public enum Status {
        PENDING,
        ACCEPTED,
        SUCCESS,
        REJECT
    }

}
