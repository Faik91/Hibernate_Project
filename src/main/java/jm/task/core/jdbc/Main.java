package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        System.out.println("Таблица создана");

        User user1 = new User("Solomon", "Kane", (byte) 30);
        User user2 = new User("Charlie", "Chaplin", (byte) 60);
        User user3 = new User("Oleg", "Olegov", (byte) 54);
        User user4 = new User("Bruce", "Li", (byte) 21);

        userDaoHibernate.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User " + user1.getName() + " добавлен в базу данных");
        userDaoHibernate.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User " + user2.getName() + " добавлен в базу данных");
        userDaoHibernate.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User " + user3.getName() + " добавлен в базу данных");
        userDaoHibernate.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User " + user4.getName() + " добавлен в базу данных");


        List<User> userList = userDaoHibernate.getAllUsers();
        System.out.println(userList.toString());

        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
        

    }
}
