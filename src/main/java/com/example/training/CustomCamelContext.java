package com.example.training;

import org.apache.camel.CamelContext;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomCamelContext {

	@Bean
	public DataFormat bindy() {
		return new BindyCsvDataFormat(com.example.training.model.Staff.class);
	}

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
