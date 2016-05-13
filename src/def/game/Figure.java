package def.game;

import def.visualization.Tile;
import def.visualization.TilesetProcessor;

import static def.game.Materials.*;

public class Figure {

    private int columns, rows;
    private Tile[][] mask;

    private Figure(int columns, int rows) {
        this.columns = columns;        
        this.rows = rows;
    }

    public static Figure createFigure(FigureTypes type) {
        Figure figure = null;
        switch (type) {
            case L_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileForMaterial(L_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(L_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(L_MATERIAL)},
                                          { TilesetProcessor.getInstance().getTileForMaterial(L_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL)}};
                break;
            }
            case J_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileForMaterial(J_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL)},
                                          { TilesetProcessor.getInstance().getTileForMaterial(J_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(J_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(J_MATERIAL)}};
                break;
            }
            case Z_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileForMaterial(Z_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(Z_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL)},
                                          { TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(Z_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(Z_MATERIAL)}};
                break;
            }
            case S_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL),
                                           TilesetProcessor.getInstance().getTileForMaterial(S_MATERIAL),
                                           TilesetProcessor.getInstance().getTileForMaterial(S_MATERIAL)},
                                         { TilesetProcessor.getInstance().getTileForMaterial(S_MATERIAL),
                                           TilesetProcessor.getInstance().getTileForMaterial(S_MATERIAL),
                                           TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL)}};
                break;
            }
            case T_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(T_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(TRANSPARENT_MATERIAL)},
                                          { TilesetProcessor.getInstance().getTileForMaterial(T_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(T_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(T_MATERIAL)}};
                break;
            }
            case O_SHAPE: {
                figure = new Figure(2, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileForMaterial(O_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(O_MATERIAL)},
                                          { TilesetProcessor.getInstance().getTileForMaterial(O_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(O_MATERIAL)}};
                break;
            }
            case I_SHAPE: {
                figure = new Figure(4, 1);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileForMaterial(I_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(I_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(I_MATERIAL),
                                            TilesetProcessor.getInstance().getTileForMaterial(I_MATERIAL)}};
                break;
            }
        }
        return figure;
    }

    public void rotateCClockwise() {
        Tile[][] newFig = new Tile[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newFig[j][i] = mask[rows - 1 - i][j];
            }
        }
        mask = newFig;
        int x = columns;
        columns = rows;
        rows = x;
    }

    public void rotateClockwise() {

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Tile[][] getMask() {
        return mask;
    }

    @Override
    protected Object clone(){
        Figure f = new Figure(columns, rows);
        f.mask = this.mask.clone();
        return f;
    }
}