package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.*;

public class Factory extends CommonArea {
    /* FIXME: Maybe it should be implements Iterable */
    private static final int size = 4;
    {
      tiles = new ArrayList<Tile>(size);
    }

    protected static class FullFactoryException extends IllegalStateException {}

    protected static class NotEmptyFactoryException extends IllegalStateException {}

    @Override
    public int size() {return tiles.size();}

    @Override
    public boolean isEmpty(){return tiles.isEmpty();}

    @Override
    public void add(Tile tile) {
      if (! (tile instanceof ColoredTile)) throw new IllegalArgumentException("tile must be a ColoredTile in the Factory");
      if (size() >= size) throw new FullFactoryException();
      tiles.add((ColoredTile) tile);
    }

    public List<Tile> getTiles() {return tiles;}

    public void fill(Bag bag) {
      if (!isEmpty()) throw new NotEmptyFactoryException();
      for (int i = 0; i < size; i++) add(bag.poll());
    }

    public void moveTilesTo(UnindexedArea area, ColoredTile ct, CenterArea ca) {
      for (Tile t : tiles) {
        if (((ColoredTile)t).getColor() == ct.getColor()) {
          area.add(t);
        }
        else ca.add(t);
      }
      tiles = new ArrayList<Tile>(size);
    }

    public void moveTilesTo(IndexedArea area, ColoredTile ct, CenterArea ca, int index) {
      for (Tile t : tiles) {
        if (((ColoredTile)t).getColor() == ct.getColor())
          area.add(index, t);
        else ca.add(t);
      }
      tiles = new ArrayList<Tile>(size);
    }

    public void emptyIt() {tiles = new ArrayList<Tile>(size);}

}
