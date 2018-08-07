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

    public Turn findTurnByPlayer(String playerName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Turn> query = criteriaBuilder.createQuery(Turn.class);
        Root<Turn> root = query.from(Turn.class);
        Join<Turn, Scoreboard> scoreboardJoin = root.join(Turn_.scoreboard);
        Join<Scoreboard, Player> playerJoin = scoreboardJoin.join(Scoreboard_.player);
        query.select(root);
        query.where(criteriaBuilder.equal(playerJoin.get(Player_.NAME), playerName));
        TypedQuery<Turn> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }

    public void createTurn(Turn turn) {
        super.create(turn);
    }
}
