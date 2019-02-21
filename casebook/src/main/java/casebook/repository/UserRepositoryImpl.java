package casebook.repository;

import casebook.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<User> findAll() {
        this.entityManager.getTransaction().begin();
        List<User> users = this.entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return users;
    }

    @Override
    public User findById(String id) {
        this.entityManager.getTransaction().begin();
        User user = this.entityManager
                .createQuery("SELECT e FROM User e WHERE e.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        this.entityManager.getTransaction().begin();
        User user = this.entityManager
                .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return user;
    }
}
