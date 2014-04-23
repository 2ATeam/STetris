package def.game;

public class TileMap {

    private TileTypes[][] map;
    private int collsAmount;
    private int rowsAmount;

    public TileMap(int rows, int colls) {
        rowsAmount = rows;
        collsAmount = colls;
        map = new TileTypes[rowsAmount][collsAmount];
        initMap();
    }

    public void initMap(){
        for (int i = 0; i < rowsAmount; i++) {
            for (int j = 0; j < collsAmount; j++) {
                map[i][j] = TileTypes.FREE;
            }
        }
    }

    public int getCollsAmount() {
        return collsAmount;
    }

    public int getRowsAmount() {
        return rowsAmount;
    }

    public TileTypes getTile(int row, int column) {
        return map[row][column];
    }

    public void setTile(int row, int column, TileTypes type) {
        map[row][column] = type;
    }
}
