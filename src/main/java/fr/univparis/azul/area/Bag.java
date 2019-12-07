package fr.univparis.azul.area;

import java.util.*;

import fr.univparis.azul.tile.*;

public class Bag extends OffSideArea {

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
