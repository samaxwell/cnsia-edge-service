package com.polarbookshop.edgeservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class WebEndpoints {
    /*
     * Creating REST controller endpoints. Using functional
     * styles for the heck of it.
     */

    @Value("${message}")
    private String message;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()

                // Fallback route for catalog service GET
                .GET("/catalog-fallback", request -> ServerResponse.ok().body(Mono.just(""), String.class))

                // Fallback route for catalog service POST
                .POST("/catalog-fallback", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build())

                .GET("/temp", request -> ServerResponse.ok().body(Mono.just(message), String.class))
                .build();
    }

}
