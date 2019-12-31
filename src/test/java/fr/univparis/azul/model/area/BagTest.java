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
}
