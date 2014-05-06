package def.game;


import def.visualization.Canvas;
import def.visualization.TilesetProcessor;


public class STetris {

    private TileMap map;
    private STController controller;
    private Canvas canvas;
    private final String tilesetPath = "tilesets/tileset.png";

    public STetris() {
        TilesetProcessor.getInstance().loadTileset(tilesetPath);
        TilesetProcessor.getInstance().splitIntoChunks(4, 6, 64, 64);
        map = new TileMap(20, 10);
        controller = new STController(map);
    }

    public STController getController() {
        return controller;
    }

    public TileMap getMap() {
        return map;
    }

    public void mainLoop() {
        long lastDropTime = System.currentTimeMillis();
        final long spawnFrequency = 1000L;
        spawnFigure();

        while (true) {
            if (System.currentTimeMillis() - lastDropTime > spawnFrequency) {
                lastDropTime = System.currentTimeMillis();
                tick();
            }
        }
    }

    private void tick() {
        if(!controller.willOverlap(Directions.DOWN)) {
            controller.moveFigure(Directions.DOWN);
        }
        else {
            checkLines();
            spawnFigure();
        }
        canvas.repaint();
    }

    private void checkLines() {
        int blocksInLine;
        int i = map.getRowsAmount() - 1;
        do {
            blocksInLine = 0;
            for (int j = 0; j < map.getCollsAmount(); j++) {
                if (map.getTile(i, j).getType() == TileTypes.BLOCK) ++blocksInLine;
            }
            if (blocksInLine == map.getCollsAmount()) {
                controller.clearLine(i);
            }
            else --i;
        } while (i >= 0 && blocksInLine > 0);
    }

    public void spawnFigure() {
        controller.addFigure(Figure.createFigure(FigureTypes.getRandom()));
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
