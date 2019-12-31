package fr.univparis.azul.game.view.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FitBackgroundPanel extends JPanel {
    private BufferedImage img;

    public FitBackgroundPanel(String path) {
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
	g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
