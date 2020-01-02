package fr.univparis.azul.view.console;
import fr.univparis.azul.view.InputManager;
import fr.univparis.azul.model.area.Factory;
import java.util.Scanner;
import fr.univparis.azul.model.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fr.univparis.azul.model.tile.ColoredTile;
public class ConsoleInputManager extends InputManager {
  Scanner sc;
  public ConsoleInputManager() {
    sc = new Scanner(System.in);
  }

  public void read(Player player) {

    String[] pos1 = {"C", "F"};
    String s = question("Do you want to choose from the center or a facotry? (C/F)", pos1);
    if (s == "C") {
      boolean again1;
      do {
        again1 = false;
        String[] pos2 = {"blue", "red", "green", "yellow", "black", "first"};
        s = question("What type of tile do you want to move? (blue, red, yellow, green, black, first)", pos2);
        if (s == "first") try {
          player.moveFirstTileFromCenterToFloor();
        } catch(IllegalStateException e) {System.out.println("Error there is no first tile"); again1 = true;}
        else {
          ColoredTile.Colors c = ColoredTile.StringToColor(s);
          String[] pos3 = {"F", "P"};
          s = question("Where to ? Floor (F) or PatternArea (P)", pos3);
          if (s == "P") {
            boolean again2;
            do {
              again2 = false;
              String[] pos4 = {"0", "1", "2", "3", "4"};
              int index = Integer.parseInt(question("At what position do you want to put it ? (0, 1, 2, 3 or 4)", pos4));
              try {
                player.moveFromCenterAreatoPattern(c, index);
              } catch(IllegalArgumentException e) {System.out.println("Error the color from the row must be the same color"); again2 = true;}
            } while (again2);
          }
          else player.moveFromCenterToFloor(c);

        }
      } while (again1);
    }

    else {
      String[] pos2 = new String[player.gameBoard.factories.size()];
      for (int i = 0; i < pos2.length; i++) {
        pos2[i] = Integer.toString(i);
      }
      Factory f = player.gameBoard.factories.get(Integer.parseInt(question("What facotry do you want to use ? insert a number from 0 to " + (pos2.length - 1), pos2)));
      String[] pos3 = {"blue", "red", "black", "green", "yellow"};
      ColoredTile.Colors c;
      do {
        s = question("What type of tile do you want to move ? (blue, red, yellow, green, black, first)", pos3);
        c = ColoredTile.StringToColor(s);
        if (! f.containsColor(c)) System.out.println("Error the color must be in the chosen facotry");
      } while (! f.containsColor(c));
      String[] pos4 = {"F", "P"};
      s = question("Where do you want to put the tile(s)? Floor (F) or the pattern area (P)", pos4);
      if (s == "F") player.moveFromFactoryToFloor(c, f);
      else {
        boolean again;
        String[] pos5 = {"0", "1", "2", "3", "4"};
        do {
          again = false;
          try {
            player.moveFromFactoryToPattern(c, f, Integer.parseInt(question("At what row of the patternArea? 0, 1, 2, 3 or 4", pos5)));
          } catch(IllegalArgumentException e) {again = true; System.out.println("Error, you must choose a row with the same color");}
        } while (again);
      }
    }

  }

  private String question(String question, String[] possible) {
    List<String> pos = Arrays.asList(possible);
    String s = "";
    String errorMessage = "Error the answer must be ";
    for (int i = 0; i < pos.size(); i++) {
      if (i == pos.size() - 1) errorMessage += "or " + pos.get(i) + ".";
      else errorMessage += pos.get(i) + ", ";
    }
    while (! pos.contains(s)) {
      System.out.println(question);
      s = sc.nextLine();
      if (! pos.contains(s)) System.out.println(errorMessage);
    }
    return s;
  }
}
