package com.example.training;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class StaffRestRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		restConfiguration().component("restlet").host("localhost").port("80").bindingMode(RestBindingMode.auto);
		
		rest("/staff")
    .get("/{staffId}").to("direct:detailStaff");
		
		from("direct:detailStaff").routeId("detailStaffRoute")
		.to("bean:bean:staffService?method=getDetail(${header.staffId})");
	}
}
