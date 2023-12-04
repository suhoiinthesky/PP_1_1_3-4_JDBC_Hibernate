package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
* Этот класс я создал после того как увидел условие об отсутствии XML 
 * Прошу понять, простить.
 * Я сделал оба варианта, так что можете применить и этот для проверки.
 * Не просто же так я осваивал этот треклятый XML ((9(
 */

public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/new_schema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Util instance;
    private Connection connection;
    private SessionFactory sessionFactory;


    private Util() {

        try {
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e); 
        }


        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", URL);
            configuration.setProperty("hibernate.connection.username", USERNAME);
            configuration.setProperty("hibernate.connection.password", PASSWORD);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError();
        }
    }


    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }


    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}