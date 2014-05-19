package def.game;

public class Stats {

    private int level;
    private long score;
    private float speed;
    private float multiplier;

    public Stats(int level, float speed) {
        this.level = level;
        this.speed = speed;
        multiplier = 1;
    }

    public Stats() {
        this(1, Config.fallingSpeed);
    }

    public void increaseScore(int clearedLines) {
        score += (clearedLines * clearedLines * Config.lineCost) + Config.lineCost * multiplier;
        if (score - Config.levelScoreLimit * Math.exp(level) >= 0)
            levelUp();

        score += (clearedLines * Config.lineCost) + Config.lineCost * multiplier;
        if (score - Config.levelScoreLimit * level >= 0)
            levelUp();
    }

    private void levelUp() {
        level++;
        speed -= Config.speedIncrement * 2 / level;
        multiplier += multiplier / level;
    }

    private void test(int levels){
        while(level <= levels){
            double score = Config.levelScoreLimit * level * level;
            System.out.println("\n\nLevel: " + level);
            System.out.println("Score to next level: " + score);
            System.out.println("Speed: " + speed);
            System.out.println("Multiplier: x" + multiplier);
            levelUp();
        }
    }

    public long getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Level: " + level + "\nSpeed: " + speed + "\nScore: " + score + "\nMultiplier: x" + multiplier;
    }

    public float getMultiplier() {
        return multiplier;
    }
}