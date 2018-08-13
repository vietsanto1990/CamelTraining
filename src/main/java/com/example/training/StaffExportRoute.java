package com.example.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.training.entity.Staff;

@Component
public class StaffExportRoute extends RouteBuilder {

	@Autowired
	@Qualifier("jsonDataFormat")
	private DataFormat jsonFormat;

	@Autowired
	private StaffProccessor processor;

	@Override
	public void configure() throws Exception {
		// Case 1: fetch all data and parse json. Export json file then
//		from("quartz2://staffExportJob?cron=*/15+*+*+*+*+?").routeId("fetchStaffQuartz")
//		.pollEnrich("jpa://com.example.training.entity.Staff?maxMessagesPerPoll=10&delay=3000", new AggregationStrategy() {
//			@Override
//			public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
//				return newExchange;
//			}
//		}).to("direct:exportStaff");

		from("quartz2://staffExportJob?cron=*/30+*+*+*+*+?").routeId("fetchStaffQuartz")
		    .to("bean:staffService?method=fetchStaff")
		    .split(body())
			    .aggregate(header("triggerName"), new AggregationStrategy() {
				    @Override
				    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
					    if (oldExchange == null) {
						    return newExchange;
					    }
					    Staff newStaff = (Staff) newExchange.getIn().getBody();
					    Object object = oldExchange.getIn().getBody();
					    if (object instanceof List) {
					    	List staffs = new ArrayList<>((List)object);
					    	staffs.add(newStaff);
					    	newExchange.getIn().setBody(staffs);
					    } else {
					    	newExchange.getIn().setBody(Arrays.asList(object, newStaff));
					    }
					    return newExchange;
				    }
			    })
			    .completionSize(50)
			    .completionPredicate(exchangeProperty(Exchange.SPLIT_COMPLETE).isEqualTo(true))
			    .completionTimeout(2000)
			    .marshal(jsonFormat)
			    .to("${file.local}/Jsontmp?fileName=Staff.json&fileExist=Append&doneFileName=done.txt")
			    .end()
		    .end()
		    .to("direct:exportStaff");

		from("direct:exportStaff").routeId("exportJsonLocalRoute")
			.routeId("exportJsonToFtp")
			.pollEnrich("${file.local}/Jsontmp?doneFileName=done.txt&delete=true")
			.process(processor)
			.to("${ftp.uri}&fileName=Staff-${header.timestamp}.json");

	}
}
