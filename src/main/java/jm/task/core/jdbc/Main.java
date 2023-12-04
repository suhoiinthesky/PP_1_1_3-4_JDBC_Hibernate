package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtilXml;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        SessionFactory sessionFactory = HibernateUtilXml.getSessionFactory();
        try  (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User("joka", "boka", (byte) 5));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
