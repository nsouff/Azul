package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.ArrayList;

public class PatternArea extends PlayerArea implements IndexedArea {

    private ArrayList<ArrayList<ColoredTile>> tiles;

    public PatternArea() {
      tiles = new ArrayList<ArrayList<ColoredTile>>(5);
      for (int i = 1; i < 6; i++) {
        tiles.add(new ArrayList<ColoredTile>(i));
      }
    }

    public static class PatternAreaIndexOutOfBoundsException extends ArrayIndexOutOfBoundsException {}

    public ColoredTile getColoredTile(int i, int j) {
      return tiles.get(i).get(j);
    }

    @Override
    public void add(int index, Tile tile){
      if (! (tile instanceof ColoredTile) || index > 4 || index < 0) throw new IllegalArgumentException();
      ArrayList<ColoredTile> list = tiles.get(index);
      if (list.isEmpty()) list.add((ColoredTile) tile);
      else if (list.get(0).getColor() == ( (ColoredTile) tile).getColor()) {
        if (isFull(index)) throw new PatternAreaIndexOutOfBoundsException();
        else list.add((ColoredTile) tile);
      }
      else throw new IllegalArgumentException();
    }

    public boolean isFull(int index) {
      return (tiles.get(index).size() == index + 1);
    }


    @Override
    public int size() {
      int count = 0;
      for (ArrayList<ColoredTile> list : tiles) count += list.size();
      return count;
    }

    @Override
    public boolean isEmpty() {
      boolean res = true;
      for (ArrayList<ColoredTile> list : tiles) res &= list.isEmpty();
      return res;
    }

    @Override
    public boolean moveTilesTo(Area area) {return false;}

}
