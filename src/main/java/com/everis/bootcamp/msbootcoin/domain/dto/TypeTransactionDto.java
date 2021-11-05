package com.everis.bootcamp.msbootcoin.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TypeTransactionDto {

    @Id
    private String id;

    @NotEmpty
    private String type;

    @NotNull
    private Double taza;

}
