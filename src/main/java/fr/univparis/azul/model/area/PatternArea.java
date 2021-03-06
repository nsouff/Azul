package fr.univparis.azul.model.area;

import java.util.ArrayList;

import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.Tile;

public class PatternArea implements IndexedArea {

    private ArrayList<ArrayList<ColoredTile>> tiles;

    public ArrayList<ArrayList<ColoredTile>> getTiles(){
    	return tiles;
    }
    
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

    public boolean isEmpty(int index) {return tiles.get(index).isEmpty();}


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

    public ColoredTile takeOneTile(int index) {
	return tiles.get(index).remove(0);
    }

    public void throwRow(Trash trash, int index) {
	for(int i=0; i < tiles.get(index).size(); i++ ) {
	    Tile t = tiles.get(index).remove(i);
	    trash.add(t);
	}
    }
}
