package def.game;

import def.visualization.Tile;
import def.visualization.TilesetProcessor;

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

    private void fillMaskRegion(Tile target){
        assert currentFigure != null;
        int projectedX, projectedY;
        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y - (currentFigure.getRows()- 1 - i);
                if (isWithin(0, projectedX, map.getCollsAmount() - 1, true) &&
                    isWithin(0, projectedY, map.getRowsAmount() - 1, true) &&
                    currentFigure.getMask()[i][j].getType() != TileTypes.FREE)
                map.setTile(projectedY, projectedX, target);
            }
        }
    }

    public void projectFigure(){
        assert currentFigure != null;
        Tile[][] figureMask = currentFigure.getMask();
        int projectedX, projectedY;
        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y - (currentFigure.getRows() - 1 - i);
                if (isWithin(0, projectedX, map.getCollsAmount() - 1, true) &&
                    isWithin(0, projectedY, map.getRowsAmount() - 1, true)&&
                    currentFigure.getMask()[i][j].getType() != TileTypes.FREE)
                map.setTile(projectedY, projectedX, figureMask[i][j]);
            }
        }
    }

    private void translateFigurePos(Directions direction) {
        if (currentFigure == null) return;
        int curX = curFigurePos.x;
        int curY = curFigurePos.y;

        switch (direction) {
            case UP: {
                curFigurePos.setLocation(curX, curY - 1);
                break;
            }
            case DOWN: {
                curFigurePos.setLocation(curX, curY + 1);
                break;
            }
            case LEFT: {
                curFigurePos.setLocation(curX - 1, curY);
                break;
            }
            case RIGHT: {
                curFigurePos.setLocation(curX + 1, curY);
                break;
            }
        }
    }

    public void moveFigure(Directions direction) {
        fillMaskRegion(TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()));
        translateFigurePos(direction);
        projectFigure();
    }

    public boolean isOverlapping() {
        assert currentFigure != null : "Fucking error";
        int projectedX;
        int projectedY;

        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y - (currentFigure.getRows() - 1 - i);

                if (!isWithin(0, projectedX, map.getCollsAmount() - 1, true) ||
                    !isWithin(0, projectedY, map.getRowsAmount() - 1, true))
                        return true;

                if (currentFigure.getMask()[i][j].getType() == TileTypes.BLOCK &&
                    map.getTile(projectedY, projectedX).getType() == TileTypes.BLOCK){
                        return true;
                }
            }
        }
        return false;
    }

    public boolean willOverlap(Directions direction) {
        assert currentFigure != null;
        Point curPos = (Point) curFigurePos.clone();
        boolean isOverlapping;
        fillMaskRegion(TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()));
        translateFigurePos(direction);
        isOverlapping = isOverlapping();
        curFigurePos = curPos;
        projectFigure();
        return isOverlapping;
    }

    public boolean willRotate(boolean clockwise) {
        assert currentFigure != null;
        int figEndX = curFigurePos.x + currentFigure.getRows() - 1;
        int figEndY = curFigurePos.y;
        Figure tmp = (Figure)currentFigure.clone();
        fillMaskRegion(TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()));
        rotateFigure(clockwise);
        boolean willOverlap = isOverlapping();
        currentFigure = tmp;
        projectFigure();
        return isWithin(0, figEndX, map.getCollsAmount() - 1, true) &&
               isWithin(0, figEndY, map.getRowsAmount() - 1, true) &&
               !willOverlap;
    }

    private void rotateFigure(boolean clockwise){
        if (clockwise)
            currentFigure.rotateClockwise();
        else
            currentFigure.rotateCClockwise();
    }

    public void rotate(boolean clockwise){
        assert currentFigure != null;
        fillMaskRegion(TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()));
        rotateFigure(clockwise);
        projectFigure();
    }

    public void clearLine(int rowIndex) {
        map.removeRow(rowIndex);
        fillMaskRegion(TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()));
    }

    public TileMap getMap() {
        return map;
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

/** UTILS */
    private boolean isWithin(int min, int value, int max, boolean inclusive){
        if (inclusive)
            return (min <= value && value <= max);
        else
            return (min < value && value < max);
    }
}