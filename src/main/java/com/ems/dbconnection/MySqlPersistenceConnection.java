package com.ems.dbconnection;

import com.ems.configuration.buisness.Configuration;
import com.ems.configuration.buisness.ConfigurationFromJSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlPersistenceConnection {
    private static Connection conn;
    private static MySqlPersistenceConnection instance;

    private String mysqlUrl;

    private String password;
    private String userName;

    private MySqlPersistenceConnection() throws SQLException {
        try {
            Configuration config=ConfigurationFromJSON.getInstance();
            String env=config.getEnvironmentFromConfiguration();
            mysqlUrl= config.getConfigurationByKey(env,"mysqlUrl");
            password= config.getConfigurationByKey(env,"dbPassword");
            userName= config.getConfigurationByKey(env,"userName");

            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(mysqlUrl, userName, password);
    }

    public static MySqlPersistenceConnection getInstance() throws SQLException {

        if (null == instance) {
            instance = new MySqlPersistenceConnection();
            return instance;
        } else if (instance.getConnection().isClosed()) {
            instance = new MySqlPersistenceConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
