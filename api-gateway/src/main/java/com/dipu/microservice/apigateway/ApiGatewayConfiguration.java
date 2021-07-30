package com.dipu.microservice.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouuter(RouteLocatorBuilder routeLocatorBuilder){
        Function<PredicateSpec, Buildable<Route>> currencyRouterFunction=p->p.path("/currency/**")
                .filters(f->f.addRequestHeader("SOURCE","API-GATEWAY"))
                .uri("lb://currency");
        Function<PredicateSpec, Buildable<Route>> exchangeRouterFunction=p->p.path("/exchange/**")
                .filters(f->f.addRequestHeader("SOURCE","API-GATEWAY"))
                .uri("lb://exchange");
        Function<PredicateSpec, Buildable<Route>> dummyRouterFunction=p->p.path("/dummy-exchange/**")
                .filters(f->f.rewritePath("/dummy-exchange/(?<segment>.*)","/currency/${segment}"))
                .uri("lb://exchange");

        return routeLocatorBuilder.routes()
                .route(currencyRouterFunction)
                .route(exchangeRouterFunction)
                .route(dummyRouterFunction)
                .build();
    }
}
