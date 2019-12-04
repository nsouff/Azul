package fr.univparis.azul.area;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univparis.azul.tile.*;

public class PatternAreaTest {

  //@Test(expected = NullPointerException.class)
  //public void notAddingSpecialTileTest() {
  //  PatternArea p = new PatternArea();
  //  Tile bad = new FirstTile();
  //  p.add(0, bad);
  //  System.out.println(p.getColoredTile(0, 0));
  //}

  @Test(expected = IllegalArgumentException.class)
  public void addTest() {
    PatternArea p = new PatternArea();
    ColoredTile t1 = new ColoredTile(ColoredTile.Colors.BLUE);
    ColoredTile t2 = new ColoredTile(ColoredTile.Colors.BLUE);
    Tile bad = new FirstTile();
    p.add(0, t1);
    p.add(0, t2);
    assertEquals(p.getColoredTile(0, 0), t1);
    assertEquals(p.getColoredTile(0, 1), t2);
    p.add(2, bad);
  }

}
