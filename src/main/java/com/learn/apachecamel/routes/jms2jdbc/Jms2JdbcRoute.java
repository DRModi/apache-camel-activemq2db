package com.learn.apachecamel.routes.jms2jdbc;

import com.learn.apachecamel.routes.processors.InsertProcessor;
import org.apache.camel.builder.RouteBuilder;

public class Jms2JdbcRoute extends RouteBuilder {

    public void configure() throws Exception {

        from("activemq:queue:testQueue")
                .log("/n ******* Printing First Message")
                .to("log:?level=INFO&showBody=true")
                .log("/n ******* Printing Second Message")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("log:?level=INFO&showBody=true")
                .to("direct:outputMessage");
    }
}
