package fr.univparis.azul;

import fr.univparis.azul.tile.TileView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameView extends JFrame {
    
    
    private Game gameModel;
    
    public GameView(Game g) {
	gameModel = g;

	setTitle("Azul");       
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	setFullScreenMode(this);
        
	JPanel rootPane = new TileBackgroundPanel("/assets/bg.png");
        rootPane.setLayout(new GridBagLayout());

	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 1.0;
	
	c.gridx = 0;
	c.gridy = 0;
	rootPane.add( createFactoriesArea(9), c );

	c.gridy = 1;
	c.weighty = 1.0; //request any extra vertical space
	String[] players = {"Romain","CPU1","BOB","CPU2"};
	rootPane.add( createBottomArea(players), c );
	
	setContentPane( rootPane );
    }


    private static void setFullScreenMode(JFrame window) {
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice device = ge.getDefaultScreenDevice();
	device.setFullScreenWindow(window);
    }

    private static JPanel createFactoriesArea(int nbFactory) {
	JPanel factoriesArea = new JPanel();
	factoriesArea.setLayout(new GridBagLayout());
	factoriesArea.setOpaque(false);
	
	GridBagConstraints c = new GridBagConstraints();
	c.weightx = 1.0;
	c.weighty = 1.0;
	c.gridy = 0;
	c.insets = new Insets(10,0,0,0);

	for(int i=0; i < nbFactory; i++) {
	    c.gridx = i;
	    factoriesArea.add( createFactory(),c );
	    if( i < nbFactory - 1 )
	     	factoriesArea.add( Box.createHorizontalGlue(),c );
	}

	return factoriesArea;
    }

    private static JPanel createFactory() {
	JPanel factory = new FitBackgroundPanel("/assets/bg_factory.png");
	factory.setLayout(new GridBagLayout() );
	factory.setOpaque(false);
	
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(10,10,10,10); //marge

	for(int y=0; y < 2; y++) {
	    c.gridy = y;
	    for(int x=0; x<2; x++) {
		c.gridx = x;
		factory.add( TileView.createTilePlaceholder(Color.BLACK),c );
	    }
	}
	
	return factory;
    }

    private static JPanel createBottomArea( String[] playersName ) {
	if( playersName.length < 2 || playersName.length > 4 )
	    throw new IllegalArgumentException("There must be 2 to 4 players.");

	JPanel bottomArea = new JPanel(new GridBagLayout());
	bottomArea.setOpaque(false);
	
	GridBagConstraints c = new GridBagConstraints();
	c.weighty = 1.0;
	c.fill = GridBagConstraints.BOTH;
	
	String[] leftPlayers = Arrays.copyOfRange( playersName, 0, playersName.length/2);
	c.gridy = 0;
	c.gridx = 0;
	bottomArea.add( createPlayersArea(leftPlayers),c );

	c.gridx = 1;
	c.weightx = 1.0;
	c.insets = new Insets(50,50,50,50);
	JPanel centerArea = new JPanel( new FlowLayout(FlowLayout.LEADING) );
	centerArea.setBackground( new Color(0,0,0,50));
	bottomArea.add( centerArea,c );
	
	String[] rightPlayers = Arrays.copyOfRange( playersName, playersName.length/2, playersName.length);
	c.gridx = 2;
	c.weightx = 0.0;
	c.insets = new Insets(0,0,0,0);
	bottomArea.add( createPlayersArea(rightPlayers),c );
	
	return bottomArea;
    }

    private static JPanel createPlayersArea(String[] names) {
	if( names.length != 1 && names.length != 2 )
	    throw new IllegalArgumentException();
	JPanel playersArea = new JPanel(new GridBagLayout());
        playersArea.setOpaque(false);
	
	GridBagConstraints c = new GridBagConstraints();
	c.weighty = 1.0;
	c.weightx = 1.0;
	c.gridx = 0;
	
	for(int y=0; y < names.length; y++) {
	    c.gridy = y;
	    playersArea.add( createPlayerBoard(names[y]),c );
	}
	
	return playersArea;
    }

    private static JPanel createPlayerBoard(String name) {
	JPanel playerBoard = new JPanel(new GridBagLayout());
	playerBoard.setBorder(new TitledBorder(name));
	
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
	patternArea.setBackground(Color.GREEN);

	GridBagConstraints c = new GridBagConstraints();
	c.anchor = GridBagConstraints.LINE_END;

	for(int y=0; y < 5; y++) {
	    c.gridy = y;
	    c.gridx = 4-y;
	    c.gridwidth = y+1;

	    JPanel patternLine = new JPanel();
	    patternLine.setBackground(Color.YELLOW);
	    
	    for(int n=0; n < y+1; n++)
		patternLine.add( TileView.createTilePlaceholder(Color.BLACK));
	    patternArea.add( patternLine,c );	    
	}
	
	return patternArea;
    }

    
    private static JPanel createFloor() {
	JPanel floor = new JPanel();
	floor.setBackground(Color.RED);

	for(int i=0; i < 7; i++) {
	    floor.add( TileView.createTilePlaceholder(Color.BLACK));
	}
	return floor;
    }

    private static JPanel createWall() {
	JPanel wall = new JPanel(new GridBagLayout());
	wall.setBackground(Color.BLUE);

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
