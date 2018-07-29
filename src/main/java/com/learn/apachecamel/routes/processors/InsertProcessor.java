package com.learn.apachecamel.routes.processors;

import org.apache.camel.Exchange;

public class InsertProcessor implements org.apache.camel.Processor {

    public void process(Exchange exchange) throws Exception {

        String inputMessage = (String) exchange.getIn().getBody();
        System.out.println("Received Input Message at Processor : "+inputMessage);

        String insertQuery ="INSERT INTO messages values ('1',' "+inputMessage+"')";
        System.out.println("INSERT Query : " + insertQuery);

        exchange.getIn().setBody(insertQuery);

    }
}
