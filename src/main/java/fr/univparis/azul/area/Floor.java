package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.ArrayList;

public class Floor extends PlayerArea implements UnindexedArea {

  private ArrayList<Tile> tiles;

  @Override
  public Tile removeTile(Tile tile) {return null;}
  
  @Override
  public int size() {return -1;}
  
  @Override
  public boolean isEmpty(){return true;}
  
  @Override
  public boolean moveTilesTo(Area area) {return false;}
  
  @Override
  public void add(Tile tile) {}


}
