package com.everis.bootcamp.msbootcoin.domain.entity;

import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Document("BootCoinTransaction")
public class BootCoinTransaction {

    @Id
    private String id;

    @NotNull
    private String solicitanteId;

    @NotNull
    private TypeTransaction typeTransaction;

    @NotNull
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
