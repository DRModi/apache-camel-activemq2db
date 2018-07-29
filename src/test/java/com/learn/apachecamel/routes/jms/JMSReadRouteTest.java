package com.learn.apachecamel.routes.jms;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class JMSReadRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder(){
        return new JMSReadRoute();
    }


    @Test
    public void ReadMessageFromJMSQueue(){

        String expectedValue = "Test Message";

        String outputMessage = (String) consumer.receiveBody("direct:readMessageFromQueue");

        assertEquals(expectedValue,outputMessage);
    }

}
