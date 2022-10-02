package server_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game_server.GameServer;

public class GameServerTest {
    GameServer server = new GameServer();

    @Test
    public void testShufflingOfDeck() {
        assertEquals(server.getDeck().size(), 26);
    }
}
