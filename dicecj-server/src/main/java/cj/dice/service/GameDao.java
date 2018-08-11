package cj.dice.service;

import cj.dice.entity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Stateless
public class GameDao extends AbstractDao {

    @PersistenceContext
    EntityManager entityManager;

    public Game findGameById(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> query = criteriaBuilder.createQuery(Game.class);
        Root<Game> root = query.from(Game.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get(Game_.ID), id));
        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Game findGameByPlayer(Player player) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> query = criteriaBuilder.createQuery(Game.class);
        Root<Game> root = query.from(Game.class);
        Join<Game, Scoreboard> scoreboardJoin = root.join(Game_.SCOREBOARD);
        query.select(root);
        query.where(criteriaBuilder.equal(scoreboardJoin.get(Scoreboard_.PLAYER), player));
        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Game findGameByPlayerName(String playerName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> query = criteriaBuilder.createQuery(Game.class);
        Root<Game> root = query.from(Game.class);
        Join<Game, Scoreboard> scoreboardJoin = root.join(Game_.SCOREBOARD);
        Join<Scoreboard, Player> playerJoin = scoreboardJoin.join(Scoreboard_.PLAYER);
        query.select(root);
        query.where(criteriaBuilder.equal(playerJoin.get(Player_.NAME), playerName));
        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createGame(Game game) {
        super.create(game);
    }
}
