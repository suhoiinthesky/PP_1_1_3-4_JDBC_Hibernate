package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtilXml {

    public static final SessionFactory sessionFactory = getSessionFactory();
    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Инициализация SessionFactory не удалась: " + e);
        }
    }
}