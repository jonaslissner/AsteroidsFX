package dk.sdu.mmmi.cbse.common.data;

public class GameData {
    private final int FPS = 120;
    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private int score = 0;


    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public int getScore() {
        return score;
    }
    public void incrementScore() {
        this.score++;
    }

    public int getFPS() {
        return FPS;
    }

}
