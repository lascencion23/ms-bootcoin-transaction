package com.everis.bootcamp.msbootcoin.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Data
public class BootCoinAccount {

    @Id
    private String id;

    private TypeDocument typeDocument;

    private String nroDocument;

    private int phoneNumber;

    private String email;

    private Double amountCoin;

    private LocalDateTime createAt;

    public enum TypeDocument {
        DNI,
        PASAPORTE,
        CEX
    }

}
