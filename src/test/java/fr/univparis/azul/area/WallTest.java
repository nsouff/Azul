package fr.univparis.azul.area;
import org.junit.*;
import static org.junit.Assert.*;
import fr.univparis.azul.tile.*;
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
