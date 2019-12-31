package fr.univparis.azul.game.controller;

import java.io.File;

import fr.univparis.azul.game.view.GameView;
import fr.univparis.azul.model.Game;

public class GameController {
    private Game gameModel;
    private GameView gameView;

    public GameController(File gameConfig) throws Exception {
	gameModel = new Game( gameConfig );
	gameView = new GameView( gameModel );

	gameView.setVisible(true);
    }

    public void play() {	
	gameModel.preparationPhase(); // update
	render();
	
    }

    public void render() {
	gameView.drawGameBoard();
	gameView.revalidate();
	//gameView.repaint();
    }
}
