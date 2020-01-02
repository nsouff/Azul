package fr.univparis.azul.view;

import fr.univparis.azul.model.Player;
public abstract class InputManager {
	protected GameRenderEngine gameView;
	
	public InputManager(GameRenderEngine g) {
		gameView = g;
	}
	
	public abstract void read(Player p);
}
