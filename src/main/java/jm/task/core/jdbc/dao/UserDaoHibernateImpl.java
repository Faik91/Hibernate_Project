package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("CREATE TABLE users (id INT  PRIMARY KEY AUTO_INCREMENT, name VARCHAR (30), lastName VARCHAR (30), age INT)");
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("DROP TABLE users");
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            query.setParameter(0, name);
            query.setParameter(1, lastName);
            query.setParameter(2, age);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("DELETE FROM users WHERE id =" + id);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> userList = null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT * FROM users");
            query.addEntity(User.class);
            userList = query.list();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("DELETE FROM users");
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }
}
