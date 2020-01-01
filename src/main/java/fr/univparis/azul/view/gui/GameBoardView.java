package fr.univparis.azul.view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;

import fr.univparis.azul.model.Game;
import fr.univparis.azul.model.Player;
import fr.univparis.azul.model.area.Factory;

public class GameBoardView extends JPanel {
	private static final BufferedImage img = loadBg();
	
	private static BufferedImage loadBg() {
		BufferedImage img; 
		try {
			img = ImageIO.read( FactoryView.class.getResource("/assets/bg.png"));
			return img;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		int imgWidth = img.getWidth();
		int imgHeight = img.getHeight();
		for (int y = 0; y < getHeight(); y += imgHeight) {
			for (int x = 0; x < getWidth(); x += imgWidth) {
				g2d.drawImage(img, x, y, this);
			}
		}
		g2d.dispose();
	}
	
	public GameBoardView(Game.GameBoard gameBoard, List<Player> players) {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;

		c.gridx = 0;
		c.gridy = 0;
		add( createFactoriesArea(gameBoard.factories), c );

		c.gridy = 1;
		c.weighty = 1.0; //request any extra vertical space
		add( createBottomArea(gameBoard, players), c );
	}
	
	private JPanel createFactoriesArea(ArrayList<Factory> factories) {
		JPanel factoriesArea = new JPanel();
		factoriesArea.setLayout(new GridBagLayout());
		factoriesArea.setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridy = 0;
		c.insets = new Insets(10,0,0,0);

		for(int i=0; i < factories.size(); i++) {
			c.gridx = i;
			factoriesArea.add( new FactoryView(factories.get(i)),c );
			if( i < factories.size() )
				factoriesArea.add( Box.createHorizontalGlue(),c );
		}

		return factoriesArea;
	}
	
	private JPanel createBottomArea(Game.GameBoard gameBoard, List<Player> players ) {
		JPanel bottomArea = new JPanel(new GridBagLayout());
		bottomArea.setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;

		List<Player> leftPlayers = players.subList(0, players.size()/2 );
		c.gridy = 0;
		c.gridx = 0;
		bottomArea.add( createPlayersArea(leftPlayers),c );

		c.gridx = 1;
		c.weightx = 1.0;
		c.insets = new Insets(50,50,50,50);
		CenterAreaView centerArea = new CenterAreaView(gameBoard.center);
		bottomArea.add( centerArea,c );

		List<Player> rightPlayers = players.subList(players.size()/2, players.size() );
		c.gridx = 2;
		c.weightx = 0.0;
		c.insets = new Insets(0,0,0,0);
		bottomArea.add( createPlayersArea(rightPlayers),c );

		return bottomArea;
	}

	private JPanel createPlayersArea(List<Player> players) {
		JPanel playersArea = new JPanel(new GridBagLayout());
		playersArea.setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1.0;
		c.weightx = 1.0;
		c.gridx = 0;

		for(int y=0; y < players.size(); y++) {
			c.gridy = y;
			playersArea.add( new PlayerBoardView(players.get(y)),c );
		}

		return playersArea;
	}
}
