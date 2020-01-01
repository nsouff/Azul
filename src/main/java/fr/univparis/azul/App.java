package fr.univparis.azul;

import java.util.Scanner;

import fr.univparis.azul.controller.GameController;
import fr.univparis.azul.model.Game;
import fr.univparis.azul.view.GameRenderEngine;
import fr.univparis.azul.view.console.ConsoleRenderEngine;

public class App {

	private static String[] createParty() {		
		String[] players;
		int n;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Combien de joueur ? (2-4) : ");
			n = sc.nextInt();
		} while ( n <= 1 || 4 < n);

		players = new String[n];
		sc.nextLine();// on vide la ligne
		for(int i=0; i < n; i++) {
			System.out.print("Nom du joueur " + i + " : ");
			players[i] = sc.nextLine();
		}

		sc.close();
		return players;
	}

	public static void main( String[] args ) {

		String[] players = createParty();

		Game gameModel = new Game( players );
		GameRenderEngine gameView = new ConsoleRenderEngine(gameModel);
		GameController gameController = new GameController(gameModel, gameView );

		gameController.play();
	}
}
