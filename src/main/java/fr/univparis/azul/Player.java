package fr.univparis.azul;

import fr.univparis.azul.area.*;
import fr.univparis.azul.tile.*;
import java.util.*;

public abstract class Player {
  public class Stat { // peut être protected
    private int totalScore = 0;
    private LinkedList<Integer> scorePerRound = new LinkedList<Integer>();
    private String name;
    private boolean isHuman;

    public int getTotalScore() {
      return totalScore;
    }

    public void addRoundScore(int score) {
      scorePerRound.add( Integer.valueOf(score) );
      totalScore += score;
    }
  }

  public class PlayerBoard { // peut être protected
    Wall playerWall;
    Floor playerFloor;
    PatternArea playerPatternArea;

    public PlayerBoard() {
      playerWall = new Wall();
      playerFloor = new Floor();
      playerPatternArea = new PatternArea();
    }

    public Wall getWall() {
      return playerWall;
    }
  }

  public Player(String name, Game.GameBoard g) {
    stats = new Stat();
    stats.name = name;
    playerBoard = new PlayerBoard();
    gameBoard = g;
  }

  Stat stats;
  PlayerBoard playerBoard;
  Game.GameBoard gameBoard;

  public PlayerBoard getPlayerBoard() {
    return playerBoard;
  }

  public Stat getStats() {
    return stats;
  }

  public void moveFromFactoryToPattern(ColoredTile.Colors c, Factory f, int index) {
    if (! playerBoard.playerPatternArea.isEmpty(index) && playerBoard.playerPatternArea.getColoredTile(index, 0).getColor() != c)
      throw new IllegalArgumentException("Tiles must be in the same color for each rows of the PatternArea");
    List<Tile> tiles = f.getTiles();
    for (Tile t : tiles) {
      if (((ColoredTile)t).getColor() == c) {
         if (playerBoard.playerPatternArea.isFull(index)) {
           if (playerBoard.playerFloor.isFull()) gameBoard.trash.add(t);
           else playerBoard.playerFloor.add(t);
         }
         else playerBoard.playerPatternArea.add(index, t);
      }
      else gameBoard.center.add(t);
    }
    f.emptyIt();
  }

  public void moveFromCenterAreatoPattern(ColoredTile.Colors c, int index) {
    if (! playerBoard.playerPatternArea.isEmpty(index) && playerBoard.playerPatternArea.getColoredTile(index, 0).getColor() != c) {
      throw new IllegalArgumentException("Tiles must be in the same color for each rows of the PatternArea");
    }

    List<Tile> tiles = gameBoard.center.getTiles();
    for (Tile t : tiles) {
      if (t instanceof ColoredTile) {
        if (((ColoredTile)t).getColor() == c ) {
          tiles.remove(t);
          ColoredTile ct = (ColoredTile) t;
          if (ct.getColor() == c) {
            if (playerBoard.playerFloor.isFull()) gameBoard.trash.add(t);
            else playerBoard.playerFloor.add(t);
          }
          else playerBoard.playerFloor.add(t);
        }
        else gameBoard.center.add(t);
      }
      else playerBoard.playerFloor.add(t);
    }
  }

}
