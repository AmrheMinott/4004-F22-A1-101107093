package server_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game_server.GameServer;

public class GameServerTest {
    GameServer server = new GameServer();

    @Test
    public void testShufflingOfDeck() {
        assertEquals((4 * 6) + (3 * 2), server.getDeck().size());
    }
}
