package cj.service;

import javax.persistence.EntityManager;

public abstract class AbstractDao {

    public void create(Object o) {
        EntityManager entityManager = CrudSupport.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();
    }
}
