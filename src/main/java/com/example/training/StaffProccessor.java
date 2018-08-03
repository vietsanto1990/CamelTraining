package com.example.training;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class StaffProccessor implements Processor{
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("We just downloaded: " + exchange.getIn().getHeader("CamelFileName"));
	}
}
