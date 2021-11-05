package com.everis.bootcamp.msbootcoin.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Document("TypeTransaction")
public class TypeTransaction {

    @Id
    private String id;

    @NotEmpty
    private String type;

    @NotNull
    private Double taza;

}
