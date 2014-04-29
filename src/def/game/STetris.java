package def.game;


import def.visualization.Canvas;

import java.util.Random;

public class STetris {

    private TileMap map;
    private STController controller;
    private Canvas canvas;
    private static final Random rnd = new Random(System.currentTimeMillis());

    public STetris() {
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
            spawnFigure();
        }
        canvas.repaint();
    }

    public void spawnFigure() {

        controller.addFigure(Figure.createFigure(FigureTypes.J_SHAPE));
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
