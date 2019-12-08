package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.*;

import fr.univparis.azul.util.SortedLinkedList;

public class CenterArea extends CommonArea {

  public CenterArea() {
    Comparator<Tile> c = new Comparator<Tile>() {
      public int compare(Tile t1, Tile t2) {
        if (t1 instanceof SpecialTile) {
          if (t2 instanceof SpecialTile) return 0;
          else return -1;
        }
        else if (t2 instanceof SpecialTile) return 1;
        else {
          ColoredTile ct1 = (ColoredTile) t1;
          ColoredTile ct2 = (ColoredTile) t2;
          return ct1.getColor().ordinal() - ct2.getColor().ordinal();
        }
      }
    };
    tiles = new SortedLinkedList<Tile>(c);
  }


  @Override
  public int size() {return tiles.size();}

  @Override
  public boolean isEmpty(){return tiles.isEmpty();}

  @Override
  public void add(Tile tile) {
    tiles.add(tile);
  }


}
