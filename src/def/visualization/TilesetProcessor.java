package def.visualization;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class TilesetProcessor {

    private BufferedImage tileset;
    private ArrayList<BufferedImage> slicedTileset;

    public TilesetProcessor(String tilesetPath) {
        try {
            tileset = loadImage(tilesetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BufferedImage> splitIntoChunks(int rows, int colls, int chunkWidth, int chunkHeight) {
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
        return chunks;
    }

    private BufferedImage loadImage(String source) throws IOException {
        BufferedImage tmp = ImageIO.read(new File(source));
        int width = tmp.getWidth();
        int height = tmp.getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img.getGraphics().drawImage(tmp, 0, 0, null);
        return img;
    }

    public BufferedImage getTileAt(int index) {
        return slicedTileset.get(index);
    }

    public void setTileset(BufferedImage tileset) {
        this.tileset = tileset;
    }
}