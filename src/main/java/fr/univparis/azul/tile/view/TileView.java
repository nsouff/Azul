package fr.univparis.azul.tile.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.tile.Tile;

public class TileView extends JLabel implements Transferable {

	private static DataFlavor tileViewDataFlavor;

	public static DataFlavor getTileViewDataFlavor() {
		try {
			if( tileViewDataFlavor == null)
				tileViewDataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=TileView");
		} catch ( Exception e) {
			return null;
		}
		return tileViewDataFlavor;
	}


	private static DataFlavor containerDataFlavor;

	public static DataFlavor getContainerDataFlavor() {
		try {
			if( containerDataFlavor == null)
				containerDataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=java.awt.Container");
		} catch ( Exception e) {
			return null;
		}
		return containerDataFlavor;
	}

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

		this.addMouseListener(new MouseAdapter() {
			@Override()
			public void mousePressed(MouseEvent e) {
				JComponent c = (JComponent) e.getSource();
				TransferHandler handler = c.getTransferHandler();
				handler.exportAsDrag(c, e, TransferHandler.COPY);
			}
		});


		this.setTransferHandler(new TransferHandler() {

			/**
			 * This creates the Transferable object. In our case, TileView implements Transferable, so this requires only a type cast.
			 */
			@Override
			public Transferable createTransferable(JComponent c) {        
				return (TileView)c;
			}

			/**
			 * This is queried to see whether the component can be copied, moved, both or neither. We are only concerned with copying.
			 */
			@Override
			public int getSourceActions(JComponent c) {
				return COPY;
			}

			// Only needed if we use MOVE
			// @Override
			// public void exportDone(JComponent source, Transferable data, int action) {}

		});	
	}


	/**
	 * If multiple DataFlavor's are supported, can choose what Object to return.
	 * In this case, we only support one: the actual TileView.
	 * Note we could easily support more than one. For example, if supports text and drops to a JTextField, could return the label's text or any arbitrary text.
	 */
	@Override
	public Object getTransferData(DataFlavor flavor) {
		if ( getTileViewDataFlavor().equals(flavor) )
			return this; // For now we assume we want this
		else if ( getContainerDataFlavor().equals(flavor) )
			return getParent();
		return null;
	}

	/**
	 * Returns supported DataFlavor. Again, we're only supporting this actual Object within the JVM.
	 * For more information, see the JavaDoc for DataFlavor.
	 */
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] flavors = new DataFlavor[2];

		flavors[0] = getTileViewDataFlavor();
		flavors[1] = getContainerDataFlavor();

		return flavors;
	}

	/**
	 * Determines whether this object supports the DataFlavor. In this case, only one is supported: for this object itself.
	 * @return True if DataFlavor is supported, otherwise false.
	 */
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		DataFlavor[] flavors = getTransferDataFlavors();

		for (DataFlavor f : flavors) {
			if (f.equals(flavor)) {
				return true;
			}
		}

		return false;
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
