package def.visualization;

import def.game.Materials;
import def.game.TileTypes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static def.game.Materials.TRANSPARENT_MATERIAL;
import static def.game.TileTypes.*;

public final class TilesetProcessor {

    private static TilesetProcessor instance;
    private BufferedImage tileset;
    private ArrayList<BufferedImage> slicedTileset;
    private HashMap<Materials, Tile> tilePool;

    public void loadTileset(String tilesetPath) {
        try {
            tileset = loadImage(tilesetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TilesetProcessor getInstance() {
        if (instance == null) {
            instance = new TilesetProcessor();
        }
        return instance;
    }

    /**
     * Fill the tile pool with chunks, sliced form the original texture atlas.
     */
    private void fillTilePool() {
        tilePool = new HashMap<>();
        final Materials[] materials = Materials.values();
        for (int i = 0; i < materials.length; i++) {
            final TileTypes tileType = materials[i].equals(TRANSPARENT_MATERIAL) ? FREE : BLOCK;
            tilePool.put(materials[i], new Tile(getChunkAt(i), tileType));
        }
    }

    /**
     * Split the image atlas into chunks(tiles) of given width and height.
     * @param rows rows of tiles amount.
     * @param colls columns of tiles amount.
     * @param chunkWidth chunk width.
     * @param chunkHeight chunk height.
     */
    public void splitIntoChunks(int rows, int colls, int chunkWidth, int chunkHeight) {
        ArrayList<BufferedImage> chunks = new ArrayList<>();
        BufferedImage chunk;
        Graphics2D g2d;
        int chunkRowPos, chunkCollPos;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colls; j++) {
                chunk = new BufferedImage(chunkWidth, chunkHeight, tileset.getType());
                chunkCollPos = j * chunkWidth + chunkWidth;
                chunkRowPos = i * chunkHeight + chunkHeight;
                g2d = chunk.createGraphics();
                g2d.drawImage(tileset, 0, 0, chunkWidth, chunkHeight,
                        j * chunkWidth, i * chunkHeight,
                        chunkCollPos, chunkRowPos, null);
                g2d.dispose();
                chunks.add(chunk);
            }
        }
        slicedTileset = chunks;
        fillTilePool();
    }

    private BufferedImage loadImage(String source) throws IOException {
        BufferedImage tmp = ImageIO.read(new File(source));
        int width = tmp.getWidth();
        int height = tmp.getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img.getGraphics().drawImage(tmp, 0, 0, null);
        return img;
    }

    public BufferedImage getChunkAt(int index) {
        try {
            return slicedTileset.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return slicedTileset.get(0);
        }
    }

    public Tile getTileForMaterial(Materials material) {
        return tilePool.get(material);
    }
}