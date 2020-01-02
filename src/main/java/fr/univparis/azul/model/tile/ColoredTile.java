package fr.univparis.azul.model.tile;


public class ColoredTile extends Tile{
  public enum Colors {
    BLUE,
    RED,
    GREEN,
    BLACK,
    YELLOW;
  }

  public static Colors StringToColor(String s) {
    s = s.toLowerCase();
    switch (s) {
      case "blue" : return Colors.BLUE;
      case "red" : return Colors.RED;
      case "green" : return Colors.GREEN;
      case "black" : return Colors.BLACK;
      case "yellow" : return Colors.YELLOW;
      default : throw new IllegalArgumentException();
    }
  }

  private Colors color;

  public ColoredTile(Colors c) {
    color = c;
  }

  public Colors getColor() {return color;}

}
