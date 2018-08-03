package com.example.training;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StaffRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("{{ftp.uri}}?username={{ftp.username}}&password={{ftp.password}}&doneFileName=done.txt&fileName=Staff.csv&delete=true&localWorkDirectory=/receives/tmp&moveFailed=.Failed")
		    .to("file:D:/receives").log("download file complete");
	}
}
