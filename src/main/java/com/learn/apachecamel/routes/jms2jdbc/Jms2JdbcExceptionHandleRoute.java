package com.learn.apachecamel.routes.jms2jdbc;

import com.learn.apachecamel.routes.exception.ExceptionProcessor;
import com.learn.apachecamel.routes.processors.InsertProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;

public class Jms2JdbcExceptionHandleRoute extends RouteBuilder {
    public void configure() throws Exception {


        onException(PSQLException.class).handled(true).log("Error: Received Exception while inserting message")
                .process(new ExceptionProcessor());

              from("activemq:queue:testQueue")
                .to("log:?level=INFO&showBody=true")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages1?dataSource=myDataSource")
                .to("log:?level=INFO&showBody=true")
                .to("direct:outputMessage");

    }
}
