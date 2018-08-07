package cj.dice.service;

import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import cj.dice.entity.Scoreboard_;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ScoreboardDao extends AbstractDao {

    public void create(Scoreboard scoreboard) {
        super.create(scoreboard);
    }

    public List<Scoreboard> findScoreboardsByPlayer(Player player) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scoreboard> query = criteriaBuilder.createQuery(Scoreboard.class);
        Root<Scoreboard> root = query.from(Scoreboard.class);
        query.where(criteriaBuilder.equal(root.get(Scoreboard_.player), player));
        query.select(root);
        TypedQuery<Scoreboard> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}