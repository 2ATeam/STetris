package def.game;


public class STetris {

    private TileMap map;
    private STController controller;

    public STetris() {
        map = new TileMap(20, 10);
        controller = new STController(map);
    }

    public STController getController() {
        return controller;
    }

    public TileMap getMap() {
        return map;
    }

    public void spawnFigure() {
        controller.addFigure(Figure.createFigure(FigureTypes.J_SHAPE));
    }
}
