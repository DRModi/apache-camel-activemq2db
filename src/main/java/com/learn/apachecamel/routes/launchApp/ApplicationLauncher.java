package com.learn.apachecamel.routes.launchApp;

import com.learn.apachecamel.routes.jms2jdbc.JMS2JDBCAppRoute;
import org.apache.camel.main.Main;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ApplicationLauncher {
    
    public static void main(String[] args){

        //create apache.camel.main 
        Main main = new Main();
        
        //database host url
        String datasourceUrl = "jdbc:postgresql://localhost:5432/appdb";
        
        //Using bind method, populate/map registry to camel context
        main.bind("myAppDataSource", setupDataSource(datasourceUrl));

        //add route to main
        main.addRouteBuilder(new JMS2JDBCAppRoute());

        System.out.println("Main Class: Starting Camel JMS to DB Route!!");

        try {
            //running the main method
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DataSource setupDataSource(String datasourceUrl) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(datasourceUrl);
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }
}
