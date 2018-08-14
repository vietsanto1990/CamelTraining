package com.example.training;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.SimpleBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.training.entity.Staff;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = CameTrainingApplication.class, properties = {
    "camel.springboot.java-routes-include-pattern=**/StaffImport*" })
public class StaffImportRouteTest extends CamelTestSupport {
	
	private StaffImportRoute importRoute;
	
	@Autowired
	public ProducerTemplate template;

	@Autowired
	protected CamelContext context;

	@Override
	public boolean isUseAdviceWith() {
		return true;
	}

	@Override
	public boolean isUseRouteBuilder() {
		return true;
	}

	@Override
	public boolean isCreateCamelContextPerClass() {
		return true;
	}

	@Override
	protected CamelContext createCamelContext() throws Exception {
		return context;
	}

	private String DOWNLOAD_STAFF_ROUTE_ID = "downloadStaffRoute";
	private String CSV_PARSE_ROUTE_ID = "csvParseStaffRoute";

	@EndpointInject(uri = "mock://downloadStaffResult")
	public MockEndpoint downloadStaffResult;
	@EndpointInject(uri = "mock://parseCsvResult")
	public MockEndpoint parseCsvResult;

	@Before
	public void setUp() throws Exception {
		context.start();
		importRoute = new StaffImportRoute();
	}

	@After
	public void tearDown() throws Exception {
		context.stop();
		importRoute = null;
	}

	@Test
	public void testImportDataFromCSV() throws Exception {
		RouteDefinition downloadStaffRouteDef = context.getRouteDefinition(DOWNLOAD_STAFF_ROUTE_ID);
		downloadStaffRouteDef.adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				replaceFromWith("direct://downloadStaff");
				interceptSendToEndpoint("file://*").skipSendToOriginalEndpoint().to("mock://downloadStaffResult");
			}
		});
		downloadStaffResult.expectedHeaderReceived("fileName", "staff.csv");
		template.sendBodyAndHeader("direct://downloadStaff", "temporary data", "fileName", "staff.csv");
		downloadStaffResult.assertIsSatisfied();
	}

	@Test
	public void testParsingCSV() throws Exception {
		assertNotNull(context);
		assertNotNull(parseCsvResult);
		
		String csvSample = "1,2,A\r3,4,B\r5,6,C";
		parseCsvResult.expectedMessageCount(1);
		RouteDefinition csvParseRoute = context.getRouteDefinition(CSV_PARSE_ROUTE_ID);
		csvParseRoute.adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				replaceFromWith("direct://parseCsv");
				interceptSendToEndpoint("direct:saveStaffs").skipSendToOriginalEndpoint().to("mock://parseCsvResult");
			}
		});
		template.sendBody("direct://parseCsv", csvSample);
		List<Exchange> list = parseCsvResult.getReceivedExchanges();
		List object = (List) list.get(0).getIn().getBody();
		assertTrue(object.size() == 2);
	}

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return importRoute;
	}

}
