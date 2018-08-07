package com.example.training;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StaffExportRoute extends RouteBuilder {

	@Autowired
	@Qualifier("jsonDataFormat")
	private DataFormat jsonFormat;

	@Autowired
	private StaffProccessor processor;

	@Override
	public void configure() throws Exception {
//		from("quartz2://staffExportJob?cron=*/15+*+*+*+*+?").routeId("fetchStaffQuartz")
//		    .to("jpa://com.example.training.model.Staff?consumer.namedQuery=fetchAllStaff").to("direct:exportStaff");
		from("quartz2://staffExportJob?cron=*/15+*+*+*+*+?").routeId("fetchStaffQuartz")
	    .to("bean:staffService?method=fetchAll").to("direct:exportStaff");

		from("direct:exportStaff").routeId("exportStaffRoute")
			.routeId("exportToLocalJson")
			.marshal(jsonFormat)
			.to("file:D:/receives/Json?fileName=Staff.json&doneFileName=done.txt");
		
		from("file:D:/receives/Json?fileName=Staff.json&doneFileName=done.txt&noop=true&filterFile=$simple{file:size} > 1000")
			.routeId("copyJsonFileToFTP")
			.process(processor)
			.to("{{ftp.uri}}/Json?username={{ftp.username}}&password={{ftp.password}}&fileName=Staff-${header.timestamp}.json");
	}

}
