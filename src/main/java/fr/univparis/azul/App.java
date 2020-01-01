package fr.univparis.azul;

import java.io.File;

import fr.univparis.azul.controller.GameController;
import fr.univparis.azul.model.Game;
import fr.univparis.azul.view.GameRenderEngine;
import fr.univparis.azul.view.console.ConsoleRenderEngine;

public class App {
	public static void main( String[] args ) {
		try {
			File config = new File(args[0]); // for the moment we are using the path passed in arguments
			Game gameModel = new Game(config);
			GameRenderEngine gameView = new ConsoleRenderEngine(gameModel);
		    GameController gameController = new GameController(gameModel, gameView );
		    gameController.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
