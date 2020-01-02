package fr.univparis.azul.model.area;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.Tile;

public class BagTest {

	@Test
	public void constructorTest() {
		Bag bag = new Bag();
		int[] color = {0,0,0,0,0};

		for (Tile t : bag.tiles) {
			ColoredTile.Colors c = ((ColoredTile) t).getColor();
			switch (c) {
			case BLUE  : color[0]++; break;
			case RED   : color[1]++; break;
			case GREEN : color[2]++; break;
			case BLACK : color[3]++; break;
			case YELLOW: color[4]++; break;
			}
		}
		for (int i = 0; i < 5; i++) assertEquals(20, color[i]);

	}

	@Test
	public void fillTest() {
		Bag bag  = new Bag();
		bag.tiles.clear();
		ColoredTile t1 = new ColoredTile(ColoredTile.Colors.GREEN);
		ColoredTile t2 = new ColoredTile(ColoredTile.Colors.GREEN);
		ColoredTile t3 = new ColoredTile(ColoredTile.Colors.GREEN);
		ColoredTile t4 = new ColoredTile(ColoredTile.Colors.GREEN);
		bag.add(t1); bag.add(t2); bag.add(t3); bag.add(t4);
		Factory f = new Factory();
		bag.fill(f);
		assertEquals(t1, f.tiles.get(0));
		assertEquals(t2, f.tiles.get(1));
		assertEquals(t3, f.tiles.get(2));
		assertEquals(t4, f.tiles.get(3));
	}
}
