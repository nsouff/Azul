package fr.univparis.azul.tile;
public class ColoredTile {
  enum Colors {
    BLUE,
    RED,
    GREEN,
    BLACK,
    YELLOW;
  }

  private Colors color;
  public Colors getColor() {return color;}

}
