package com.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {
	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	    return builder.routes()
	    		.route(p -> p.path("/swipe/**").uri("lb://SWIPE-CAPTURE-SERVICE"))
	    		.route(p -> p.path("/publish/**").uri("lb://SWIPE-CAPTURE-SERVICE"))
	    		.route(p -> p.path("/attendance/**").uri("lb://ATTENDANCE-MONITOR"))
	    		.build();
	}

}
