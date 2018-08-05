package cj.dice.service;

import cj.dice.entity.Player;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PlayerService {

    @Inject
    private PlayerDao dao;

    public void createPlayer(Player player) {
        dao.create(player);
    }

    public Player findPlayerByName(String name) {
        return dao.findPlayerByName(name);
    }

    public Player findOrCreatePlayer(String playerName) {
        Player player = findPlayerByName(playerName);
        if (player == null) {
            player = new Player(playerName);
            createPlayer(player);
        }
        return player;
    }
}
