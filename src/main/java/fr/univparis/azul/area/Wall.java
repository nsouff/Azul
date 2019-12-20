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
    // if (rowSize(index) == 5) throw new IllegalArgumentException("The row " + index + " is full");
    ColoredTile ct = (ColoredTile)tile;
    int j = columnPos(index, ct.getColor());
    if (tiles[index][j] != null) throw new IllegalStateException("There  is already a " + ct.getColor() + " in row " + index);
    tiles[index][j] = ct;
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

  // FIXME : Refactoring needed
  public int nbAdjacentTile(int row, ColoredTile.Colors color) {
    int adj = 0;

    int column = columnPos(row, color);

    // on regarde à droite
    int i = column +1;
    while( i < 5 && tiles[row][i] != null ) {
      adj++;
      i++;
    }

    // en haut
    i = row - 1;
    while( i >= 0 && tiles[i][column] != null ) {
      adj++;
      i--;
    }

    // à gauche
    i = column - 1;
    while( i >= 0 && tiles[row][i] != null ) {
      adj++;
      i--;
    }

    // en bas
    i = row + 1;
    while( i < 5 && tiles[i][column] != null ) {
      adj++;
      i++;
    }
    return adj;
  }

  public ColoredTile get(int i, int j) {
    if (i < 5 && i > -1 && j < 5 && j > -1) return tiles[i][j];
    throw new IllegalArgumentException();
  }
}
