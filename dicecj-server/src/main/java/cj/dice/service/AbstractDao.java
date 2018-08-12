package cj.dice.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public abstract class AbstractDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public void create(Object o) {
        entityManager.persist(o);
        entityManager.flush();
    }
}
