package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.ArrayList;

public class Wall extends PlayerArea implements IndexedArea {

  private ArrayList<ArrayList<ColoredTile>> tiles;

  public Wall() {
    tiles = new ArrayList<ArrayList<ColoredTile>>(5);
    for (int i = 0; i < 5; i++) {tiles.add(new ArrayList<ColoredTile>(5));}
  }

  @Override
  public void add(int index, Tile tile){
    if (! (tile instanceof ColoredTile)) throw new IllegalArgumentException("Wall only accept ColoredTile");
    tiles.get(index).add((ColoredTile) tile);
  }


  @Override
  public int size() {
    int size = 0;
    for (ArrayList<ColoredTile> list : tiles) {
      size += list.size();
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    for (int i = 0; i < 5; i++) if (!tiles.get(i).isEmpty()) return false;
    return true;
  }

  public boolean hasRow() {
    for (ArrayList<ColoredTile> array : tiles ) {
      if( array.size() == 5 )
        return true;
    }
    return false;
  }

}
