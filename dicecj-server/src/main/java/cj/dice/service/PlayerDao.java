package cj.dice.service;

import cj.dice.entity.Player;
import cj.dice.entity.Player_;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class PlayerDao extends AbstractDao {

    public Player findPlayerByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
        Root<Player> root = query.from(Player.class);
        query.where(criteriaBuilder.equal(root.get(Player_.NAME), name));
        query.select(root);
        TypedQuery<Player> typedQuery = entityManager.createQuery(query);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
