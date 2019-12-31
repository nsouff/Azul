package fr.univparis.azul.model.area;

import java.util.Comparator;
import java.util.List;

import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.SpecialTile;
import fr.univparis.azul.model.tile.Tile;
import fr.univparis.azul.model.util.SortedLinkedList;

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

  public List<Tile> getTiles() {
    return tiles;
  }


}
