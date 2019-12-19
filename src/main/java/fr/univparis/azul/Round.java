package fr.univparis.azul;

import fr.univparis.azul.area.*;
import fr.univparis.azul.tile.*;
import fr.univparis.azul.util.*;
import java.util.*;

// agit comme un automate
public class Round {
    private CircularLinkedList<Player> players;
    private Game.GameBoard board;
    
    public Round(List<Player> p, Player firstPlayer, Game.GameBoard b) {
	initPlayers(p, firstPlayer);
	board = b;
    }

    private void initPlayers(List<Player> p, Player firstPlayer) {
	players = new CircularLinkedList<Player>();
	for( Player player : p) {
	    players.add(player);
	}
	players.setFirst(firstPlayer);
    }
    
    
    public void preparationPhase() {
	board.center.add(new FirstTile());
	
	if( board.bag.isEmpty() ) {
	    board.bag.refill(board.trash);
	}

	for( Factory f : board.factories )
	    f.fill(board.bag);
    }

    public void offerPhase() {
	Iterator<Player> it = players.iterator();
	while ( !areFactoriesEmpty() && !isCenterEmpty() ) {
 	    Player currentPlayer = it.next();
	    // currentPlayer.play();	    
	}
    }

    public void decorationPhase() {
	Iterator<Player> it = players.iterator();
	Player stop = it.next();
	Player player;
	do {
	    player = it.next();
	    for(int i=0; i < 4; i++) {
		PatternArea patternArea = player.playerBoard.playerPatternArea;
		Wall wall = player.playerBoard.playerWall;
		if ( patternArea.isFull(i) ) {
		    ColoredTile cTile = patternArea.takeOneTile(i);
		    wall.add(i, cTile);
		    patternArea.throwRow(board.trash, i);
		    
		    player.stats.addRoundScore(1);
		    player.stats.addRoundScore( wall.nbAdjacentTile(i, cTile.getColor() ));
		    player.stats.addRoundScore( -player.playerBoard.playerFloor.size() );
		}
	    }	    
	} while (player != stop);	
    }

    private boolean areFactoriesEmpty() {
	for( Factory f : board.factories ) {
	    if ( ! f.isEmpty() )
		return false;
	}
	return true;
    }

    private boolean isCenterEmpty() {
	return board.center.isEmpty();
    }    
}
