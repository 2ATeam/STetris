package def.game;

import java.awt.*;

public class STController {

    private TileMap map;
    private Figure currentFigure;
    private Point curFigurePos;

    public STController(TileMap map) {
        this.map = map;
    }

    public TileMap getMap() {
        return map;
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

    public void addFigure(Figure figure) {
        currentFigure = figure;
        curFigurePos = new Point(map.getCollsAmount() / 2, 1);
        projectFigure();
    }

    private void fillMaskRegion(TileTypes target){
        int projectedX, projectedY;
        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y - (currentFigure.getRows()- 1 - i);
                if (isWithin(0, projectedX, map.getCollsAmount() - 1, true) &&
                        isWithin(0, projectedY, map.getRowsAmount() - 1, true))
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
                projectedY = curFigurePos.y - (currentFigure.getRows() - 1 - i);
                if (isWithin(0, projectedX, map.getCollsAmount() - 1, true) &&
                    isWithin(0, projectedY, map.getRowsAmount() - 1, true))
                map.setTile(projectedY, projectedX, figureMask[i][j]);
            }
        }
    }

    public void moveFigure(Directions direction) {
        if (currentFigure == null) return;
        int curX = curFigurePos.x;
        int curY = curFigurePos.y;
        fillMaskRegion(TileTypes.FREE);
        switch (direction) {
            case UP: {
                curFigurePos.setLocation(curX, (curY > currentFigure.getRows() - 1 ? curY - 1 : curY));
                break;
            }
            case DOWN: {
                curFigurePos.setLocation(curX, (curY < map.getRowsAmount() - 1 ? curY + 1 : curY));
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
        int figEndX = curFigurePos.x + currentFigure.getRows() - 1;
        int  figEndY = curFigurePos.y;
        if (!(isWithin(0, figEndX, map.getCollsAmount() - 1, true) &&
            isWithin(0, figEndY, map.getRowsAmount() - 1, true))) return;

        fillMaskRegion(TileTypes.FREE);
        if (clockwise)
            currentFigure.rotateClockwise();
        else
            currentFigure.rotateCClockwise();
        projectFigure();
    }



/** UTILS */
    private boolean isWithin(int min, int value, int max, boolean inclusive){
        if (inclusive)
            return (min <= value && value <= max);
        else
            return (min < value && value < max);
    }
}