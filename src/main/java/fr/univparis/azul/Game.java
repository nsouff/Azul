package fr.univparis.azul;

import fr.univparis.azul.area.*;
import java.io.File;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class Game { // implements WallObserver
    private class GameConfiguration {
	private int nbOfPlayers;

	public GameConfiguration(File f) throws Exception {
	    JSONObject json = new JSONObject (FileUtils.readFileToString(f,"utf-8"));

	    nbOfPlayers = json.getInt("nb_of_players");
	}
    }

    public class GameBoard {
	Bag bag;
	Trash trash;
	ArrayList<Factory> factories;
	CenterArea center;

	public GameBoard(int nbOfPlayers) {
	    bag = new Bag();
	    trash = new Trash();
	    initFactories(nbOfPlayers);
	    center = new CenterArea();
	}
	
	private void initFactories(int nbOfPlayers) {
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

	private void fillWithFactory(int n) {
	    for(int i=0; i < n; i++) {
		factories.add(new Factory());
	    }
	}
    }

    private GameConfiguration config;
    private ArrayList<Player> players;
    private GameBoard board;

    public Game(File gameConfig) throws Exception {
	config = new GameConfiguration(gameConfig);

	initPlayers(config.nbOfPlayers);

	board = new GameBoard(config.nbOfPlayers);	
    }

    public List<Player> getWinners() {
	List<Player> winners = new LinkedList<Player>();
	winners.add( players.get(0) );
	
	int max = winners.get(0).getStats().getTotalScore();
	
	for( Player p : players ) {
	    int pScore = p.getStats().getTotalScore();	   
	    if( max < pScore ) {
		winners.clear();
		winners.add(p);
		max = pScore;
	    } else if ( max == pScore ) {
		winners.add(p);
	    }
	}
	return winners;
    }
    
    public void play() {
	boolean playing = true;
	Round round = new Round(players, players.get(0));	
	do {
	    board.initFactories(players.size());
	    board.center = new CenterArea();

	    round.preparationPhase(board);
	    round.offerPhase(board);
	    round.decorationPhase(board);
	    if ( rowInAnyWall() ) {
		playing = false;
	    }
	    // else {
	    // 	Player first = ;// obtenir le joueur possédant la tile 1er joueur
	    // 	round.getPlayers().setFirst( first ); // écrire une méthode dans Round pour faire ça
	    // }
	} while ( playing );
    }

    // peut-être pas nécessaire
    public void shutdown() {
    }

    private void initPlayers(int nbOfPlayers) {
	if( nbOfPlayers <= 1 || nbOfPlayers > 4 )
	    throw new IllegalArgumentException("Invalid number of players");

	players = new ArrayList<Player>(nbOfPlayers);

	for(int i=0; i < nbOfPlayers; i++) {
	    players.add(new HumanPlayer(String.valueOf(i))); //pour l'instant on initialise que des joueurs humains
	}	
    }

    private boolean rowInAnyWall() {
	for( Player p : players ) {
	    if( p.getPlayerBoard().getWall().hasFullRow() )
		return true;
	}
	return false;
    }
}
