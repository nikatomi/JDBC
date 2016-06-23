package com.company.arhitype;


import java.util.ResourceBundle;

public class DataSource {

    private final String driver;
    private final String url;
    private final String password;
    private final String user;

    public DataSource(){
        ResourceBundle bundle = ResourceBundle.getBundle("db");

        driver = bundle.getString("db.driver");
        url = bundle.getString("db.url");
        password = bundle.getString("db.password");
        user = bundle.getString("db.user");
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }
}
