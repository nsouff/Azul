package fr.univparis.azul;

import java.util.*;
import fr.univparis.azul.tile.*;
import fr.univparis.azul.area.*;
import fr.univparis.azul.util.*;

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
	
	// board.bag.fill(board.factories);
    }

    public void offerPhase() {
	Iterator<Player> it = players.iterator();
	while ( !areFactoriesEmpty() && !isCenterEmpty() ) {
 	    Player currentPlayer = it.next();
	    // currentPlayer.play();	    
	}
    }

    public void decorationPhase() {
	
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
