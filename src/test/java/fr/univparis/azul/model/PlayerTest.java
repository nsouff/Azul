package fr.univparis.azul.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import fr.univparis.azul.model.area.CenterArea;
import fr.univparis.azul.model.area.Factory;
import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.tile.SpecialTile;

public class PlayerTest {

	private static String[] players = {"Romain","CPU1","Chris"};

	@Test
	public void moveFromFactoryToPatternTest() throws Exception{
		Game g = new Game(players);
		Player p = g.getPlayer("Romain");
		Factory f = new Factory();
		f.add(new ColoredTile(ColoredTile.Colors.BLUE));
		f.add(new ColoredTile(ColoredTile.Colors.BLUE));
		f.add(new ColoredTile(ColoredTile.Colors.BLUE));
		f.add(new ColoredTile(ColoredTile.Colors.RED));

		p.moveFromFactoryToPattern(ColoredTile.Colors.BLUE, f, 0);
		assertEquals(2, p.playerBoard.getFloor().size());
		assertEquals(1, g.getBoard().center.size());
		assertEquals(1, p.playerBoard.getPatternArea().size());
		assertTrue(f.isEmpty());
		p.playerBoard.getFloor().add(new ColoredTile(ColoredTile.Colors.BLUE));
		p.playerBoard.getFloor().add(new ColoredTile(ColoredTile.Colors.BLUE));
		p.playerBoard.getFloor().add(new ColoredTile(ColoredTile.Colors.BLUE));
		p.playerBoard.getFloor().add(new ColoredTile(ColoredTile.Colors.BLUE));

		for (int i = 0; i < 4; i++) f.add(new ColoredTile(ColoredTile.Colors.BLUE));

		p.moveFromFactoryToPattern(ColoredTile.Colors.BLUE, f, 1);
		assertEquals(1, g.getBoard().trash.size());
		assertEquals(3, p.playerBoard.getPatternArea().size());
		assertEquals(7, p.playerBoard.getFloor().size());
		assertEquals(1, g.getBoard().center.size());

	}


	@Test
	public void moveFromCenterAreatoPattern() throws Exception {
		Game g = new Game(players);
		Player p = g.getPlayer("Romain");
		CenterArea c = g.getBoard().center;
		for (int i = 0; i < 6; i++) c.add(new ColoredTile(ColoredTile.Colors.GREEN));
		c.add(new FirstTile());
		c.add(new ColoredTile(ColoredTile.Colors.YELLOW));
		try {p.moveFromCenterAreatoPattern(ColoredTile.Colors.GREEN, 4);} catch (Exception e) {e.printStackTrace();}
		assertEquals(2, p.playerBoard.getFloor().size());
		assertEquals(1, c.size());
		assertEquals(5, p.playerBoard.getPatternArea().size());
		assertEquals(ColoredTile.Colors.YELLOW, ((ColoredTile)c.getTiles().get(0)).getColor());
	}

	@Test
	public void moveFromFactoryToFloorTest() throws Exception {
		Game g = new Game(players);
		Player p = g.getPlayer("Romain");
		Factory f = new Factory();
		for (int i = 0; i < 3; i++) f.add(new ColoredTile(ColoredTile.Colors.BLUE));
		f.add(new ColoredTile(ColoredTile.Colors.BLACK));
		for (int i = 0; i < 6; i++) p.playerBoard.getFloor().add(new FirstTile());
		p.moveFromFactoryToFloor(ColoredTile.Colors.BLUE, f);
		assertEquals(7, p.playerBoard.getFloor().size());
		assertEquals(2, g.getBoard().trash.size());
		assertEquals(1, g.getBoard().center.size());
	}

	@Test
	public void moveSpecialTileFromCenterToFloorTest() throws Exception {
		Game g = new Game(players);
		Player p = g.getPlayer("Romain");
		for (int i = 0; i < 2; i++) {
			g.getBoard().center.add(new ColoredTile(ColoredTile.Colors.BLUE));
			g.getBoard().center.add(new ColoredTile(ColoredTile.Colors.GREEN));
		}
		
		g.getBoard().center.add(new FirstTile());
		p.moveFirstTileFromCenterToFloor();

		assertEquals(1, p.playerBoard.getFloor().size());
		assertEquals(4, g.getBoard().center.size());
	}

	@Test
	public void moveFromCenterToFloorTest() throws Exception {
		Game g = new Game(players);
		Game.GameBoard board = g.getBoard();
		Player p = g.getPlayer("Romain");
		for (int i = 0; i < 3; i++) {
			board.center.add(new ColoredTile(ColoredTile.Colors.BLUE));
			board.center.add(new ColoredTile(ColoredTile.Colors.GREEN));
		}
		board.center.add(new FirstTile());
		p.moveFromCenterToFloor(ColoredTile.Colors.GREEN);

		assertEquals(3, board.center.size());
		assertEquals(4, p.playerBoard.getFloor().size());
	}
}
