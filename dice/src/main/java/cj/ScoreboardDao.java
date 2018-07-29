package cj;

import javax.persistence.EntityManager;

public class ScoreboardDao extends AbstractDao {

    public ScoreboardDao(EntityManager em) {
        setEntityManager(em);
    }

    public void create(Scoreboard scoreboard) {
        super.create(scoreboard);
    }
}
