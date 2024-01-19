package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userFinalTest = new UserServiceImpl();
        //Создание таблицы User(ов)
        userFinalTest.createUsersTable();
        //Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userFinalTest.saveUser("Name1", "LastName1", (byte) 20);
        userFinalTest.saveUser("Name2", "LastName2", (byte) 25);
        userFinalTest.saveUser("Name3", "LastName3", (byte) 31);
        userFinalTest.saveUser("Name4", "LastName4", (byte) 38);
        //Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        System.out.println(userFinalTest.getAllUsers());
        //Очистка таблицы User(ов)
        userFinalTest.cleanUsersTable();
        //Удаление таблицы
        userFinalTest.dropUsersTable();

        Util.closeConnection();
    }
}
