package fr.univparis.azul;

import java.io.File;

import fr.univparis.azul.game.controller.GameController;

public class App {
    public static void main( String[] args ) {
	try {
	    File config = new File(args[0]); // for the moment we are using the path passed in arguments
	    GameController game = new GameController( config );
	    game.play();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
