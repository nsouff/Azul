package fr.univparis.azul.model.area;

import java.util.Collections;
import java.util.LinkedList;

import fr.univparis.azul.model.area.Factory.NotEmptyFactoryException;
import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.Tile;

public class Bag extends OffSideArea {

	public Bag() {
		super();
		for (ColoredTile.Colors c : ColoredTile.Colors.values()) {
			addTiles(20, c);
		}
	}

	private void addTiles(int n, ColoredTile.Colors c) {
		for (int i = 0; i < n; i++) add(new ColoredTile(c));
	}

	private class BagEmptyException extends IllegalStateException{}

	public void fill(Factory f) {
		if (!f.isEmpty()) 
			throw new NotEmptyFactoryException();
		for (int i = 0; i < f.size; i++) 
			f.add( poll() );
	}

	public Tile poll() {
		if (isEmpty()) throw new BagEmptyException();
		return tiles.poll();
	}

	public void shuffle() {
		Collections.shuffle( tiles );
	}
}
