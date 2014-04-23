package def.game;

import java.awt.*;

public class STController {

    private TileMap map;
    private Figure currentFigure;
    private Point curFigurePos;

    public STController(TileMap map) {
        this.map = map;
    }

    public void addFigure(Figure figure) {
        currentFigure = figure;
        curFigurePos = new Point(map.getCollsAmount() / 2, 1);
        projectFigure();
    }

    private void morphMaskRegion(TileTypes target){
        int projectedX, projectedY;
        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y + (currentFigure.getRows()- 1 - i);
                map.setTile(projectedY, projectedX, target);
            }
        }
    }

    public void projectFigure(){
        TileTypes[][] figureMask = currentFigure.getMask();
        int projectedX, projectedY;
        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y + (currentFigure.getRows() - 1 - i);
                map.setTile(projectedY, projectedX, figureMask[i][j]);
            }
        }
    }

    public void moveFigure(Directions direction) {
        int curX = curFigurePos.x;
        int curY = curFigurePos.y;
        morphMaskRegion(TileTypes.FREE);
        switch (direction) {
            case UP: {
                curFigurePos.setLocation(curX, (curY > 0 ? curY - 1 : curY));
                break;
            }
            case DOWN: {
                curFigurePos.setLocation(curX, (curY < map.getRowsAmount() - currentFigure.getRows() ? curY + 1 : curY));
                break;
            }
            case LEFT: {
                curFigurePos.setLocation((curX > 0 ? curX - 1 : curX), curY);
                break;
            }
            case RIGHT: {
                curFigurePos.setLocation((curX < map.getCollsAmount() - currentFigure.getColumns() ? curX + 1 : curX), curY);
                break;
            }/**/
        }
        projectFigure();
    }

    public void rotate(boolean clockwise){
        morphMaskRegion(TileTypes.FREE);
        if (clockwise) currentFigure.rotateClockwise();
        else currentFigure.rotateCClockwise();
        projectFigure();
    }

    public TileMap getMap() {
        return map;
    }
}