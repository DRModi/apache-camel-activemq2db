package com.learn.apachecamel.routes.jms;

import org.apache.camel.builder.RouteBuilder;

public class JMSReadRoute extends RouteBuilder {


    public void configure() throws Exception {

        from("activemq:queue:testQueue")
                .log("Received input message is ${body}")
                .to("direct:readMessageFromQueue");
    }
}
