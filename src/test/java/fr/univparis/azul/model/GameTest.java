package fr.univparis.azul.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class GameTest {

  @Test
  public void constructorTest() throws Exception{
    URL url = this.getClass().getResource("/config.json");
    File config = new File(url.getFile());
    Game g = new Game(config);
    assertEquals(4, g.getNbPlayers());
  }

}
