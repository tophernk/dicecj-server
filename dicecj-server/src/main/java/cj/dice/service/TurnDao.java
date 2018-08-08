package cj.dice.service;

import cj.dice.entity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Stateless
public class TurnDao extends AbstractDao {

    @PersistenceContext
    EntityManager entityManager;

    public Turn findTurnById(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Turn> query = criteriaBuilder.createQuery(Turn.class);
        Root<Turn> root = query.from(Turn.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get(Turn_.ID), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    public Turn findTurnByPlayer(String playerName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Turn> query = criteriaBuilder.createQuery(Turn.class);
        Root<Turn> root = query.from(Turn.class);
        Join<Turn, Scoreboard> scoreboardJoin = root.join(Turn_.SCOREBOARD);
        Join<Scoreboard, Player> playerJoin = scoreboardJoin.join(Scoreboard_.PLAYER);
        query.select(root);
        query.where(criteriaBuilder.equal(playerJoin.get(Player_.NAME), playerName));
        return entityManager.createQuery(query).getSingleResult();
    }

    public void createTurn(Turn turn) {
        super.create(turn);
    }
}
