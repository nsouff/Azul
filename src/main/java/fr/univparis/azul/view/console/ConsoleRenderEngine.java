package fr.univparis.azul.view.console;

import java.util.List;

import fr.univparis.azul.model.Game;
import fr.univparis.azul.model.area.Factory;
import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.tile.Tile;
import fr.univparis.azul.view.GameRenderEngine;

public class ConsoleRenderEngine extends GameRenderEngine{

	public ConsoleRenderEngine(Game g) {
		super(g);
	}

	@Override
	public void render() {
		renderFactories(game.getBoard().factories);
	}

	private static void renderFactories(List<Factory> factories) {
		System.out.println("Factories");
		for(Factory f : factories)
			renderFactory(f);
		System.out.println("");
	}
	
	private static void renderFactory(Factory factory) {
		System.out.println("Factory");
		for(Tile t : factory.getTiles())
			System.out.println( "\t"+renderTile(t));				
	}
	
	private static String renderTile(Tile t) {
		if( t instanceof FirstTile )
			return "1st";
		else if ( t instanceof ColoredTile)
			return renderColor(((ColoredTile)t).getColor());
		return "Undefined TILE";
			
	}
	
	private static String renderColor(ColoredTile.Colors color) {
		switch(color) {
		case BLACK: return "BLACK";
		case BLUE: return "BLUE";
		case GREEN: return "GREEN";
		case RED: return "RED";
		case YELLOW: return "YELLOW";
		}
		return "Undefined COLOR";
	}
}
