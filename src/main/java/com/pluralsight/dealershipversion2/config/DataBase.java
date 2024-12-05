package com.pluralsight.dealershipversion2.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
public class DataBase {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "dealership";
    private static final String USER = System.getenv("MYSQL_ROOT_USER");
    private static final String PASSWORD = System.getenv("MYSQL_ROOT_PASSWORD");
    private DataBase instance ;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(URL + DATABASE);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (Exception e) {
            log.error("Error while getting connection: ", e);
            return null;
        }
    }
}
