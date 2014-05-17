package def;

import def.game.Config;
import def.visualization.STetrisWindow;

public class Core {
    public static void main(String[] args) {
        Config.load();
        new STetrisWindow();
    }
}
