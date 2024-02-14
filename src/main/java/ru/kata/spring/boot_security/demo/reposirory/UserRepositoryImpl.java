package ru.kata.spring.boot_security.demo.reposirory;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u from User u").getResultList();
    }

    @Override
    public User getById(Long id) {
        // все поля без пароля
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUserById(Long id) {
        Query query = entityManager.createQuery("delete from User user where user.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = (entityManager.createQuery("SELECT user FROM  User user Join fetch  user.roles WHERE  user.email=:email", User.class));
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
