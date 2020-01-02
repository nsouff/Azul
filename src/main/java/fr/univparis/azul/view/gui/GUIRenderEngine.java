package fr.univparis.azul.view.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;


import fr.univparis.azul.model.Game;
import fr.univparis.azul.view.GameRenderEngine;

public class GUIRenderEngine extends GameRenderEngine {

	JFrame window;
	
	public GUIRenderEngine(Game g) {
		super(g);
		
		window = new JFrame("Azul");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setFullScreenMode(window);
		
		window.setVisible(true);
	}

	private static void setFullScreenMode(JFrame window) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = ge.getDefaultScreenDevice();
		device.setFullScreenWindow(window);
	}
	
	@Override
	public void render() {
		JPanel gameBoard = new GameBoardView(game.getBoard(), game.getPlayers());

		window.setContentPane( gameBoard );
		window.revalidate();
		window.repaint();
	}
}
