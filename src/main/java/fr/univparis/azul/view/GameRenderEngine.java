package fr.univparis.azul.view;

import fr.univparis.azul.model.Game;

public abstract class GameRenderEngine {
	protected Game game;

	public GameRenderEngine(Game g) {
		game = g;
	}
	
	public abstract void render();
}
