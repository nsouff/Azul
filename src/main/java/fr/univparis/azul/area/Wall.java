package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.ArrayList;

public class Wall extends PlayerArea implements IndexedArea {

  private ArrayList<ArrayList<Tile>> tiles;

  @Override
  public void add(int index, Tile tile){}

  @Override
  public Tile removeTile(Tile tile) {return null;}

  @Override
  public int size() {return -1;}

  @Override
  public boolean isEmpty() {return true;}

  @Override
  public boolean moveTilesTo(Area area) {return false;}




}
