package fr.univparis.azul.view.console;

import java.util.ArrayList;
import java.util.List;

import fr.univparis.azul.model.Game;
import fr.univparis.azul.model.Player;
import fr.univparis.azul.model.area.CenterArea;
import fr.univparis.azul.model.area.Factory;
import fr.univparis.azul.model.area.Floor;
import fr.univparis.azul.model.area.PatternArea;
import fr.univparis.azul.model.area.Wall;
import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.tile.Tile;
import fr.univparis.azul.view.GameRenderEngine;

public class ConsoleRenderEngine extends GameRenderEngine{

	public ConsoleRenderEngine(Game g) {
		super(g);
		input = new ConsoleInputManager(this);
	}

	@Override
	public void render() {
		renderFactories(game.getBoard().factories);
		renderCenterArea(game.getBoard().center);
		for( Player p : game.getPlayers() ) {
			renderPlayerBoard(p);
		}
	}
	
	@Override
	public void renderVictoryScreen() {
		System.out.println("Final ranks : ");
		ArrayList<Player> ranks = game.getPlayerRanking();
		for	(int i=0; i < ranks.size(); i++) {
			Player.Stat stat = ranks.get(i).getStats();
			System.out.println(i + " - " + stat.getName() + " with a score of " + stat.getTotalScore());
		}
	}

	private static void renderFactories(List<Factory> factories) {
		System.out.println("Factories");
		int num = 0;
		for(Factory f : factories) {
			System.out.print( (num++) + " ");
			renderFactory(f);
		}
		System.out.println();
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

	private static void renderCenterArea(CenterArea centerAre) {
		int maxLargeur = 5;
		int i=0;
		System.out.print("Center Area");
		for( Tile t : centerAre.getTiles()) {
			if(i++%maxLargeur == 0)
				System.out.println();
			System.out.print("\t"+renderTile(t));
		}
		System.out.println();
	}


	private static void renderPlayerBoard(Player player) {
		System.out.println("PlayerBoard of " + player.getStats().getName() + " with score of " + player.getStats().getTotalScore());
		renderPatternArea(player.getPlayerBoard().getPatternArea());
		renderWall(player.getPlayerBoard().getWall());
		renderFloor(player.getPlayerBoard().getFloor());
		System.out.println();
	}

	private static void renderPatternArea(PatternArea patternArea) {
		System.out.println(" Pattern Area");
		int numLine = 1;
		for(ArrayList<ColoredTile> line : patternArea.getTiles()) {
			for(ColoredTile ct : line)
				System.out.print("\t"+renderTile(ct));
			// on complète avec des symboles vides
			for(int i=line.size(); i < numLine; i++)
				System.out.print("\t ---");
			System.out.println();
			numLine++;
		}
	}

	private static void renderFloor(Floor floor) {
		System.out.println(" Floor");
		for(Tile t : floor.getTiles())
			System.out.print("\t"+renderTile(t));
		// on complète avec des symboles vides
		int numLine = 7;
		for(int i=floor.size(); i < numLine; i++)
			System.out.print("\t ---");
		System.out.println();
	}

	private static void renderWall(Wall wall) {
		System.out.println(" Wall");
		for(int i=0; i < 5; i++) {
			for(int j=0; j < 5; j++) {
				Tile t = wall.get(i, j);
				if( t == null)
					System.out.print("\t --- ");
				else
					System.out.print("\t"+renderTile(t));
			}
			System.out.println();
		}
	}
}
