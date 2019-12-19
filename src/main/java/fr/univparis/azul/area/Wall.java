package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

import java.util.ArrayList;

public class Wall extends PlayerArea implements IndexedArea {

    private ColoredTile[][] tiles;

    public Wall() {
	tiles = new ColoredTile[5][5];
    }

    @Override
    public void add(int index, Tile tile){
	if( index < 0 || index >= 5 )
	    throw new IllegalArgumentException("Invalid index : " + index);
	if (! (tile instanceof ColoredTile))
	    throw new IllegalArgumentException("Wall only accept ColoredTile");
	ColoredTile ct = (ColoredTile)tile;

    }

    private int rowSize(int row) {
	int size = 0;
	for(int i=0; i < 5; i++) {
	    if( tiles[row][i] != null )
		size++;
	}
	return size;
    }

    @Override
    public int size() {
	int size = 0;
	for (int i=0; i < 5; i++) {
	    size += rowSize(i);
	}
	return size;
    }

    @Override
    public boolean isEmpty() {	
	return size() == 0;
    }

    public boolean hasFullRow() {
	for(int i=0; i < 5; i++) {
	    if( rowSize(i) == 5 )
		return true;
	}
	return false;
    } 

    private int columnPos(int row, ColoredTile.Colors color ) {
	int c = -1;
	// ordre des couleurs pour la première ligne
	switch( color) {
	case BLUE:
	    c = 0;
	    break;
	case YELLOW:
	    c = 1;
	    break;
	case RED:
	    c = 2;
	    break;
	case BLACK:
	    c = 3;
	    break;
	case GREEN:
	    c = 4;
	    break;
	}

	// les lignes suivantes sont juste des cycles de la première
	c = ( c + row ) % 5;

	return c;
    }

}
