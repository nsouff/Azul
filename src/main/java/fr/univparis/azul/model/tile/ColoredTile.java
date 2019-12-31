package fr.univparis.azul.model.tile;


public class ColoredTile extends Tile{
  public enum Colors {
    BLUE,
    RED,
    GREEN,
    BLACK,
    YELLOW;
  }

  private Colors color;

  public ColoredTile(Colors c) {
    color = c;
  }

  public Colors getColor() {return color;}

}
