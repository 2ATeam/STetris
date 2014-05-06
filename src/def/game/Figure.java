package def.game;

import def.visualization.Tile;
import def.visualization.TilesetProcessor;

public class Figure {

    private int columns, rows;
    private Tile[][] mask;
    private TilesetProcessor tpInstance;

    private Figure(int columns, int rows) {
        this.columns = columns;        
        this.rows = rows;
        tpInstance = TilesetProcessor.getInstance();
    }

    public static Figure createFigure(FigureTypes type) {
        Figure figure = null;
        switch (type) {
            case L_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileAt(Materials.L_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.L_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.L_MATERIAL.ordinal())},
                                          { TilesetProcessor.getInstance().getTileAt(Materials.L_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal())}};
                break;
            }
            case J_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileAt(Materials.J_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal())},
                                          { TilesetProcessor.getInstance().getTileAt(Materials.J_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.J_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.J_MATERIAL.ordinal())}};
                break;
            }
            case Z_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileAt(Materials.Z_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.Z_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal())},
                                          { TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.Z_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.Z_MATERIAL.ordinal())}};
                break;
            }
            case S_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()), 
                                           TilesetProcessor.getInstance().getTileAt(Materials.S_MATERIAL.ordinal()), 
                                           TilesetProcessor.getInstance().getTileAt(Materials.S_MATERIAL.ordinal())},
                                         { TilesetProcessor.getInstance().getTileAt(Materials.S_MATERIAL.ordinal()),
                                           TilesetProcessor.getInstance().getTileAt(Materials.S_MATERIAL.ordinal()), 
                                           TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal())}};
                break;
            }
            case T_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.T_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal())},
                                          { TilesetProcessor.getInstance().getTileAt(Materials.T_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.T_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.T_MATERIAL.ordinal())}};
                break;
            }
            case O_SHAPE: {
                figure = new Figure(2, 2);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileAt(Materials.O_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.O_MATERIAL.ordinal())},
                                          { TilesetProcessor.getInstance().getTileAt(Materials.O_MATERIAL.ordinal()), 
                                            TilesetProcessor.getInstance().getTileAt(Materials.O_MATERIAL.ordinal())}};
                break;
            }
            case I_SHAPE: {
                figure = new Figure(4, 1);
                figure.mask = new Tile[][]{{TilesetProcessor.getInstance().getTileAt(Materials.I_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.I_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.I_MATERIAL.ordinal()),
                                            TilesetProcessor.getInstance().getTileAt(Materials.I_MATERIAL.ordinal())}};
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