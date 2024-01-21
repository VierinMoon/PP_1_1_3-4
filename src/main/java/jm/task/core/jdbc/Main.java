package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {

        UserService userFinalTest = new UserServiceImpl();

        userFinalTest.createUsersTable();

        userFinalTest.saveUser("Name1", "LastName1", (byte) 20);
        userFinalTest.saveUser("Name2", "LastName2", (byte) 25);
        userFinalTest.saveUser("Name3", "LastName3", (byte) 31);
        userFinalTest.saveUser("Name4", "LastName4", (byte) 38);

        System.out.println(userFinalTest.getAllUsers());

        userFinalTest.cleanUsersTable();

        userFinalTest.dropUsersTable();
    }
}
