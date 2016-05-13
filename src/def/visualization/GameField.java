package def.visualization;

import def.game.TileMap;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {

    private TileMap map;
    private int blockSize;
    private static final int mapOffset = -22; // compensating the window decoration.

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < map.getRowsAmount(); i++) {
            for (int j = 0; j < map.getCollsAmount(); j++) {
                g2d.drawImage(map.getTile(i, j).getImage(), j * blockSize + mapOffset,
                                                            i * blockSize + mapOffset,
                                                            blockSize, blockSize, null);
            }
        }
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
}
