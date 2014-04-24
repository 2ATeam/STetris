package def.visualization;

import def.game.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class STetrisWindow extends JFrame{
    private JPanel pnlRootPane;
    private JPanel pnlCanvas;
    private Canvas canvas;
    private STetris tetris;
    private STController controller;

    public STetrisWindow() {
        setTitle("Swing Tetris");
        setSize(450, 800);
        setContentPane(pnlRootPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tetris = new STetris();
        controller = tetris.getController();
        canvas.setMap(tetris.getMap());
        addKeyListener(new KeyListener());
        setVisible(true);
    }

    private void createUIComponents() {
        pnlCanvas = new Canvas(); // set the canvas
        canvas = (Canvas)pnlCanvas; // we just need to communicate with pnlSurface as with canvas, so we morph it.
    }

    private class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_D:{
                            controller.moveFigure(Directions.RIGHT);
                            break;
                        }
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_A:{
                            controller.moveFigure(Directions.LEFT);
                            break;
                        }
                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_S:{
                            controller.moveFigure(Directions.DOWN);
                            break;
                        }
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:{
                            controller.moveFigure(Directions.UP);
                            break;
                        }
                        case KeyEvent.VK_SPACE: {
                            controller.rotate(true);
                            break;
                        }
                        case KeyEvent.VK_ENTER:{
                            tetris.spawnFigure();
                            break;
                        }
                    }
            canvas.repaint();
        }
    }
}
