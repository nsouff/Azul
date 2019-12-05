package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.LinkedList;

public abstract class OffSideArea implements UnindexedArea {

  protected LinkedList<Tile> tiles;

  public OffSideArea() {tiles = new LinkedList<Tile>();}

  @Override
  public int size() {return tiles.size();}

  @Override
  public boolean isEmpty(){return tiles.isEmpty();}

  @Override
  public void add(Tile tile) {
    tiles.add(tile);
  }


  protected LinkedList<Tile> getTiles() {return tiles;}
}
