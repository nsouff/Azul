package fr.univparis.azul.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.univparis.azul.model.area.Bag;
import fr.univparis.azul.model.area.CenterArea;
import fr.univparis.azul.model.area.Factory;
import fr.univparis.azul.model.area.PatternArea;
import fr.univparis.azul.model.area.Trash;
import fr.univparis.azul.model.area.Wall;
import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.util.CircularLinkedList;
import fr.univparis.azul.view.InputManager;

public class Game {
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

	private ArrayList<Player> players;
	private Player firstPlayer;
	private GameBoard board;

	public void setFirst(Player p) {
		firstPlayer = p;
	}
	
	public Game(String[] playersName)  {
		if( playersName == null )
			throw new NullPointerException();
		if( playersName.length <= 1 || 4 < playersName.length )
			throw new IllegalArgumentException();

		board = new GameBoard( playersName.length );

		initPlayers( playersName );
		
		firstPlayer = players.get(0);
	}


	public int getNbPlayers() {
		return players.size();
	}

	public ArrayList<Player> getPlayerRanking() {
		ArrayList<Player> ranks = new ArrayList<Player>(players);
		ranks.sort((Player p1, Player p2) -> p1.getStats().getTotalScore()-p2.getStats().getTotalScore());
		return ranks;
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
				board.trash.fill(board.bag);
			board.bag.fill(f);
		}
	}

	public void offerPhase(InputManager input) {
		Iterator<Player> it = getOrderedPlayers().iterator();
		while ( !board.areFactoriesEmpty() && !board.center.isEmpty() ) {
			Player currentPlayer = it.next();
			currentPlayer.play(input);
		}
	}

	public CircularLinkedList<Player> getOrderedPlayers() {
		CircularLinkedList<Player> orederedP = new CircularLinkedList<Player>();
		for( Player player : players ) {
			orederedP.add(player);
		}
		orederedP.setFirst( firstPlayer );
		return orederedP;
	}

	public boolean decorationPhase() {
		boolean rowDetected = false;

		for( Player player : players ) {
			// on s'occupe du mur et des lignes de motif
			for(int i=0; i < 4; i++) {
				PatternArea patternArea = player.playerBoard.getPatternArea();
				Wall wall = player.playerBoard.getWall();
				if ( patternArea.isFull(i) ) {
					ColoredTile cTile = patternArea.takeOneTile(i);
					wall.add(i, cTile);
					patternArea.throwRow(board.trash, i);

					player.stats.addRoundScore(1);
					player.stats.addRoundScore( wall.nbAdjacentTile(i, cTile.getColor() ));
					player.stats.addRoundScore( malusFloor(player.playerBoard.getFloor().size()) );
				}
			}
			// on s'occupe du plancher
			player.getPlayerBoard().getFloor().clean(this);
			if( !rowDetected )
				rowDetected = player.playerBoard.getWall().hasFullRow();
		}
		if(rowDetected) {
			for(Player player : players) {
				Wall wall = player.playerBoard.getWall();
				player.getStats().addRoundScore(wall.nbFullRow()*2);
				player.getStats().addRoundScore(wall.nbFullColumn()*7);
				player.getStats().addRoundScore(wall.nbFullColor()*10);
			}
		}
		return rowDetected;
	}


	private static int malusFloor(int size) {
		switch(size) {
		case 0 : return 0;
		case 1 : return -1;
		case 2 : return -2;
		case 3 : return -4;
		case 4 : return -6;
		case 5 : return -8;
		case 6 : return -11;
		case 7 : return -14;
		}
		return 0;
	}

	private void initPlayers(String[] playersName) {
		players = new ArrayList<Player>(playersName.length);

		for(int i=0; i < playersName.length; i++) {
			players.add(new HumanPlayer(playersName[i], board)); //pour l'instant on initialise que des joueurs humains
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
