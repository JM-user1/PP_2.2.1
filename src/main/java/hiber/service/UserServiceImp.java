package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;


   @Transactional
   @Override
   public void add(User user, Car car) {
      User newUser = user;
      newUser.setUserCar(car);
      userDao.add(user, car);
   }

   @Override
   public User getCarUser(String model, int series){
     return userDao.getCarUser(model,series);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

}
