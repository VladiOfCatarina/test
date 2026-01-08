package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS User (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(30) NOT NULL," +
                    "lastName VARCHAR(40) NOT NULL," +
                    "age INT UNSIGNED)";
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS user";
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User tempUser = new User(name, lastName, age);

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(tempUser);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            User tempUser = session.get(User.class, id);
            if (tempUser != null) {
                Transaction transaction = session.beginTransaction();
                session.remove(tempUser);
                transaction.commit();
            }
        } catch (Exception e) {
            System.out.println("ERROR REMOVE");
        }
    }

    @Override
    public List<User> getAllUsers() {
        String hql = "FROM User";
        try (Session session = sessionFactory.openSession()) {
            List<User> resultList = session.createQuery(hql, User.class).list();
            return resultList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.createNativeQuery("ALTER TABLE user AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("ERROR CLEAN");
        }
    }
}
