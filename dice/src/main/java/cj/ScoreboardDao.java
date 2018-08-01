package cj;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.util.List;

public class ScoreboardDao extends AbstractDao {

    public void create(Scoreboard scoreboard) {
        super.create(scoreboard);
    }

    public List<Scoreboard> findScoreboardsByPlayer(Player player) {
        EntityManager entityManager = CrudSupport.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scoreboard> query = criteriaBuilder.createQuery(Scoreboard.class);
        Root<Scoreboard> root = query.from(Scoreboard.class);
        EntityType<Scoreboard> model = root.getModel();
        query.where(criteriaBuilder.equal(root.get(model.getAttribute("player").getName()), player));
        query.select(root);
        TypedQuery<Scoreboard> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}