package com.example.training;

import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class StaffProccessor implements Processor{
	@Override
	public void process(Exchange exchange) throws Exception {
		Calendar calendar = Calendar.getInstance();
		exchange.getIn().setHeader("timestamp", calendar.getTimeInMillis());
	}
}
