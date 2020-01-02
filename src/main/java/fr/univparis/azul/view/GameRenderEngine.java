package fr.univparis.azul.view;

import java.util.ArrayList;

import fr.univparis.azul.model.Game;
import fr.univparis.azul.model.Player;
import fr.univparis.azul.view.InputManager;

public abstract class GameRenderEngine {
	protected Game game;
	public InputManager input;

	public GameRenderEngine(Game g) {
		game = g;
	}

	public abstract void render();
	
	public abstract void renderVictoryScreen();
}
