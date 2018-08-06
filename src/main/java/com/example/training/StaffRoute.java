package com.example.training;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffRoute extends RouteBuilder {

	@Autowired
	private DataFormat bindy;

	@Autowired
	private StaffProccessor proccessor;

	@Override
	public void configure() throws Exception {
		from(
		    "{{ftp.uri}}?username={{ftp.username}}&password={{ftp.password}}&doneFileName=done.txt&fileName=Staff.csv&delete=true&localWorkDirectory=/receives/tmp&moveFailed=.Failed")
		        .routeId("downloadRouteId").to("file:D:/receives").log("download file complete");

		from("file:D:/receives?fileName=Staff.csv&noop=true").routeId("csvParseRouteId").unmarshal(bindy)
		    .process(proccessor).to("direct:processStaff");

		from("direct:processStaff").routeId("processStaffRouteId").to("bean:staffService?method=save").log("Save successfully").end();
	}
}
