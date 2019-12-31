package fr.univparis.azul.game.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.univparis.azul.game.view.util.FitBackgroundPanel;
import fr.univparis.azul.game.view.util.TileBackgroundPanel;
import fr.univparis.azul.model.Game;
import fr.univparis.azul.model.Player;
import fr.univparis.azul.model.area.CenterArea;
import fr.univparis.azul.model.area.Factory;
import fr.univparis.azul.model.tile.ColoredTile;
import fr.univparis.azul.model.tile.FirstTile;
import fr.univparis.azul.model.tile.Tile;
import fr.univparis.azul.tile.view.TileView;

//FIXME : Refactor this class
public class GameView extends JFrame {
    
    
    private Game gameModel;
    
    public GameView(Game g) {
	gameModel = g;

	setTitle("Azul");       
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	setFullScreenMode(this);
    }

    public void drawGameBoard() {
	JPanel gameBoard = createGameBoard();
	
	setContentPane( gameBoard );
    }
    
    private JPanel createGameBoard() {
	JPanel rootPane = new TileBackgroundPanel("/assets/bg.png");
        rootPane.setLayout(new GridBagLayout());

	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 1.0;
	
	c.gridx = 0;
	c.gridy = 0;
	rootPane.add( createFactoriesArea(gameModel.getBoard().factories), c );

	c.gridy = 1;
	c.weighty = 1.0; //request any extra vertical space
	rootPane.add( createBottomArea(gameModel.getPlayers()), c );

	return rootPane;
    }
    

    private static void setFullScreenMode(JFrame window) {
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice device = ge.getDefaultScreenDevice();
	device.setFullScreenWindow(window);
    }

    private static JPanel createFactoriesArea(ArrayList<Factory> factories) {
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
	    factoriesArea.add( createFactory(factories.get(i)),c );
	    if( i < factories.size() )
	     	factoriesArea.add( Box.createHorizontalGlue(),c );
	}

	return factoriesArea;
    }

    private static TileView createTileView(ColoredTile.Colors color) {

	switch ( color ) {
	case BLUE:
	    return new TileView(GameView.class.getResource("/assets/blue.png"));
	case RED:
	    return new TileView(GameView.class.getResource("/assets/red.png"));
	case GREEN:
	    return new TileView(GameView.class.getResource("/assets/green.png"));
	case BLACK:
	    return new TileView(GameView.class.getResource("/assets/black.png"));
	case YELLOW:
	    return new TileView(GameView.class.getResource("/assets/yellow.png"));	    
	}
	return null;
    }
    
    private static JPanel createFactory(Factory factory) {
	JPanel factoryView = new FitBackgroundPanel("/assets/bg_factory.png");
	factoryView.setLayout(new GridBagLayout() );
	factoryView.setOpaque(false);
	
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(10,10,10,10); //marge

        List<Tile> tiles = factory.getTiles();
	boolean isFactoryEmpty = factory.isEmpty();
	for(int y=0; y < 2; y++) {
	    c.gridy = y;
	    for(int x=0; x<2; x++) {
		c.gridx = x;
		if( isFactoryEmpty )
		    factoryView.add( TileView.createTilePlaceholder(),c );
		else
		    factoryView.add( createTileView( ((ColoredTile)tiles.get(y+x)).getColor() ),c );
	    }
	}
	
	return factoryView;
    }

    private JPanel createBottomArea( List<Player> players ) {
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
	bottomArea.add( createCenterArea(gameModel.getBoard().center),c );
	
        List<Player> rightPlayers = players.subList(players.size()/2, players.size() );
	c.gridx = 2;
	c.weightx = 0.0;
	c.insets = new Insets(0,0,0,0);
	bottomArea.add( createPlayersArea(rightPlayers),c );
	
	return bottomArea;
    }

    
    private static JPanel createCenterArea(CenterArea centerArea) {
	JPanel centerAreaView = new JPanel( new FlowLayout(FlowLayout.LEADING) );
	centerAreaView.setBackground( new Color(0,0,0,50));

	for( Tile tile : centerArea.getTiles() ) {
	    if( tile instanceof ColoredTile )
		centerAreaView.add( createTileView( ((ColoredTile)tile).getColor()  ));
	    else if ( tile instanceof FirstTile )
		centerAreaView.add( new TileView(GameView.class.getResource("/assets/first.png")));
	}

	return centerAreaView;
    }
    
    private static JPanel createPlayersArea(List<Player> players) {
	JPanel playersArea = new JPanel(new GridBagLayout());
        playersArea.setOpaque(false);
	
	GridBagConstraints c = new GridBagConstraints();
	c.weighty = 1.0;
	c.weightx = 1.0;
	c.gridx = 0;
	
	for(int y=0; y < players.size(); y++) {
	    c.gridy = y;
	    playersArea.add( createPlayerBoard(players.get(y)),c );
	}
	
	return playersArea;
    }

    private static JPanel createPlayerBoard(Player player) {
	JPanel playerBoard = new TileBackgroundPanel("/assets/bg_playerboard.png");
	playerBoard.setLayout( new GridBagLayout() );

	String name = player.getStats().getName();
	TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), name, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP);
	border.setTitleColor( Color.WHITE );
	playerBoard.setBorder( border );

	
	GridBagConstraints c = new GridBagConstraints();
	c.weightx = 1.0;
	c.fill = GridBagConstraints.HORIZONTAL;
	
	c.gridx = 0;
	c.gridy = 0;
	playerBoard.add( createPatternArea(), c );

	c.gridx = 1;
	c.gridy = 0;
	playerBoard.add( createWall(), c );


	c.gridx = 0;
	c.gridy = 1;
	c.gridwidth = 2;
	playerBoard.add( createFloor(), c);
	
	return playerBoard;
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


}
