package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = new Util().getConnection();


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
           PreparedStatement statement = connection.prepareStatement("CREATE TABLE users (id INT  PRIMARY KEY AUTO_INCREMENT, name VARCHAR (30), lastName VARCHAR (30), age INT) ");
           statement.executeLargeUpdate();
        } catch (SQLException s) {

        }

    }

    public void dropUsersTable() {
        try {
            PreparedStatement statement = connection.prepareStatement("DROP TABLE users");
            statement.executeLargeUpdate();
        } catch (SQLException s) {

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeLargeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id =" + id);
            statement.executeLargeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                list.add(user);
            }
        }catch(SQLException s){
            s.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users");
            statement.executeLargeUpdate();
        }catch (SQLException s){
            s.printStackTrace();
        }

    }
}
