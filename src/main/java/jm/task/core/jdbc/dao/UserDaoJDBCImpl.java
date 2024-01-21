package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String sql;

    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){
            sql = "create table if not exists users\n" +
                    "(\n" +
                    "    id          int auto_increment\n" +
                    "        primary key,\n" +
                    "    name        varchar(40) null,\n" +
                    "    `lastName` varchar(40) null,\n" +
                    "    age         tinyint     null\n" +
                    ");";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            sql = "drop table if exists users\n";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        sql = "insert into users (name, lastName, age) values(?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        sql = "delete from users where id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsersList = new ArrayList<>();

        sql = "select id, name, lastName, age from users";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName((resultSet.getString(2)));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));

                allUsersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsersList;
    }

    public void cleanUsersTable() {
        sql = "truncate table users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
