package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtilXml;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nascofe", "Gold", (byte) 100);
        userService.saveUser("Nikola", "Tesla", (byte) 100);
        userService.saveUser("Ronald", "Mc`Donald", (byte) 100);
        userService.saveUser("Santa", "Clous", (byte) 100);
        userService.saveUser("Adolf", "Rihter", (byte) 100);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
