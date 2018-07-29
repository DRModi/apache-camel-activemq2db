package com.learn.apachecamel.routes.jdbc;

import com.learn.apachecamel.routes.processors.InsertProcessor;
import org.apache.camel.builder.RouteBuilder;

public class DBPostgresRoute extends RouteBuilder {


    public void configure() throws Exception {

        from("direct:inputMessage")
                .to("log:?level=INFO&showBody=true")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("log:?level=INFO&showBody=true");
    }
}
