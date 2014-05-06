package def.visualization;

import def.game.Materials;
import def.game.TileTypes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class TilesetProcessor {

    private static TilesetProcessor instance;
    private BufferedImage tileset;
    private ArrayList<BufferedImage> slicedTileset;
    private Tile[] tilePool;

    public void loadTileset(String tilesetPath) {
        try {
            tileset = loadImage(tilesetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TilesetProcessor getInstance() {
        if (instance == null)
            instance = new TilesetProcessor();

        return instance;
    }

    private void fillTilePool() {
        tilePool = new Tile[Materials.values().length];
        tilePool[Materials.L_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.L_MATERIAL.ordinal()), TileTypes.BLOCK);
        tilePool[Materials.J_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.J_MATERIAL.ordinal()), TileTypes.BLOCK);
        tilePool[Materials.Z_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.Z_MATERIAL.ordinal()), TileTypes.BLOCK);
        tilePool[Materials.S_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.S_MATERIAL.ordinal()), TileTypes.BLOCK);
        tilePool[Materials.T_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.T_MATERIAL.ordinal()), TileTypes.BLOCK);
        tilePool[Materials.O_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.O_MATERIAL.ordinal()), TileTypes.BLOCK);
        tilePool[Materials.I_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.I_MATERIAL.ordinal()), TileTypes.BLOCK);
        tilePool[Materials.TRANSPARENT_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.TRANSPARENT_MATERIAL.ordinal()), TileTypes.FREE);
    }

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

    public Tile getTileAt(int index) {
        return tilePool[index];
    }
}