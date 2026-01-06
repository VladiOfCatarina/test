package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService usi = new UserServiceImpl();
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
