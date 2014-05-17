package def.visualization;

import def.game.Stats;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class StatsField extends JPanel implements Observer {

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
        lblScore = new JLabel(scoreLblTxt);
        lblLevel = new JLabel(levelLblTxt);
        lblSpeed = new JLabel(speedLblTxt);
        lblMult = new JLabel(multLblTxt);
        add(lblScore);
        add(lblLevel);
        add(lblSpeed);
        add(lblMult);
    }

    @Override
    public void update(Observable o, Object arg) {
        Stats stats;
        if (o instanceof Stats) {
            stats = (Stats) o;
            lblScore.setText(String.format("%s %d", scoreLblTxt, stats.getScore()));
            lblLevel.setText(String.format("%s %d", levelLblTxt, stats.getLevel()));
            lblSpeed.setText(String.format("%s %.1f", speedLblTxt, stats.getSpeed()));
            lblMult.setText(String.format("%s%.2f", multLblTxt, stats.getMultiplier()));
        }
    }
}
