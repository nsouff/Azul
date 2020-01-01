package fr.univparis.azul.view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.univparis.azul.model.area.Factory;
import fr.univparis.azul.model.tile.Tile;

public class FactoryView extends JPanel {

	private Factory factoryModel;
	private static final BufferedImage img = loadBg();
	
	public FactoryView(Factory f) {
		factoryModel = f;
	
		setLayout(new GridBagLayout() );
		setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10); //marge

		List<Tile> tiles = factoryModel.getTiles();
		boolean isFactoryEmpty = factoryModel.isEmpty();
		for(int y=0; y < 2; y++) {
			c.gridy = y;
			for(int x=0; x<2; x++) {
				c.gridx = x;
				if( isFactoryEmpty )
					add( TileView.createTilePlaceholder(),c );
				else
					add( new TileView(tiles.get(x+y)),c );
			}
		}
	}
	
	private static BufferedImage loadBg() {
		BufferedImage img; 
		try {
		    img = ImageIO.read( FactoryView.class.getResource("/assets/bg_factory.png"));
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
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}