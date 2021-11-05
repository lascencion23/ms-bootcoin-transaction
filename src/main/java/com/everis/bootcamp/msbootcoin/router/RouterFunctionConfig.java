package com.everis.bootcamp.msbootcoin.router;

import com.everis.bootcamp.msbootcoin.handler.BootCoinTransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> routes (BootCoinTransactionHandler handler) {
        return route(GET("/bootCoinTransaction"), handler::findAll)
                .andRoute(GET("/bootCoinTransaction/{id}"), handler::findId)
                .andRoute(POST("/bootCoinTransaction"),handler::create)
                .andRoute(PUT("/bootCoinTransaction/{id}/{seller}"),handler::acceptRequest)

                .andRoute(GET("/typeTransaction"), handler::findAllType)
                .andRoute(GET("/typeTransaction/{id}"), handler::findIdType)
                .andRoute(POST("/typeTransaction"), handler::createType);
    }
}
