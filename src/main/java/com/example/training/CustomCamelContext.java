package com.example.training;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomCamelContext {

	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {

			@Override
			public void beforeApplicationStart(CamelContext camelContext) {
				camelContext.setTracing(true);
			}

			@Override
			public void afterApplicationStart(CamelContext camelContext) {
			}
		
		};
	}
}
