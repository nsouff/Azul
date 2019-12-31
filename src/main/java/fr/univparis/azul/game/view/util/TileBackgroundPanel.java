package fr.univparis.azul.game.view.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TileBackgroundPanel extends JPanel {
    private BufferedImage img;

    public TileBackgroundPanel(String path) {
	super();
	try {
	    img = ImageIO.read( getClass().getResource(path) );
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
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
