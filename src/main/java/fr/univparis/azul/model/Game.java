package fr.univparis.azul.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import fr.univparis.azul.model.area.Bag;
import fr.univparis.azul.model.area.CenterArea;
import fr.univparis.azul.model.area.Factory;
import fr.univparis.azul.model.area.Trash;
import fr.univparis.azul.model.tile.FirstTile;

public class Game {
    private class GameConfiguration {
	private int nbOfPlayers;

	public GameConfiguration(File f) throws Exception {
	    JSONObject json = new JSONObject (FileUtils.readFileToString(f,"utf-8"));

	    nbOfPlayers = json.getInt("nb_of_players");
	}
    }

    public class GameBoard {
	public Bag bag;
	public Trash trash;
	public ArrayList<Factory> factories;
	public CenterArea center;

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


	public boolean areFactoriesEmpty() {
	    for( Factory f : factories ) {
		if ( ! f.isEmpty() )
		    return false;
	    }
	    return true;
	}
    }

    private GameConfiguration config;
    private ArrayList<Player> players;
    private GameBoard board;

    public Game(File gameConfig) throws Exception {
	config = new GameConfiguration(gameConfig);

	board = new GameBoard(config.nbOfPlayers);

	initPlayers(config.nbOfPlayers);
    }

    
    public int getNbPlayers() {
	return config.nbOfPlayers;
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
    
    private void initRound() {
	board.initFactories( players.size() );
	board.center = new CenterArea();
	board.bag.shuffle();
    }

    public void preparationPhase() {
	initRound();

	// on place la tuile 1er joueur an centre de la table
	board.center.add(new FirstTile());

	// on remplit chaque usine
	for( Factory f : board.factories ) {
	    if( board.bag.isEmpty() && board.trash.isEmpty() )
		break;
	    else if( board.bag.isEmpty() )
		board.bag.refill( board.trash );
	    f.fill(board.bag);
	}
    }

    // public void play() {
    // 	boolean playing = true;
    // 	Round round = new Round(players, players.get(0));
    // 	do {
    // 	    round.preparationPhase(board);
    // 	    round.offerPhase(board);
    // 	    playing = !round.decorationPhase(board);
    // 	    // if ( playing ) {
    // 	    // 	Player first = ;// obtenir le joueur possédant la tile 1er joueur
    // 	    // 	round.getPlayers().setFirst( first ); // écrire une méthode dans Round pour faire ça
    // 	    // }
    // 	} while ( playing );
    // }

    // peut-être pas nécessaire
    public void shutdown() {
    }

    private void initPlayers(int nbOfPlayers) {
	if( nbOfPlayers <= 1 || nbOfPlayers > 4 )
	    throw new IllegalArgumentException("Invalid number of players");

	players = new ArrayList<Player>(nbOfPlayers);

	for(int i=0; i < nbOfPlayers; i++) {
	    players.add(new HumanPlayer(String.valueOf(i), board)); //pour l'instant on initialise que des joueurs humains
	}
    }

    public Player getPlayer(String name) {
	for (Player p : players)
	    if (p.getStats().getName().equals(name)) return p;
	return null;
    }

    public GameBoard getBoard() {
	return board;
    }

    public ArrayList<Player> getPlayers() {
	return players;
    }
}
