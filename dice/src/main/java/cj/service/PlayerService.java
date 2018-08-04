package cj.service;

import cj.entity.Player;

public class PlayerService {

    public void createPlayer(Player player) {
        new PlayerDao().create(player);
    }

    public Player findPlayerByName(String name) {
        return new PlayerDao().findPlayerByName(name);
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
