package def.game;

import java.util.Random;

public enum FigureTypes {
    L_SHAPE,
    J_SHAPE,
    Z_SHAPE,
    S_SHAPE,
    T_SHAPE,
    O_SHAPE,
    I_SHAPE;

    private static Random rnd = new Random(System.currentTimeMillis());

    public static FigureTypes getRandom() {
        int index = rnd.nextInt(values().length);
        return values()[index];
    }
}
