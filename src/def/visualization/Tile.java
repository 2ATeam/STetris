package def.visualization;

import def.game.TileTypes;

import java.awt.image.BufferedImage;

/**
 * Created by Alvo on 05.05.2014.
 */

public class Tile {

    BufferedImage image;
    TileTypes mapBlockType;

    public Tile(BufferedImage image, TileTypes type) {
        this.image = image;
        this.mapBlockType = type;
    }

    public Tile(TileTypes type) {
        this(null, type);
    }

    public BufferedImage getImage() {
        return image;
    }

    public TileTypes getType() {
        return mapBlockType;
    }
}
