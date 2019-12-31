package fr.univparis.azul.model.area;

import java.util.LinkedList;

import fr.univparis.azul.model.tile.Tile;

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
