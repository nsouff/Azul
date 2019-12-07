package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.*;

public class Factory extends CommonArea {

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

    public void fill(Bag bag) {
      if (!isEmpty()) throw new NotEmptyFactoryException();
      for (int i = 0; i < size; i++) add(bag.poll());
    }

}
