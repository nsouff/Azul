package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.LinkedList;

public class Floor extends PlayerArea implements UnindexedArea {

  private LinkedList<Tile> tiles;

  public Floor() {
    tiles = new LinkedList<Tile>();
  }

  @Override
  public int size() {return tiles.size();}

  @Override
  public boolean isEmpty(){return tiles.isEmpty();}

  @Override
  public void add(Tile tile) {tiles.add(tile);}

  /* FIXME: maxe size of floor should be configurable */
  public boolean isFull() {
    return tiles.size() == 7;
  }


}
