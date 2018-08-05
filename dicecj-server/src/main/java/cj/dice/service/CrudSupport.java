package cj.dice.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CrudSupport {

    private static final EntityManager ENTITY_MANAGER = Persistence.createEntityManagerFactory("DiceCJ").createEntityManager();

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER;
    }
}
