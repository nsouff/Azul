package fr.univparis.azul.model.tile;

import fr.univparis.azul.model.Game;
import fr.univparis.azul.model.Player;

public class FirstTile extends Tile implements SpecialTile {

	private Player owner;

	public FirstTile() {
		this(null);
	}
	
	public FirstTile(Player p) {
		owner = p;
	}
	
	public void effect(Game g) {
		if(owner == null)
			throw new IllegalStateException();
		else
			g.setFirst(owner);
	}
}
