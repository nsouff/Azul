package fr.univparis.azul;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.net.*;
import fr.univparis.azul.area.*;
import fr.univparis.azul.tile.*;

public class PlayerTest {

  @Test
  public void moveFromFactoryToPatternTest() throws Exception{
    Game g = new Game(new File(this.getClass().getResource("/config.json").getFile()));
    Player p = g.getPlayer("1");
    Factory f = new Factory();
    f.add(new ColoredTile(ColoredTile.Colors.BLUE));
    f.add(new ColoredTile(ColoredTile.Colors.BLUE));
    f.add(new ColoredTile(ColoredTile.Colors.BLUE));
    f.add(new ColoredTile(ColoredTile.Colors.RED));

    p.moveFromFactoryToPattern(ColoredTile.Colors.BLUE, f, 0);
    assertEquals(2, p.playerBoard.playerFloor.size());
    assertEquals(1, g.getBoard().center.size());
    assertEquals(1, p.playerBoard.playerPatternArea.size());
    assertTrue(f.isEmpty());
    p.playerBoard.playerFloor.add(new ColoredTile(ColoredTile.Colors.BLUE));
    p.playerBoard.playerFloor.add(new ColoredTile(ColoredTile.Colors.BLUE));
    p.playerBoard.playerFloor.add(new ColoredTile(ColoredTile.Colors.BLUE));
    p.playerBoard.playerFloor.add(new ColoredTile(ColoredTile.Colors.BLUE));
    
    for (int i = 0; i < 4; i++) f.add(new ColoredTile(ColoredTile.Colors.BLUE));

    p.moveFromFactoryToPattern(ColoredTile.Colors.BLUE, f, 1);
    assertEquals(1, g.getBoard().trash.size());
    assertEquals(3, p.playerBoard.playerPatternArea.size());
    assertEquals(7, p.playerBoard.playerFloor.size());
    assertEquals(1, g.getBoard().center.size());

  }

}
