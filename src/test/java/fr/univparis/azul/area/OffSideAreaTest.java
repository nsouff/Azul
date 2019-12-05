package fr.univparis.azul.area;

import org.junit.Test;

import static org.junit.Assert.*;

import fr.univparis.azul.tile.*;

import java.util.LinkedList;

public class OffSideAreaTest {

  @Test
  public void refillTest() {
    Trash trash = new Trash();
    Tile t1 = new ColoredTile(ColoredTile.Colors.BLUE);
    Tile t2 = new ColoredTile(ColoredTile.Colors.RED);
    Tile t3 = new ColoredTile(ColoredTile.Colors.GREEN);
    trash.add(t1);
    trash.add(t2);
    trash.add(t3);
    LinkedList<Tile> tiles = new LinkedList<Tile>();
    tiles.add(t1); tiles.add(t2); tiles.add(t3);
    Bag bag = new Bag();
    bag.refill(trash);
    assertTrue(trash.isEmpty());
    assertTrue(bag.tiles.containsAll(tiles));

  }
}
