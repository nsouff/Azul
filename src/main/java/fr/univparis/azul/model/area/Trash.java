package fr.univparis.azul.model.area;

import java.util.Collections;
import java.util.LinkedList;

import fr.univparis.azul.model.tile.Tile;

public class Trash extends OffSideArea {
	public void fill(Bag bag) {
		bag.tiles = this.tiles;
		this.tiles = new LinkedList<Tile>();
		Collections.shuffle(bag.tiles);
	}
}
