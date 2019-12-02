package fr.univparis.azul;

import java.io.File;
//import java.utils.ArrayList;

public class Game {
  private static class GameConfiguration {
    private static int nbOfPlayers;

    public static void loadFrom(File f) {
    }
  }

  private static class GameBoard {
    // private static ArrayList<PlayerBoard> playerBoards;
    // private static Bag bag;
    // private static Trash trash;
    // private static Factory[] factories;
    // private static CenterArea center;

    public static void initGameBoard() {
      // initPlayerBoards();
      // bag = new Bag();
      // trash = new Trash();
      // initFactories();
      // initCenter();
    }
  }

  // private static ArrayList<Player> players;
  // private static LinkedList<Round> rounds;
  // private static Round currentRound;
  private static Player winner;

  public static void initGame(File configFile) {
    GameConfiguration.loadFrom(configFile);
    GameBoard.initGameBoard();
    // initPlayers();
    // initRounds();
    // round.addObserver(Game.class)
    winner = null; // ??
  }

  public static void play() {
  }

  // peut-être pas nécessaire
  public static void shutdown() {
  }

}
