package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;


   @Override
   public void add(User user, Car car) {
      Session session =  sessionFactory.openSession();
      user.setUserCar(car);
      car.setUser(user);
      session.beginTransaction();
      session.save(car);
      session.getTransaction().commit();
   }


   public User getCarUser(String model, int series){
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      TypedQuery<User> query = session.createQuery("SELECT user from User user where" +
              " user.userCar.model = (:setModel) AND user.userCar.series = (:setSeries)",User.class);
      query.setParameter("setModel",model);
      query.setParameter("setSeries", series);
      User newUser = query.getSingleResult();
      session.close();
      return newUser;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
