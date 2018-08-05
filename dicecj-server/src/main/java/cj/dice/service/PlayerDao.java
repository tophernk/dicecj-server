package cj.dice.service;

import cj.dice.entity.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

@Stateless
public class PlayerDao extends AbstractDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Player findPlayerByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
        Root<Player> root = query.from(Player.class);
        EntityType<Player> playerModel = root.getModel();
        query.where(criteriaBuilder.equal(root.get(playerModel.getAttribute("name").getName()), name));
        query.select(root);
        TypedQuery<Player> typedQuery = entityManager.createQuery(query);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
