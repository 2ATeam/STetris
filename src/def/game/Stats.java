package def.game;

public class Stats {

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
    }

    public void increaseScore(int clearedLines) {
        score += (clearedLines * Config.lineCost) + Config.lineCost * multiplier;
        if (score - Config.levelScoreLimit * level >= 0)
            levelUp();
    }

    private void levelUp() {
        level++;
        speed -= Config.speedIncrement;
        multiplier *= 2;
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
}