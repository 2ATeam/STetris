package def.game;

import java.util.Observable;

public class Stats extends Observable {

    private int level;
    private long speed;
    private long score;
    private long multiplier;

    public Stats(int level, long speed) {
        this.level = level;
        this.speed = speed;
        multiplier = 1;
    }

    public Stats() {
        this(1, Config.spawnFrequency);
        setChanged();
    }

    public void increaseScore(int clearedLines) {
        score += (clearedLines * Config.lineCost) + Config.lineCost * multiplier;
        if (score - Config.levelScoreLimit * level >= 0)
            levelUp();
        setChanged();
        notifyObservers();
    }

    private void levelUp() {
        level++;
        speed -= Config.speedIncrement;
        multiplier *= 2;
        setChanged();
        notifyObservers();
    }

    public long getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public long getSpeed() {
        return speed;
    }



    @Override
    public String toString() {
        return "Level: " + level + "\nSpeed: " + speed + "\nScore: " + score + "\nMultiplier: x" + multiplier;
    }

    public long getMultiplier() {
        return multiplier;
    }
}