package fr.univparis.azul.area.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import fr.univparis.azul.model.area.CenterArea;
import fr.univparis.azul.model.tile.Tile;
import fr.univparis.azul.tile.view.TileView;

public class CenterAreaView extends JPanel {

	private CenterArea centerAreaModel;

	public CenterAreaView(CenterArea cA) {
		centerAreaModel = cA;

		setLayout(new FlowLayout(FlowLayout.LEADING) );
		setBackground( new Color(0,0,0,50));

		for( Tile tile : centerAreaModel.getTiles() ) {
			add( new TileView(tile));
		}	
	}

}
