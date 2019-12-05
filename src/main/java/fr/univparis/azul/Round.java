package fr.univparis.azul;

import java.util.*;
import fr.univparis.azul.tile.*;
import fr.univparis.azul.area.*;

// agit comme un automate
public class Round {
    // private CircularLinkedList<Player> players;

    public Round(List<Player> players, Player firstPlayer) {
	// on initialise this.players avec players et firstPlayer. à préciser
	// players = new CircularLinkedList(players, firstPlayer);
    }
    
    
    public void preparationPhase(Game.GameBoard board) {
	board.center.add(new FirstTile());
	
	if( board.bag.isEmpty() ) {
	    board.bag.refill(board.trash);
	}
	
	// board.bag.fill(board.factories);
    }

    public void offerPhase(Game.GameBoard board) {
	// iterator<Player> it = players.iterator();
	// while ( !areFactoriesEmpty(board) ) {
 	//     Player currentPlayer = it.next();
	//     while( currentPlayer.havePlayed() ) {
	//     	currentPlayer.play();
	//     }
	// }
    }

    public void decorationPhase() {
	
    }

    private boolean areFactoriesEmpty(Game.GameBoard board) {
	for( Factory f : board.factories ) {
	    if ( ! f.isEmpty() )
		return false;
	}
	return true;
    }
    
    
}
