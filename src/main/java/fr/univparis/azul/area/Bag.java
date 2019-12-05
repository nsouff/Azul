package fr.univparis.azul.area;

import java.util.*;

import fr.univparis.azul.tile.*;

public class Bag extends OffSideArea {

  public void refill(Trash trash) {
    tiles = trash.getTiles();
    trash.tiles = new LinkedList<Tile>();
    Collections.shuffle(tiles);
  }

}
