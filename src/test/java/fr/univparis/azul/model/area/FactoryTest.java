package fr.univparis.azul.model.area;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;

public class FactoryTest {

  @Test
  public void addTest() {
    ColoredTile t = new ColoredTile(ColoredTile.Colors.BLUE);
    Factory f = new Factory();
    f.add(t);
    assertEquals(t, f.tiles.get(0));
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
    f.fill(bag);
    assertEquals(t1, f.tiles.get(0));
    assertEquals(t2, f.tiles.get(1));
    assertEquals(t3, f.tiles.get(2));
    assertEquals(t4, f.tiles.get(3));
  }

  @Test(expected = Factory.NotEmptyFactoryException.class)
  public void notEmptyExceptionTest() {
    Factory f = new Factory();
    f.add(new ColoredTile(ColoredTile.Colors.BLUE));
    f.fill(new Bag());
  }

  @Test(expected = Factory.FullFactoryException.class)
  public void FullFactoryExceptionTest() {
    Factory f = new Factory();
    for (int i = 0; i < 5; i++) f.add(new ColoredTile(ColoredTile.Colors.BLUE));
  }

  @Test(expected = IllegalArgumentException.class)
  public void notColoredTileExceptionTest() {
    new Factory().add(new FirstTile());
  }

  @Test
  public void moveTilesToTest() {
    Factory f = new Factory();
    ColoredTile.Colors bl = ColoredTile.Colors.BLUE;
    f.add(new ColoredTile(bl));
    f.add(new ColoredTile(bl));
    f.add(new ColoredTile(ColoredTile.Colors.GREEN));
    f.add(new ColoredTile(bl));
    CenterArea c = new CenterArea();
    Floor floor = new Floor();
    f.moveTilesTo(floor, new ColoredTile(bl), c);
    assertEquals(0, f.size());
    assertEquals(3, floor.size());
    assertEquals(1, c.size());
    f.add(new ColoredTile(bl));
    f.add(new ColoredTile(bl));
    f.add(new ColoredTile(ColoredTile.Colors.GREEN));
    f.add(new ColoredTile(bl));
    PatternArea p = new PatternArea();
    f.moveTilesTo(p, new ColoredTile(ColoredTile.Colors.BLUE), c, 4);
    assertEquals(3, p.size());
    assertEquals(0, f.size());
    assertEquals(2, c.size());
  }
}
