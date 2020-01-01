package fr.univparis.azul.controller;

import fr.univparis.azul.model.Game;
import fr.univparis.azul.view.GameRenderEngine;

public class GameController {

	private Game gameModel;
	private GameRenderEngine gameView;

	public GameController(Game gM, GameRenderEngine gV) {
		gameModel = gM;
		gameView = gV;
	}

	public void play() {

		boolean play = true;

		for(int i=0; i < 2; i++) {
			//phase de préparation
			gameModel.preparationPhase();
			gameView.render();

			//phase d'offre
			gameModel.offerPhase();

			//decoration phase
			play = !gameModel.decorationPhase();
			gameView.render();
		}
	}
}