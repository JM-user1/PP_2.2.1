package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user, Car car);
    void add(Car car, User user);
    User getCarUser(String model, int series);
    List<User> listUsers();
}
