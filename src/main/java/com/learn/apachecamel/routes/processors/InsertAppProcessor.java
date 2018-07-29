package com.learn.apachecamel.routes.processors;

import org.apache.camel.Exchange;

public class InsertAppProcessor implements org.apache.camel.Processor {

    public void process(Exchange exchange) throws Exception {

        String inputMessageFromQueue = exchange.getIn().getBody(String.class);
        System.out.println("Processor: received input message  - "+inputMessageFromQueue);

        String preparedInsertQuery = "INSERT INTO persistmessages values ('1','"+inputMessageFromQueue+"')";
        System.out.println("AppProcessor: Prepared Insert Query - "+preparedInsertQuery);

        //set insert query to exchange
        exchange.getIn().setBody(preparedInsertQuery);


    }
}
