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
    private STetris tetris;
    private STController controller;

    public STetrisWindow() {
        setTitle("Swing Tetris");
        setResizable(false);
        setUndecorated(true);
        setContentPane(pnlRootPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tetris = new STetris();
        controller = tetris.getController();
        tetris.setGameField(gameField);
        gameField.setMap(tetris.getMap());
        tetris.getPlayerStats().addObserver((StatsField) pnlStats);
        tetris.getPlayerStats().notifyObservers();
        addKeyListener(new KeyListener());
        setVisible(true);
        setSize(Config.mapWidth * Config.blockSize + pnlStats.getWidth(), Config.mapHeight * Config.blockSize);
        setLocationRelativeTo(null);
        tetris.mainLoop();
    }

    private void createUIComponents() {
        pnlCanvas = new GameField(); // set the gameField
        gameField = (GameField)pnlCanvas; // we just need to communicate with pnlSurface as with gameField, so we morph it.
        pnlStats = new StatsField();
    }

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
