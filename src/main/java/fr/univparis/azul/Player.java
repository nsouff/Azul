package fr.univparis.azul;

import java.util.*;

public abstract class Player {
    public class Stat { // peut être protected
	private int totalScore = 0;
	private LinkedList<Integer> scorePerRound = new LinkedList<Integer>();
	private String name;
	private boolean isHuman;
    }

    public class PlayerBoard { // peut être protected
	// private Wall playerWall;
	// private Floor playerFloor;
	// private PatternArea playerPatternArea;
    }

    private Stat stats;
    private PlayerBoard playerBoard;


}
