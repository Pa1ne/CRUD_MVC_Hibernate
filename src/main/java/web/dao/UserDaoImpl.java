package web.dao;


import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import java.util.List;

import web.models.User;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void addOrEditUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.getReference(User.class, id);
        entityManager.remove(user);
    }
}