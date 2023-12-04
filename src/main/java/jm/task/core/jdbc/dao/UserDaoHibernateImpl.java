package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtilXml;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static UserDaoHibernateImpl instance = new UserDaoHibernateImpl();


    private UserDaoHibernateImpl() {

    }

    private UserDaoHibernateImpl(UserDaoHibernateImpl instance) {
        UserDaoHibernateImpl.instance = instance;
    }

    public static UserDaoHibernateImpl getInstance() {
        return instance;
    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = HibernateUtilXml.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                      " name VARCHAR(255)," +
                                      " lastName VARCHAR(255)," +
                                      " age TINYINT)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = HibernateUtilXml.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = HibernateUtilXml.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = HibernateUtilXml.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = HibernateUtilXml.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User").list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = HibernateUtilXml.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
