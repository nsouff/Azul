package fr.univparis.azul;

import fr.univparis.azul.area.*;
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
    }

    private class PlayerBoard { // peut être protected
	private Wall playerWall;
	private Floor playerFloor;
	private PatternArea playerPatternArea;

	public PlayerBoard() {
	    playerWall = new Wall();
	    playerFloor = new Floor();
	    playerPatternArea = new PatternArea();
	}
    }

    public Player() {
	stats = new Stat();
	playerBoard = new PlayerBoard();
    }

    protected Stat stats;
    protected PlayerBoard playerBoard;

    public PlayerBoard getPlayerBoard() {
	return playerBoard;
    }

    public Stat getStats() {
	return stats;
    }
}
