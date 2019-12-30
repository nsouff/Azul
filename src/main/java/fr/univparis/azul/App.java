package fr.univparis.azul;

import java.io.*;

import java.net.*;

public class App {
    public static void main( String[] args ) {
	try {
	    File config = new File(args[0]); // for the moment we are using the path passed in arguments
	    GameController game = new GameController( config );
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
