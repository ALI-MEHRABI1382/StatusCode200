public class Pipe extends Element {
    private EndOfPipe startEnd; // Start point of pipe (Analysis 4.3.5)
    private EndOfPipe endEnd;   // End point of pipe (Analysis 4.3.5)
    private boolean isLeaking;  // Tracks leak status (Analysis 4.3.5)

    // Constructor
    public Pipe() {
        isOperational = true; // Pipes start operational
        isLeaking = false;    // Pipes start without leaks
    }

    // Simulates water transfer or loss (Planning 5.1.2.12)
    public void transferWater() {
        // Placeholder for water flow logic
    }

    // Repairs pipe, stops leak (Planning 5.2.6)
    public void repair() { 
        isLeaking = false; 
        isOperational = true;
        System.out.println("The pipe has been fixed successfully.");
    }

    // Getter for leaking status
    public boolean isLeaking() {
        return isLeaking;
    }

    // Setter for leaking (used by Saboteur)
    public void setLeaking(boolean leaking) {
        isLeaking = leaking;
    }
}