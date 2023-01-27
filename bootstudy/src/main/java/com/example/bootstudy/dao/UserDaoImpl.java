package com.example.bootstudy.dao;

import com.example.bootstudy.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAll() {
        return  entityManager.createQuery("select u from User u", User.class).
                getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select  u from User u join fetch u.roles " +
                "where u.username = :name", User.class).
                setParameter("name", name).getSingleResult();
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}




