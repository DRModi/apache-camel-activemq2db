package com.learn.apachecamel.routes.jms2jdbc;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.naming.Context;
import javax.sql.DataSource;
import java.util.ArrayList;

public class Jms2JdbcExceptionRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder(){
        return new Jms2JdbcExceptionHandleRoute();
    }


    //Creating myDataSource connection and populating in CamelContext

    @Override
    public CamelContext createCamelContext(){

        String url = "jdbc:postgresql://localhost:5432/localdb";
        DataSource dataSource = setupDataSource(url);

        SimpleRegistry registry = new SimpleRegistry();
        registry.put("myDataSource",dataSource);

        CamelContext context = new DefaultCamelContext(registry);
        return context;
    }

    private static DataSource setupDataSource(String url) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setDriverClassName("org.postgresql.Driver");

        return dataSource;
    }


    @Test
    public void insertData(){

        //ArrayList responseList = (ArrayList) template.requestBody("direct:outputMessage");
        ArrayList responseList = (ArrayList) consumer.receiveBody("direct:outputMessage");
        System.out.println("ResponseList Size : "+responseList.size());

        assertNotEquals(0, responseList.size());

    }

}
