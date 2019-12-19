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

    public Player(String name) {
	stats = new Stat();
	stats.name = name;
	playerBoard = new PlayerBoard();
    }

    Stat stats;
    PlayerBoard playerBoard;

    public PlayerBoard getPlayerBoard() {
	return playerBoard;
    }

    public Stat getStats() {
	return stats;
    }

}
