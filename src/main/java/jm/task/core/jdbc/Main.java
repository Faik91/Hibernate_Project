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


        userDaoHibernate.saveUser("Solomon", "Kane", (byte) 30);
        userDaoHibernate.saveUser("Charlie", "Chaplin", (byte) 60);
        userDaoHibernate.saveUser("Oleg", "Olegov", (byte) 54);
        userDaoHibernate.saveUser("Bruce", "Li", (byte) 21);


        List<User> userList = userDaoHibernate.getAllUsers();
        System.out.println(userList.toString());

        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
        

    }
}
