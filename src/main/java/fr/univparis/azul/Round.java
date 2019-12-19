package fr.univparis.azul;

import fr.univparis.azul.area.*;
import fr.univparis.azul.tile.*;
import fr.univparis.azul.util.*;
import java.util.*;

// agit comme un automate
public class Round {
    private CircularLinkedList<Player> players;
    
    public Round(List<Player> p, Player firstPlayer) {
	initPlayers(p, firstPlayer);
    }

    private void initPlayers(List<Player> p, Player firstPlayer) {
	players = new CircularLinkedList<Player>();
	for( Player player : p) {
	    players.add(player);
	}
	players.setFirst(firstPlayer);
    }
    
    
    public void preparationPhase(Game.GameBoard board) {
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

    public void offerPhase(Game.GameBoard board) {
	Iterator<Player> it = players.iterator();
	while ( !board.areFactoriesEmpty() && board.center.isEmpty() ) {
 	    Player currentPlayer = it.next();
	    // currentPlayer.play();	    
	}
    }

    public void decorationPhase(Game.GameBoard board) {
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
}
