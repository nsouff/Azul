package fr.univparis.azul.area;

import java.util.*;

import fr.univparis.azul.tile.*;

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

  public void refill(Trash trash) {
    tiles = trash.getTiles();
    trash.tiles = new LinkedList<Tile>();
    Collections.shuffle(tiles);
  }

  public Tile poll() {
    if (isEmpty()) throw new BagEmptyException();
    return tiles.poll();
  }

}
