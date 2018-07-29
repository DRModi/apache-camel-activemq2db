package com.learn.apachecamel.routes.jms2jdbc;

import com.learn.apachecamel.routes.exception.ExceptionProcessor;
import com.learn.apachecamel.routes.processors.InsertAppProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;

public class JMS2JDBCAppRoute extends RouteBuilder {

    public void configure() throws Exception {

        onException(PSQLException.class).handled(true).log("App Route Error: Received Exception while inserting message")
                .process(new ExceptionProcessor());


        from("activemq:queue:AppQueue")
                .to("log:?level=INFO&showBody=true")
                .process(new InsertAppProcessor())
                .log("Route: After processing prepared insert query is - ${body}")
                .to("jdbc:myAppDataSource");
    }
}
