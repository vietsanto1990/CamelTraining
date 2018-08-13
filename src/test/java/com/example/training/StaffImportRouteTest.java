package com.example.training;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.EnableRouteCoverage;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = { CameTrainingApplication.class }, properties = {
    "camel.springboot.java-routes-include-pattern=**/StaffImport*" })
@EnableRouteCoverage
@MockEndpoints("mock://result")
public class StaffImportRouteTest extends CamelTestSupport {
	@Override
	public boolean isUseAdviceWith() {
		return true;
	}

	@Override
	public boolean isUseRouteBuilder() {
		return true;
	}

	@Override
	public String isMockEndpoints() {
		return "*";
	}

	private String DOWNLOAD_STAFF_ROUTE_ID = "downloadStaffRoute";
	private RouteDefinition downloadStaffRouteDef;

	@EndpointInject(uri = "mock://result")
	public MockEndpoint resultEndpoint;

	@Autowired
	public ProducerTemplate template;

	@Autowired
	private CamelContext camelContext;

	@Before
	public void setUp() throws Exception {
		downloadStaffRouteDef = camelContext.getRouteDefinition(DOWNLOAD_STAFF_ROUTE_ID);
		downloadStaffRouteDef.adviceWith(camelContext, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				mockEndpoints();
				interceptFrom("ftp://*").process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getIn().setBody("csv data");
						exchange.getIn().setHeader("fileName", "staff.csv");
					}
				});
				interceptSendToEndpoint("file://*").skipSendToOriginalEndpoint().to("mock://result");
			}
		});
		context.start();
	}

	@After
	public void tearDown() throws Exception {
		downloadStaffRouteDef = null;
		context.stop();
	}

	@Test
	void testImportDataFromCSV() throws Exception {
//		MockEndpoint endpoint = getMockEndpoint("mock:result");
		resultEndpoint.expectedHeaderReceived("fileName", "staff.csv");
//		endpoint.expectedHeaderReceived("fileName", "staff.csv");
		template.sendBodyAndHeader("temporary data", "fileName", "staff.csv");
		resultEndpoint.assertIsSatisfied();
//		endpoint.assertIsSatisfied();
	}

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new StaffImportRoute();
	}

}
