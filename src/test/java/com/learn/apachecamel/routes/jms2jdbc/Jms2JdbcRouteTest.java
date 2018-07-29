package com.learn.apachecamel.routes.jms2jdbc;

import com.learn.apachecamel.routes.jdbc.DBPostgresRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.ArrayList;

public class Jms2JdbcRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder(){
        return new Jms2JdbcRoute();
    }

    //Populating myDataSource connection which is being used in Route

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

        BasicDataSource basicDS = new BasicDataSource();
        basicDS.setUsername("postgres");
        basicDS.setDriverClassName("org.postgresql.Driver");
        basicDS.setPassword("postgres");
        basicDS.setUrl(url);
        return basicDS;
    }

    @Test
    public void insertData(){
        //String inputMessage = "First DB Input Message";
        ArrayList responseList = (ArrayList) consumer.receiveBody("direct:outputMessage");
        System.out.println("Response List Size : "+responseList.size());
       /* String outputMessage = "";

        if(responseList!=null && responseList.size()!=0)
        {
            Iterator it = responseList.iterator();
            while(it.hasNext()){
                System.out.println("First in list"+it.next().toString());

                outputMessage =  it.next().toString();

                System.out.println("Output Message "+outputMessage);

            }

        }*/

        //assertEquals(4,responseList.size());
        assertNotEquals(0,responseList.size());
        //assertTrue(outputMessage.contains(inputMessage));



    }

}
