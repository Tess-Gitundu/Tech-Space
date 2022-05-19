package Interfaces;

import Models.User;

import java.util.List;

public interface UserInterface {
    void save(User user);

    void update(int id, User user);

    List<User> getAll();

    void clearAll();

    void delete(int id);

    User findById(int id);

}
