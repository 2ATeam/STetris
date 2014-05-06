package def.game;

import def.visualization.Tile;
import def.visualization.TilesetProcessor;

import java.util.ArrayList;

public class TileMap {

    private ArrayList<Tile[]> map;
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
            map.add(i, new Tile[collsAmount]);
            for (int j = 0; j < collsAmount; j++) {
                map.get(i)[j] = TilesetProcessor.getInstance().getTileAt(Materials.TRANSPARENT_MATERIAL.ordinal());
            }
        }
    }

    public int getCollsAmount() {
        return collsAmount;
    }

    public int getRowsAmount() {
        return rowsAmount;
    }

    public Tile getTile(int row, int column) {
        return map.get(row)[column];
    }

    public void setTile(int row, int column, Tile tile) {
        map.get(row)[column] = tile;
    }

    public void removeRow(int index){
        map.remove(index);
        --rowsAmount;
    }
    public void addRow(Tile tile){
        map.add(0, new Tile[collsAmount]);
        for (int i = 0; i < collsAmount; i++) {
            map.get(0)[i] = tile;
        }
        ++rowsAmount;
    }
}
