package fr.univparis.azul.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class GameTest {

	@Test
	public void constructorTest() throws Exception{
		String[] players = {"Romain","CPU1","Chris"};
		Game g = new Game(players);
		assertEquals(3, g.getNbPlayers());
	}

}
