package fr.univparis.azul;

import java.io.*;

import java.net.*;

public class App {
    // for the moment we are using the path passed in arguments
    public static void main( String[] args ) {
	try {
	    File config = new File(args[0]);
		
	    Game.initGame(config);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Game.play();
    }
}
