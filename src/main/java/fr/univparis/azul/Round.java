package fr.univparis.azul;

import java.util.*;

public class Round {
    // private LinkedList<Player> players;

    private Player winner = null;
    private List<RoundObserver> observers = new ArrayList<>();
    
    public void preparationPhase() {

    }

    public void offerPhase() {
	// for ( Player p : players ) {
	//     p.canPlay(true);
	//     p.play();
	//     p.canPlay(false);
	// }
    }

    public void decorationPhase() {

    }

    public void addObserver(RoundObserver obs) {
	observers.add(obs);
    }

    public void removeObserver(RoundObserver obs) {
	observers.remove(obs);
    }

    private void notifyObservers() {
	for( RoundObserver obs : observers ) {
	    obs.update(winner);
	}	    
    }
}
