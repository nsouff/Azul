package fr.univparis.azul.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr.univparis.azul.model.area.Factory;
import fr.univparis.azul.model.area.Floor;
import fr.univparis.azul.model.area.PatternArea;
import fr.univparis.azul.model.area.Wall;
import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.SpecialTile;
import fr.univparis.azul.model.tile.Tile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.GameAction;
import fr.univparis.azul.view.InputManager;

public abstract class Player {
	public class Stat { // peut être protected
		private int totalScore = 0;
		private LinkedList<Integer> scorePerRound = new LinkedList<Integer>();
		private String name;

		public String getName() {return name;}
		public int getTotalScore() {
			return totalScore;
		}

		public void addRoundScore(int score) {
			scorePerRound.add( Integer.valueOf(score) );
			totalScore += score;
		}
	}

	public class PlayerBoard { // peut être protected
		private Wall playerWall;
		private Floor playerFloor;
		private PatternArea playerPatternArea;

		public PlayerBoard() {
			playerWall = new Wall();
			playerFloor = new Floor();
			playerPatternArea = new PatternArea();
		}

		public Wall getWall() {
			return playerWall;
		}

		public Floor getFloor() {
			return playerFloor;
		}

		public PatternArea getPatternArea() {
			return playerPatternArea;
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
	public Game.GameBoard gameBoard;

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
		ArrayList<Tile> toDelete = new ArrayList<Tile>();
		for (Tile t : tiles) {
			if (t instanceof ColoredTile) {
				ColoredTile ct = (ColoredTile) t;
				if (ct.getColor() == c) {
					toDelete.add(t);
					if (! playerBoard.playerPatternArea.isFull(index)) playerBoard.playerPatternArea.add(index, t);
					else if (playerBoard.playerFloor.isFull()) gameBoard.trash.add(t);
					else playerBoard.playerFloor.add(t);
				}
			}
			else {
				toDelete.add(t);
				playerBoard.playerFloor.add(t);
			}
		}
		tiles.removeAll(toDelete);
	}

	public void moveFirstTileFromCenterToFloor() {
		FirstTile f = null;
		for (Tile t : gameBoard.center.getTiles()) {
			if (t instanceof FirstTile) {
				f = (FirstTile) t;
			}
		}
		if (f == null) throw new IllegalStateException();
		playerBoard.playerFloor.add(f);
		gameBoard.center.getTiles().remove(f);
	}

	public void moveFromFactoryToFloor(ColoredTile.Colors c, Factory f) {
		for (Tile t : f.getTiles()) {
			if (((ColoredTile)t).getColor() == c) {
				if (playerBoard.playerFloor.isFull()) gameBoard.trash.add(t);
				else playerBoard.playerFloor.add(t);
			}
			else gameBoard.center.add(t);
		}
		f.emptyIt();
	}

	public void moveFromCenterToFloor(ColoredTile.Colors c) {
		List<Tile> toDelete = new ArrayList<Tile>();
		for (Tile t : gameBoard.center.getTiles()) {
			if (t instanceof ColoredTile) {
				if (((ColoredTile)t).getColor() == c) {
					toDelete.add(t);
					if (! playerBoard.playerFloor.isFull()) playerBoard.playerFloor.add(t);
					else gameBoard.trash.add(t);
				}
			}
			else {
				playerBoard.playerFloor.add(t);
				toDelete.add(t);
			}
		}
		gameBoard.center.getTiles().removeAll(toDelete);
	}

	public abstract void play(InputManager i);

}
