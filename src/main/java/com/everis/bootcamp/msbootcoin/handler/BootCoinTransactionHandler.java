package com.everis.bootcamp.msbootcoin.handler;

import com.everis.bootcamp.msbootcoin.domain.dto.BootCoinTransactionDto;
import com.everis.bootcamp.msbootcoin.domain.dto.TypeTransactionDto;
import com.everis.bootcamp.msbootcoin.domain.entity.BootCoinTransaction;
import com.everis.bootcamp.msbootcoin.domain.entity.TypeTransaction;
import com.everis.bootcamp.msbootcoin.kafka.BootCoinProducer;
import com.everis.bootcamp.msbootcoin.service.srv.BootCoinTransactionService;
import com.everis.bootcamp.msbootcoin.service.srv.TypeTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@Slf4j
public class BootCoinTransactionHandler {

    @Autowired
    private BootCoinTransactionService service;

    @Autowired
    private TypeTransactionService serviceTypeTransaction;

    @Autowired
    private BootCoinProducer producer;

    @Autowired
    private Validator validator;

//    TRANSACTION

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), BootCoinTransactionDto.class);
    }

    public Mono<ServerResponse> findId(ServerRequest request) {
        String id = request.pathVariable("id");

        return service.findById(id)
                .flatMap(dto -> ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
                .switchIfEmpty(
                        ErrorResponse.buildBadResponse("El bootCoin transaction con el id: ".concat(id).concat(" no se encontró."), HttpStatus.NOT_FOUND))
                .onErrorResume(throwable ->
                        ErrorResponse.buildBadResponse(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public Mono<ServerResponse> create(ServerRequest request) {

        Mono<BootCoinTransaction> bootCoin = request.bodyToMono(BootCoinTransaction.class);

        return bootCoin.flatMap(b -> {
            Errors errors = new BeanPropertyBindingResult(b, BootCoinTransaction.class.getName());
            validator.validate(b, errors);

            if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(fromValue(list)));
            } else {
                if (b.getCreateAt() == null) {
                    b.setCreateAt(LocalDateTime.now());
                }

                serviceTypeTransaction.findById(b.getTypeTransaction().getId())
                        .flatMap(c -> serviceTypeTransaction.getTypeTransaction(c)
                                .flatMap(d -> {
                                    b.setTypeTransaction(d);
                                    b.setStatus(BootCoinTransaction.Status.PENDING);
                                    b.setAmountCoin( b.getAmountSoles() / b.getTypeTransaction().getTaza());
                                    service.save(b).subscribe();
                                    return serviceTypeTransaction.getTypeTransactionDto(d);
                                })).subscribe();


                return  ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .build();
            }

        });
    }

    public Mono<ServerResponse> acceptRequest(ServerRequest request) {

        String id = request.pathVariable("id");
        String idv = request.pathVariable("seller");

        service.findById(id).flatMap(c -> {
            c.setVendedorId(idv);
            c.setStatus(BootCoinTransactionDto.Status.ACCEPTED);
            producer.producer(c);
            return service.save(service.getBootTransaction(c));
        }).subscribe();

        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }


//    TYPE TRANSACTION

    public Mono<ServerResponse> findAllType(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceTypeTransaction.findAll(), TypeTransactionDto.class);

    }

    public Mono<ServerResponse> createType(ServerRequest request) {
        return request.bodyToMono(TypeTransaction.class)
                .flatMap(c -> serviceTypeTransaction.save(c))
                .flatMap(t -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(t)));
    }

    public Mono<ServerResponse> findIdType(ServerRequest request) {
        String id = request.pathVariable("id");

        return serviceTypeTransaction.findById(id)
                .flatMap(dto -> ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
                .switchIfEmpty(
                        ErrorResponse.buildBadResponse("El type transaction con el id: ".concat(id).concat(" no se encontró."), HttpStatus.NOT_FOUND))
                .onErrorResume(throwable ->
                        ErrorResponse.buildBadResponse(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
