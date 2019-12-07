package fr.univparis.azul.area;

import java.util.*;

import fr.univparis.azul.tile.*;

public abstract class CommonArea implements UnindexedArea {
  protected List<Tile> tiles;

  public List<ColoredTile> getSameColorTile(ColoredTile.Colors c) {
    ArrayList<ColoredTile> list = new ArrayList<ColoredTile>();
    Iterator<Tile> it = tiles.iterator();
    while (it.hasNext()) {
      Tile t = it.next();

      if (t instanceof ColoredTile && ( ((ColoredTile) t).getColor() == c )) {
        it.remove();
        list.add((ColoredTile)t);
      }
    }

    return list;
  }
}
