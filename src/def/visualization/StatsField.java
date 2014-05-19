package def.visualization;

import def.game.STetris;
import def.game.Stats;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class StatsField extends JPanel implements Observer {

    private GameField nextFigureField;

    private JLabel lblScore;
    private JLabel lblLevel;
    private JLabel lblSpeed;
    private JLabel lblMult;

    private final String scoreLblTxt = "Score:";
    private final String levelLblTxt = "Level:";
    private final String speedLblTxt = "Speed:";
    private final String multLblTxt = "Mult: x";

    public StatsField() {
        super();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        nextFigureField = new GameField();

        lblScore = new JLabel(scoreLblTxt);
        lblLevel = new JLabel(levelLblTxt);
        lblSpeed = new JLabel(speedLblTxt);
        lblMult = new JLabel(multLblTxt);
        add(lblScore);
        add(lblLevel);
        add(lblSpeed);
        add(lblMult);
    }

    public void initNextFigureField(){
        nextFigureField.setBlockSize(16);
        add(nextFigureField);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof STetris) {
            STetris tetris;
            tetris = (STetris) o;
            Stats stats;
            stats = tetris.getPlayerStats();
            nextFigureField.setMap(tetris.getNextFigureMap());
            nextFigureField.repaint();
            lblScore.setText(String.format("%s %d", scoreLblTxt, stats.getScore()));
            lblLevel.setText(String.format("%s %d", levelLblTxt, stats.getLevel()));
            lblSpeed.setText(String.format("%s %.1f", speedLblTxt, stats.getSpeed()));
            lblMult.setText(String.format("%s%.2f", multLblTxt, stats.getMultiplier()));
        }
    }
}