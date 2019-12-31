package fr.univparis.azul.model.area;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.tile.Tile;



public class PatternAreaTest {


  @Test
  public void addTest() {
    PatternArea p = new PatternArea();
    ColoredTile t1 = new ColoredTile(ColoredTile.Colors.BLUE);
    ColoredTile t2 = new ColoredTile(ColoredTile.Colors.RED);
    ColoredTile t3 = new ColoredTile(ColoredTile.Colors.RED);
    p.add(0, t1);
    p.add(1, t2);
    p.add(1, t3);
    assertEquals(t1, p.getColoredTile(0, 0));
    assertEquals(t2, p.getColoredTile(1, 0));
    assertEquals(t3, p.getColoredTile(1, 1));
  }

  @Test(expected = PatternArea.PatternAreaIndexOutOfBoundsException.class)
  public void addOutOfBoundsTest() {
    PatternArea p = new PatternArea();
    p.add(0, new ColoredTile(ColoredTile.Colors.BLUE));
    p.add(0, new ColoredTile(ColoredTile.Colors.BLUE));
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannotAddSpecialTileTest() {
    Tile bad = new FirstTile();
    new PatternArea().add(0, bad);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannotAddDifferentColorTest() {
    PatternArea p = new PatternArea();
    p.add(1, new ColoredTile(ColoredTile.Colors.BLUE));
    p.add(1, new ColoredTile(ColoredTile.Colors.RED));
  }

  @Test
  public void sizeTest() {
    PatternArea p = new PatternArea();
    p.add(0, new ColoredTile(ColoredTile.Colors.BLUE));
    p.add(1, new ColoredTile(ColoredTile.Colors.BLUE));
    p.add(1, new ColoredTile(ColoredTile.Colors.BLUE));
    assertEquals(3, p.size());
  }

  @Test
  public void isEmptyTest() {
    PatternArea p = new PatternArea();
    assertTrue(p.isEmpty());
    p.add(3, new ColoredTile(ColoredTile.Colors.RED));
    assertFalse(p.isEmpty());
  }

  @Test
  public void isFullTest() {
    ColoredTile.Colors bl = ColoredTile.Colors.BLUE;
    PatternArea p = new PatternArea();
    p.add(0, new ColoredTile(bl));
    p.add(2, new ColoredTile(bl));
    p.add(2, new ColoredTile(bl));
    assertFalse(p.isFull(2));
    assertTrue(p.isFull(0));
    p.add(2, new ColoredTile(bl));
    assertTrue(p.isFull(2));
  }



}
