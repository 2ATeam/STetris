package def.game;

import java.util.ArrayList;

public class TileMap {

    private ArrayList<TileTypes[]> map;
    private int collsAmount;
    private int rowsAmount;

    public TileMap(int rows, int colls) {
        rowsAmount = rows;
        collsAmount = colls;
        map = new ArrayList<>(rows);
        initMap();
    }

    public void initMap(){
        for (int i = 0; i < rowsAmount; i++) {
            map.add(i, new TileTypes[collsAmount]);
            for (int j = 0; j < collsAmount; j++) {
                map.get(i)[j] = TileTypes.FREE;
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
        return map.get(row)[column];
    }

    public void setTile(int row, int column, TileTypes type) {
        map.get(row)[column] = type;
    }

    public void removeRow(int index){
        map.remove(index);
        --rowsAmount;
    }
    public void addRow(TileTypes type){
        map.add(0, new TileTypes[collsAmount]);
        for (int i = 0; i < collsAmount; i++) {
            map.get(0)[i] = type;
        }
        ++rowsAmount;
    }
}
