package com.learn.apachecamel.routes.exception;

import org.apache.camel.Exchange;

public class ExceptionProcessor implements org.apache.camel.Processor {

    public void process(Exchange exchange) throws Exception {

        //Exception ex = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        Exception ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        System.out.println("Actual Received Exception Message : "+ex.getMessage());
        System.out.println("Actual Exception Class : "+ex.getClass());

        String failedEndpoint = exchange.getProperty(Exchange.FAILURE_ENDPOINT, String.class);
        System.out.println("Failed Endpoint : "+failedEndpoint);

        exchange.getIn().setBody("Exception Happened in the Route!!");

    }
}
