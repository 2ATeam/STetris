package def.game;

import def.visualization.GameField;
import def.visualization.TilesetProcessor;


public class STetris {

    private TileMap map;
    private STController controller;
    private GameField gameField;
    private Stats playerStats;
    private boolean isStuck = false;
    private boolean isLoosed = false;

    public STetris() {
        TilesetProcessor.getInstance().loadTileset(Config.tilesetPath);
        TilesetProcessor.getInstance().splitIntoChunks(4, 6, 64, 64);
        map = new TileMap(Config.mapHeight, Config.mapWidth);
        controller = new STController(map);
        playerStats = new Stats(); // default.
    }

    public void mainLoop() {
        long lastDropTime = System.currentTimeMillis();
        spawnFigure();

        while (!isLoosed) {
            if (System.currentTimeMillis() - lastDropTime > playerStats.getSpeed()) {
                lastDropTime = System.currentTimeMillis();
                tick();
            }
        }
    }

    private void tick() {
        if(!controller.willOverlap(Directions.DOWN)) {
            controller.moveFigure(Directions.DOWN);
            isStuck = false;
        }
        else if(isStuck) {
            gameOver();
        }
        else{
            checkLines();
            spawnFigure();
            isStuck = true;
        }
        gameField.repaint();
    }

    private void gameOver() {
        isLoosed = true;
        System.out.println("GAME OVER!\n" + playerStats.toString());
    }

    private void checkLines() {
        int blocksInLine;
        int clearedLines = 0;
        int i = map.getRowsAmount() - 1;
        do {
            blocksInLine = 0;
            for (int j = 0; j < map.getCollsAmount(); j++) {
                if (map.getTile(i, j).getType() == TileTypes.BLOCK) ++blocksInLine;
            }
            if (blocksInLine == map.getCollsAmount()) {
                controller.clearLine(i);
                ++clearedLines;
            }
            else --i;
        } while (i >= 0 && blocksInLine > 0);
        if (clearedLines > 0) {
            playerStats.increaseScore(clearedLines);
            System.out.println(playerStats.toString());
        }
    }

    public void spawnFigure() {
        controller.addFigure(Figure.createFigure(FigureTypes.getRandom()));
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public STController getController() {
        return controller;
    }

    public TileMap getMap() {
        return map;
    }

    public Stats getPlayerStats() {
        return playerStats;
    }
}
