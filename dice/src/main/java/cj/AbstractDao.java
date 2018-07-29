package cj;

import javax.persistence.EntityManager;

public abstract class AbstractDao {
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Object o) {
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
