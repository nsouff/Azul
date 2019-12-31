package fr.univparis.azul.model.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import fr.univparis.azul.model.tile.ColoredTile;

public class SortedLinkedListTest {

    private static SortedLinkedList<ColoredTile> createList() {
        return new SortedLinkedList( new Comparator<ColoredTile>() {
		public int compare(ColoredTile t1, ColoredTile t2) {
		    return t1.getColor().ordinal() - t2.getColor().ordinal(); // BLUE < RED < GREEN < BLACK < YELLOW
		}
	    });
    }
    
    @Test
    public void addGetTest() {
	SortedLinkedList<ColoredTile> list = createList();
	
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
	SortedLinkedList<ColoredTile> list = createList();
	
	assertTrue(list.isEmpty());

	list.add(new ColoredTile(ColoredTile.Colors.BLUE));

	assertFalse(list.isEmpty());
    }
}
