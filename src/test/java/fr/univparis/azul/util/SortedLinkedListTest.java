package fr.univparis.azul.util;

import static org.junit.Assert.*;

import fr.univparis.azul.tile.ColoredTile;
import fr.univparis.azul.util.SortedLinkedList;

import java.util.*;
import org.junit.Test;

public class SortedLinkedListTest {

    @Test
    public void addTest() {
	SortedLinkedList<ColoredTile> list = new SortedLinkedList( new Comparator<ColoredTile>() {
		public int compare(ColoredTile t1, ColoredTile t2) {
		    return t1.getColor().ordinal() - t2.getColor().ordinal(); // BLUE < RED < GREEN < BLACK < YELLOW
		}
	    });

	ColoredTile t1 = new ColoredTile(ColoredTile.Colors.BLUE);
	ColoredTile t2 = new ColoredTile(ColoredTile.Colors.BLUE);
	ColoredTile t3 = new ColoredTile(ColoredTile.Colors.RED);
	ColoredTile t4 = new ColoredTile(ColoredTile.Colors.GREEN);

	list.add(t1);
	list.add(t2);
	list.add(t3);
	list.add(t4);

	assertSame(list.get(0), t2);
	assertSame(list.get(1), t1);
	assertSame(list.get(2), t3);
	assertSame(list.get(3), t4);
    }

    @Test
    public void isEmptyTest() {
	SortedLinkedList<ColoredTile> list = new SortedLinkedList( new Comparator<ColoredTile>() {
		public int compare(ColoredTile t1, ColoredTile t2) {
		    return t1.getColor().ordinal() - t2.getColor().ordinal(); // BLUE < RED < GREEN < BLACK < YELLOW
		}
	    });
	
	assertTrue(list.isEmpty());

	list.add(new ColoredTile(ColoredTile.Colors.BLUE));

	assertFalse(list.isEmpty());
    }
}
