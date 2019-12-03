package fr.univparis.azul;

import fr.univparis.azul.area.*;
import java.io.File;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class Game {
    private static class GameConfiguration {
	private static int nbOfPlayers;

	public static void loadFrom(File f) throws Exception {
	    JSONObject json = new JSONObject (FileUtils.readFileToString(f,"utf-8"));

	    nbOfPlayers = json.getInt("nb_of_players");
	}
    }

    private static class GameBoard {
	private static Bag bag;
	private static Trash trash;
	private static ArrayList<Factory> factories;
	private static CenterArea center;

	public static void initGameBoard() {
	    bag = new Bag();
	    trash = new Trash();
	    initFactories(GameConfiguration.nbOfPlayers);
	    center = new CenterArea();
	}

	private static void initFactories(int nbOfPlayers) {
	    int n = 0;
	    switch(nbOfPlayers) {
	    case 2:
		n = 5;
		break;
	    case 3:
		n = 7;
		break;
	    case 4:
		n = 9;
		break;
	    default:
		throw new RuntimeException("Invalid number of players");
	    }
	    
	    factories = new ArrayList<Factory>(n);

	    fillWithFactory(n);
	}

	private static void fillWithFactory(int n) {
	    for(int i=0; i < n; i++) {
		factories.add(new Factory());
	    }
	}
    }

    private static ArrayList<Player> players;
    private static Round round;
    private static Player winner;

    public static void initGame(File configFile) throws Exception {
	GameConfiguration.loadFrom(configFile);

	initPlayers(GameConfiguration.nbOfPlayers);

	GameBoard.initGameBoard();
	
	round = new Round();//nécessite probablement un argument pour savoir qui commence
	
	winner = null;
    }

    public static void play() {
    }

    // peut-être pas nécessaire
    public static void shutdown() {
    }

    private static void initPlayers(int nbOfPlayers) {
	if( nbOfPlayers <= 1 || nbOfPlayers > 4 )
	    throw new RuntimeException("Invalid number of players");

	players = new ArrayList<Player>(nbOfPlayers);

	for(int i=0; i < nbOfPlayers; i++) {
	    players.add(new HumanPlayer()); //pour l'instant on initialise que des joueurs humains
	}	
    }
}
