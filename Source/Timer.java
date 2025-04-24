public class Timer {
    private int totalTime;
    private int timeRemaining;
    private GameManager gameManager;

    public Timer(GameManager gm) {
        this.totalTime = 20; // for example, 20 turns
        this.timeRemaining = totalTime;
        this.gameManager = gm;
    }

    public void startCountdown() {
        timeRemaining--;
        if (isTimeUp()) {
            endGame();
        }
    }

    public void endGame() {
        System.out.println("Time is up!");
        gameManager.determineWinner(); // 🔗 now you can call this
        gameManager.exitGame();
    }

    public boolean isTimeUp() {
        return timeRemaining <= 0;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }
}

