package fr.univparis.azul.area;
import org.junit.Test;
import static org.junit.Assert.*;
import fr.univparis.azul.tile.*;
public class CenterAreaTest {

  @Test
  public void constructorTest() {
    CenterArea c = new CenterArea();
    ColoredTile.Colors blue = ColoredTile.Colors.BLUE;
    ColoredTile.Colors red = ColoredTile.Colors.RED;
    ColoredTile.Colors green = ColoredTile.Colors.GREEN;
    ColoredTile.Colors black = ColoredTile.Colors.BLACK;
    ColoredTile.Colors yellow = ColoredTile.Colors.YELLOW;

    c.add(new FirstTile());
    c.add(new ColoredTile(blue));
    c.add(new ColoredTile(green));
    c.add(new ColoredTile(black));
    c.add(new ColoredTile(yellow));
    c.add(new ColoredTile(red));
    c.add(new FirstTile());
    c.add(new ColoredTile(yellow));
    c.add(new ColoredTile(black));
    c.add(new FirstTile());
    c.add(new ColoredTile(yellow));
    c.add(new ColoredTile(blue));
    c.add(new ColoredTile(black));
    c.add(new ColoredTile(green));
    c.add(new ColoredTile(black));
    c.add(new FirstTile());
    c.add(new ColoredTile(red));

    assertEquals(FirstTile.class, c.tiles.get(0).getClass());
    assertEquals(FirstTile.class, c.tiles.get(1).getClass());
    assertEquals(FirstTile.class, c.tiles.get(2).getClass());
    assertEquals(FirstTile.class, c.tiles.get(3).getClass());
    assertEquals(blue,( (ColoredTile)c.tiles.get(4)).getColor() );
    assertEquals(blue, ( (ColoredTile)c.tiles.get(5)).getColor() );
    assertEquals(red, ( (ColoredTile)c.tiles.get(6)).getColor() );
    assertEquals(red, ( (ColoredTile)c.tiles.get(7)).getColor() );
    assertEquals(green, ( (ColoredTile)c.tiles.get(8)).getColor() );
    assertEquals(green, ( (ColoredTile)c.tiles.get(9)).getColor() );
    assertEquals(black, ( (ColoredTile)c.tiles.get(10)).getColor() );
    assertEquals(black, ( (ColoredTile)c.tiles.get(11)).getColor() );
    assertEquals(black, ( (ColoredTile)c.tiles.get(12)).getColor() );
    assertEquals(black, ( (ColoredTile)c.tiles.get(13)).getColor() );
    assertEquals(yellow, ( (ColoredTile)c.tiles.get(14)).getColor() );
    assertEquals(yellow, ( (ColoredTile)c.tiles.get(15)).getColor() );
    assertEquals(yellow, ( (ColoredTile)c.tiles.get(16)).getColor() );

    assertEquals(17, c.size());
  }
}
