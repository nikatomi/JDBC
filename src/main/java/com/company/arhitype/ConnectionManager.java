package com.company.arhitype;


import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.sql.*;

@Singleton
public class ConnectionManager {

    private Connection connection;
    private DataSource dataSource;

    @Inject
    public ConnectionManager(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Connection getConnection(){
        try {
            if(connection==null||connection.isClosed()){

                Class.forName(dataSource.getDriver());
                connection = DriverManager.getConnection(dataSource.getUrl(),
                                                         dataSource.getUser(),
                                                         dataSource.getPassword());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
