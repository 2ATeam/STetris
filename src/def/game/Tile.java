package def.game;

import java.awt.image.BufferedImage;

public class Tile {
    BufferedImage image;
    TileTypes type;


    public Tile(BufferedImage image, TileTypes type) {
        this.image = image;
        this.type = type;
    }

    public Tile(TileTypes type) {
        this(null, type);
    }

    public BufferedImage getImage() {
        return image;
    }

    public TileTypes getType() {
        return type;
    }
}
