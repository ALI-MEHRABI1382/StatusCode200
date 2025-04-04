public class Timer {
    private int totalTime;     // Total game duration (Analysis 4.3.12)
    private int timeRemaining; // Time left (Analysis 4.3.12)

    public void startCountdown() {}
    public void endGame() {}
    public boolean isTimeUp() { return timeRemaining <= 0; }
    public int getTimeRemaining() { return timeRemaining; }
}
