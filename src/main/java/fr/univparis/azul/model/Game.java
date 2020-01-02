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
	private GameBoard board;

	public Game(String[] playersName)  {
		if( playersName == null )
			throw new NullPointerException();
		if( playersName.length <= 1 || 4 < playersName.length )
			throw new IllegalArgumentException();

		board = new GameBoard( playersName.length );

		initPlayers( playersName );
	}


	public int getNbPlayers() {
		return players.size();
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

	public void offerPhase(InputManager input) {
		Iterator<Player> it = getOrderedPlayers().iterator();
		while ( !board.areFactoriesEmpty() && !board.center.isEmpty() ) {
			Player currentPlayer = it.next();
			currentPlayer.play(input).apply();
		}
	}

	public CircularLinkedList<Player> getOrderedPlayers() {
		CircularLinkedList<Player> orederedP = new CircularLinkedList<Player>();
		for( Player player : players ) {
			orederedP.add(player);
		}
		orederedP.setFirst(players.get(0));
		return orederedP;
	}

	public boolean decorationPhase() {
		boolean rowDetected = false;

		for( Player player : players ) {
			for(int i=0; i < 4; i++) {
				PatternArea patternArea = player.playerBoard.getPatternArea();
				Wall wall = player.playerBoard.getWall();
				if ( patternArea.isFull(i) ) {
					ColoredTile cTile = patternArea.takeOneTile(i);
					wall.add(i, cTile);
					patternArea.throwRow(board.trash, i);

					player.stats.addRoundScore(1);
					player.stats.addRoundScore( wall.nbAdjacentTile(i, cTile.getColor() ));
					player.stats.addRoundScore( -player.playerBoard.getFloor().size() );//FIXME : It should be -1, -1, -1, -2, -2, -3, -3, -3
				}
			}
			if( !rowDetected )
				rowDetected = player.playerBoard.getWall().hasFullRow();
		}
		return rowDetected;
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
