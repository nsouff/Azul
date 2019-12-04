package fr.univparis.azul.area;

import fr.univparis.azul.tile.*;

public interface Area {

  public int size();

  public boolean isEmpty();

  public boolean moveTilesTo(Area area);


}
