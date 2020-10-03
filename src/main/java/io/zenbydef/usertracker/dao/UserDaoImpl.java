package io.zenbydef.usertracker.dao;

import io.zenbydef.usertracker.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory emfFactory;

    @Override
    public List<User> getUsers() {
        EntityManager entityManager = emfFactory.createEntityManager();

        return entityManager.createQuery("select a from users_db a", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<User> searchUsers(String theSearchName) {
        return null;
    }
}
