package fr.univparis.azul.tile;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

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
	    System.err.println("erre");
	    return null;
	}
	return containerDataFlavor;
    }

    public static final int TILE_HEIGHT = 50;
    public static final int TILE_WIDTH = 50;
    
    /** create a TileView with the icon given in constructor's path */
    public TileView(URL url) {
	super( new ImageIcon(url) );

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
    
    public static JLabel createTilePlaceholder(URL bgPath) {
	JLabel placeholder = new JLabel();
	placeholder.setIcon(new ImageIcon(bgPath));
	placeholder.setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));

	return placeholder;
    }
}
