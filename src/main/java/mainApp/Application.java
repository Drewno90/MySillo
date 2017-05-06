package mainApp;

import Users.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class Application {

    private static SessionFactory factory;

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);

        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Application ME = new Application();

      /* Add few employee records in database */
//        Integer empID1 = ME.addUser("Zara");
//        Integer empID2 = ME.addUser("Daisy");
//        Integer empID3 = ME.addUser("John");

      /* List down all the employees */
//        ME.listUsers();
//
//      /* Update employee's records */
//        ME.updateUser(empID1, "bóbr");
//
//      /* Delete an employee from the database */
//        ME.deleteUser(empID2);
//
//      /* List down new list of the employees */
//        ME.listUsers();
    }

    private Integer addUser(String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userID = null;
        try{
            tx = session.beginTransaction();
            User user = new User(name);
            userID = (Integer) session.save(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userID;
    }


    public void listUsers( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List<User> users = (List<User>) session.createQuery("from User").list();
            for (Iterator iterator =
                 users.iterator(); iterator.hasNext();){
                User user = (User) iterator.next();
                System.out.print("First Name: " + user.getName());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to UPDATE salary for an employee */
    public void updateUser(Integer UserID, String name ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User user =
                    (User) session.get(User.class, UserID);
            user.setName( name );
            session.update(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteUser(Integer UserID){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User user =
                    (User) session.get(User.class, UserID);
            session.delete(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

}