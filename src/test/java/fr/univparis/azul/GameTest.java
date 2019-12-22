package fr.univparis.azul;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.net.*;

public class GameTest {

  @Test
  public void constructorTest() throws Exception{
    URL url = this.getClass().getResource("/config.json");
    File config = new File(url.getFile());
    Game g = new Game(config);
    assertEquals(4, g.getNbPlayers());
  }

}
