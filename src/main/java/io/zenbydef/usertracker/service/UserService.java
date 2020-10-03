package io.zenbydef.usertracker.service;

import io.zenbydef.usertracker.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void saveUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    List<User> searchUsers(String theSearchName);
}
