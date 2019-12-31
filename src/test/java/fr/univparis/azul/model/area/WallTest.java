package fr.univparis.azul.model.area;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import fr.univparis.azul.model.tile.ColoredTile;
public class WallTest {

  @Test
  public void addTest() {
    Wall wall = new Wall();
    wall.add(0, new ColoredTile(ColoredTile.Colors.BLUE));
    wall.add(2, new ColoredTile(ColoredTile.Colors.RED));
    assertEquals(2, wall.size());
    assertEquals(ColoredTile.Colors.BLUE, wall.get(0, 0).getColor());
    assertEquals(ColoredTile.Colors.RED, wall.get(2, 4).getColor());
    assertNull(wall.get(0,1));
  }

  @Test(expected = IllegalStateException.class)
  public void addArgumentExceptionTest() {
    Wall wall = new Wall();
    wall.add(0, new ColoredTile(ColoredTile.Colors.BLUE));
    wall.add(0, new ColoredTile(ColoredTile.Colors.BLUE));
  }
}
