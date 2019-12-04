package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.*;

public class Factory extends CommonArea {

    private static final int size = 4;
    {
      tiles = new ArrayList<Tile>(size);
    }
    @Override
    public int size() {return -1;}

    @Override
    public boolean isEmpty(){return true;}

    @Override
    public boolean moveTilesTo(Area area) {return false;}

    @Override
    public void add(Tile tile) {}

}
