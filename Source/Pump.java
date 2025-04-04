public class Pump extends ActiveElement {
    private Pipe incomingPipe;  // Input pipe (Analysis 4.3.6)
    private Pipe outgoingPipe;  // Output pipe (Analysis 4.3.6)
    private Reservoir reservoir;// Temporary storage (Analysis 4.3.6)

    // Initializes pump with reservoir (Analysis 4.1.3)
    public Pump() {
        type = "pump";
        reservoir = new Reservoir();
        isOperational = true; // Pump starts operational
    }

    // Simulates water transfer (Planning 5.1.2.12)
    public void transferWater() {
        // Placeholder for water flow logic
    }

    // Breaks pump (Planning 5.2.7)
    public void breakDown() { 
        isOperational = false; 
    }

    // Repairs pump (Planning 5.2.8)
    public void repair() { 
        isOperational = true; 
        System.out.println("The pump has been fixed successfully.");
    }

    // Changes pump direction (Planning 5.2.7)
    public void changeDirection() { 
        // Swap incoming and outgoing pipes (skeletal implementation)
        Pipe temp = incomingPipe;
        incomingPipe = outgoingPipe;
        outgoingPipe = temp;
        System.out.println("The pump direction has been changed successfully.");
    }
}