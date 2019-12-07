package fr.univparis.azul.area;
import org.junit.Test;
import static org.junit.Assert.*;
import fr.univparis.azul.tile.*;
import java.util.List;
public class CommonAreaTest {

  @Test
  public void getSameColorTileTest() {
    Factory f = new Factory();
    ColoredTile.Colors blue = ColoredTile.Colors.BLUE;
    for (int i = 0; i < 3; i++) f.add(new ColoredTile(blue));
    f.add(new ColoredTile(ColoredTile.Colors.GREEN));
    List<ColoredTile> list = f.getSameColorTile(blue);
    for (ColoredTile t : list) assertEquals(blue, t.getColor());
    assertEquals(ColoredTile.Colors.GREEN, ((ColoredTile)f.tiles.get(0)).getColor());
  }
}
