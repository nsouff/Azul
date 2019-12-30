package fr.univparis.azul;

import java.io.File;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class GameController {
    private Game gameModel;
    private GameView gameView;

    public GameController(File gameConfig) throws Exception {
	gameModel = new Game( gameConfig );
	gameView = new GameView( gameModel );

	gameView.setVisible(true);
    }
}
