package fr.univparis.azul.area.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.univparis.azul.model.Player;
import fr.univparis.azul.tile.view.TileView;

public class PlayerBoardView extends JPanel {

	private Player.PlayerBoard playerBoardModel;
	private static final BufferedImage img = loadBg();





	public PlayerBoardView(Player player) {
		playerBoardModel = player.getPlayerBoard();

		setLayout( new GridBagLayout() );

		String name = player.getStats().getName();
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), name, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP);
		border.setTitleColor( Color.WHITE );
		setBorder( border );

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		add( createPatternArea(), c );

		c.gridx = 1;
		c.gridy = 0;
		add( createWall(), c );


		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		add( createFloor(), c);
	}


	private static JPanel createPatternArea() {
		JPanel patternArea = new JPanel(new GridBagLayout());
		patternArea.setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;

		for(int y=0; y < 5; y++) {
			c.gridy = y;
			c.gridx = 4-y;
			c.gridwidth = y+1;

			JPanel patternLine = new JPanel();
			patternLine.setBackground(new Color(224, 211, 175));

			for(int n=0; n < y+1; n++)
				patternLine.add( TileView.createTilePlaceholder(Color.BLACK));
			patternArea.add( patternLine,c );	    
		}

		return patternArea;
	}


	private static JPanel createFloor() {
		JPanel floor = new JPanel();
		floor.setBackground(new Color(224, 211, 175));

		for(int i=0; i < 7; i++) {
			floor.add( TileView.createTilePlaceholder(Color.BLACK));
		}
		return floor;
	}

	private static JPanel createWall() {
		JPanel wall = new JPanel(new GridBagLayout());
		wall.setBackground(new Color(224, 211, 175));

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);

		for(int y=0; y < 5; y++) {
			c.gridy = y;
			for(int x=0; x < 5; x++) {
				c.gridx = x;
				wall.add( TileView.createTilePlaceholder(Color.BLACK), c);
			}
		}

		return wall;
	}

	private static BufferedImage loadBg() {
		BufferedImage img; 
		try {
			img = ImageIO.read( FactoryView.class.getResource("/assets/bg_playerboard.png"));
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
}
