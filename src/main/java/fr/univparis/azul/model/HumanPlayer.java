package fr.univparis.azul.model;
import fr.univparis.azul.model.GameAction;
import fr.univparis.azul.view.InputManager;


public class HumanPlayer extends Player {

  public HumanPlayer(String name, Game.GameBoard g) {
    super(name, g);
  }

  public GameAction play(InputManager input) {
    return null;
  }

}
