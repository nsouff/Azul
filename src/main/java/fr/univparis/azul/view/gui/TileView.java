package fr.univparis.azul.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.tile.Tile;

public class TileView extends JLabel {

	public static final int TILE_HEIGHT = 50;
	public static final int TILE_WIDTH = 50;

	private Tile tileModel;

	/** create a TileView with the icon given in constructor's path */
	public TileView(Tile t) {	
		tileModel = t;
		loadIcon(t);

		int iconHeight = getIcon().getIconHeight();
		int iconWidth = getIcon().getIconWidth();

		if( iconHeight != 50 || iconWidth != 50)
			throw new IllegalStateException();
	}

	private void loadIcon(Tile t) {
		if( t instanceof FirstTile )
			setIcon( new ImageIcon( getClass().getResource("/assets/first.png")));
		else if( t instanceof ColoredTile )
			setIcon( new ImageIcon( getColorURL( ((ColoredTile)t).getColor() )));
	}

	private URL getColorURL(ColoredTile.Colors color) {
		switch(color) {
		case BLACK:
			return getClass().getResource("/assets/black.png");
		case BLUE:
			return getClass().getResource("/assets/blue.png");
		case RED:
			return getClass().getResource("/assets/red.png");
		case GREEN:
			return getClass().getResource("/assets/green.png");
		case YELLOW:
			return getClass().getResource("/assets/yellow.png");	    
		}
		return null;
	}


	public static JLabel createTilePlaceholder() {
		JLabel placeholder = new JLabel();
		placeholder.setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));

		return placeholder;
	}


	public static JLabel createTilePlaceholder(Color bgColor) {
		JLabel placeholder = new JLabel();
		placeholder.setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));

		placeholder.setBackground( bgColor );
		placeholder.setOpaque( true );

		return placeholder;
	}  
}
