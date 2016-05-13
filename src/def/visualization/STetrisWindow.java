package def.visualization;

import def.game.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class STetrisWindow extends JFrame{
    private JPanel pnlRootPane;
    private JPanel pnlCanvas;
    private JPanel pnlStats;

    private GameField gameField;
    private StatsField statsField;
    private STetris tetris;
    private STController controller;

    public STetrisWindow() {
        tetris = new STetris();
        tetris.addObserver((StatsField) pnlStats);
        tetris.setGameField(gameField);
        statsField.initNextFigureField();
        gameField.setMap(tetris.getMap());
        gameField.setBlockSize(Config.blockSize);
        controller = tetris.getGameController();

        setTitle("Swing Tetris");
        setResizable(false);
        setContentPane(pnlRootPane);
        setUndecorated(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new KeyListener());
        setVisible(true);
        setSize(Config.mapWidth * Config.blockSize + pnlStats.getWidth(), Config.mapHeight * Config.blockSize);
        setLocationRelativeTo(null);

        tetris.mainLoop();
    }

    private void createUIComponents() {
        pnlCanvas = new GameField(); // set the gameField
        gameField = (GameField)pnlCanvas; // we just need to communicate with pnlCanvas as with gameField, so we morph it.
        pnlStats = new StatsField();
        statsField = (StatsField) pnlStats; // same as gameField
    }

    /**
     * Inner class for handing the user input.
     */
    private class KeyListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:{
                        if (!controller.willOverlap(Directions.RIGHT))
                            controller.moveFigure(Directions.RIGHT);
                        break;
                    }
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:{
                        if (!controller.willOverlap(Directions.LEFT))
                            controller.moveFigure(Directions.LEFT);
                        break;
                    }
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:{
                        if (!controller.willOverlap(Directions.DOWN))
                            controller.moveFigure(Directions.DOWN);
                        break;
                    }
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:{
                        if (!controller.willOverlap(Directions.UP))
                            controller.moveFigure(Directions.UP);
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        if(controller.willRotate(false))
                            controller.rotate(false);
                        break;
                    }
                    case KeyEvent.VK_ENTER:{
                        tetris.spawnFigure();
                        break;
                    }
                }
            gameField.repaint();
        }
    }
}
