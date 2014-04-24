package def.game;

public class Figure {

    private int columns, rows;
    private TileTypes[][] mask;

    private Figure(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public static Figure createFigure(FigureTypes type) {
        Figure figure = null;
        switch (type) {
            case L_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new TileTypes[][]{{TileTypes.BLOCK, TileTypes.BLOCK, TileTypes.BLOCK},
                                                {TileTypes.BLOCK, TileTypes.FREE, TileTypes.FREE}};
                break;
            }
            case J_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new TileTypes[][]{{TileTypes.BLOCK, TileTypes.FREE, TileTypes.FREE},
                                                {TileTypes.BLOCK, TileTypes.BLOCK, TileTypes.BLOCK}};
                break;
            }
            case Z_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new TileTypes[][]{{TileTypes.BLOCK, TileTypes.BLOCK, TileTypes.FREE},
                                                {TileTypes.FREE, TileTypes.BLOCK, TileTypes.BLOCK}};
                break;
            }
            case S_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new TileTypes[][]{{TileTypes.FREE, TileTypes.BLOCK, TileTypes.BLOCK},
                                                {TileTypes.BLOCK, TileTypes.BLOCK, TileTypes.FREE}};
                break;
            }
            case T_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new TileTypes[][]{{TileTypes.FREE, TileTypes.BLOCK, TileTypes.FREE},
                                                {TileTypes.BLOCK, TileTypes.BLOCK, TileTypes.BLOCK}};
                break;
            }
            case O_SHAPE: {
                figure = new Figure(2, 2);
                figure.mask = new TileTypes[][]{{TileTypes.BLOCK, TileTypes.BLOCK},
                                                {TileTypes.BLOCK, TileTypes.BLOCK}};
                break;
            }
            case I_SHAPE: {
                figure = new Figure(4, 1);
                figure.mask = new TileTypes[][]{{TileTypes.BLOCK, TileTypes.BLOCK, TileTypes.BLOCK, TileTypes.BLOCK}};
                break;
            }
        }
        return figure;
    }

    public void rotateClockwise() {
        TileTypes[][] newFig = new TileTypes[columns][rows];
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

    public void rotateCClockwise() {

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public TileTypes[][] getMask() {
        return mask;
    }
}