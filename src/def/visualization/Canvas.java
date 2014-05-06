package def.visualization;

import def.game.TileMap;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    private TileMap map;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int mapOffset = 32;
        final int blockSize = 32;
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < map.getRowsAmount(); i++) {
            for (int j = 0; j < map.getCollsAmount(); j++) {
                g2d.drawImage(map.getTile(i, j).getImage(), j * mapOffset + mapOffset,
                                                            i * mapOffset + mapOffset,
                                                            blockSize, blockSize, null);
            }
        }
    }

    public void setMap(TileMap map) {
        this.map = map;
    }
}
