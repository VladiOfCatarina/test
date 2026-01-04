package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Vova", "Ivanov", (byte) 20);
        usi.saveUser("Sasha", "Kuznetsov", (byte) 30);
        usi.saveUser("Masha", "Kim", (byte) 25);
        usi.saveUser("Sveta", "Dmitrieva", (byte) 18);

        System.out.println(usi.getAllUsers());
        usi.removeUserById(2);
        System.out.println("Removed user with id = 2");
        System.out.println(usi.getAllUsers());
        usi.cleanUsersTable();
        usi.dropUsersTable();

    }
}
