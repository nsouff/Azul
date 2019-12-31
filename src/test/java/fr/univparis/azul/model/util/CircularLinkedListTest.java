package fr.univparis.azul.model.util;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import fr.univparis.azul.model.HumanPlayer;
import fr.univparis.azul.model.Player;

public class CircularLinkedListTest {

  @Test
  public void addTest() {
    CircularLinkedList<Player> c = new CircularLinkedList<Player>();
    Player p1 = new HumanPlayer("p1", null);
    Player p2 = new HumanPlayer("p2", null);
    Player p3 = new HumanPlayer("p3", null);
    c.add(p1);
    c.add(p2);
    c.add(p3);
    Iterator<Player> i = c.iterator();

    assertEquals(p1, i.next());
    assertEquals(p2, i.next());
    assertEquals(p3, i.next());
    assertEquals(p1, i.next());
  }


  @Test
  public void setFirstTest() {
    CircularLinkedList<Player> c = new CircularLinkedList<Player>();
    Player p1 = new HumanPlayer("p1", null);
    Player p2 = new HumanPlayer("p2", null);
    Player p3 = new HumanPlayer("p3", null);
    c.add(p1);
    c.add(p2);
    c.add(p3);
    c.setFirst(p2);
    Iterator<Player> i = c.iterator();

    assertEquals(p2, i.next());
    assertEquals(p3, i.next());
    assertEquals(p1, i.next());
    assertEquals(p2, i.next());
  }
}
