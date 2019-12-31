package fr.univparis.azul.model.area;

import fr.univparis.azul.model.tile.Tile;

public interface IndexedArea extends Area {

  public void add(int index, Tile tile);

}
