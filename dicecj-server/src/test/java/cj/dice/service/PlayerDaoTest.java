package cj.dice.service;

import cj.dice.command.MockitoTest;
import cj.dice.entity.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class PlayerDaoTest extends MockitoTest {

    @InjectMocks
    private PlayerDao daoUnderTest;

    private EntityManager testEM;

    @Mock
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dicecjtest");
        testEM = entityManagerFactory.createEntityManager();
        transaction = testEM.getTransaction();
        CriteriaBuilder criteriaBuilder = testEM.getCriteriaBuilder();
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(entityManager.createQuery(Mockito.any(CriteriaQuery.class))).thenAnswer(
                (Answer<TypedQuery<Player>>) invocationOnMock -> {
                    CriteriaQuery argument = invocationOnMock.getArgumentAt(0, CriteriaQuery.class);
                    TypedQuery query = testEM.createQuery(argument);
                    return query;
                });
    }

    @Test
    public void findPlayerByName() {
        String name = "test";
        Player player = daoUnderTest.findPlayerByName(name);
        Assert.assertNull(player);
        transaction.begin();
        testEM.persist(new Player(name));
        transaction.commit();
        player = daoUnderTest.findPlayerByName(name);
        Assert.assertNotNull(player);
    }
}