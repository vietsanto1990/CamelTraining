package com.example.training;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StaffImportRoute extends RouteBuilder {

	@Autowired
	@Qualifier("csvBindy")
	private DataFormat bindy;

	@Override
	public void configure() throws Exception {
		from("{{ftp.uri}}&doneFileName=done.txt&fileName=Staff.csv&delete=true&localWorkDirectory=/receives/tmp&moveFailed=.Failed")
      .routeId("downloadStaffRoute")
      .to("{{file.local}}")
      .to("mock://result")
      .log("download file complete");

		from("{{file.local}}?fileName=Staff.csv").routeId("csvParseStaffRoute")
			.unmarshal(bindy).to("direct:saveStaffs").to("mock://result");

		from("direct:saveStaffs").routeId("saveStaffsRoute")
			.to("jpa://com.example.training.entity.Staff?entityType=java.util.ArrayList").log("Save successfully").to("mock://result");
	}
}
