package fr.univparis.azul.util;

import org.junit.Test;

import static org.junit.Assert.*;

import fr.univparis.azul.*;

import java.util.Iterator;

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
