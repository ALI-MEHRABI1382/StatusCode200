public class Pump extends ActiveElement {
    protected Pipe incomingPipe;
    protected Pipe outgoingPipe;
    private Reservoir reservoir;

    private static int idCounter = 1;
    private int id;

    public Pump() {
        type = "pump";
        reservoir = new Reservoir();
        isOperational = true;
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public Reservoir getReservoir() {
        return reservoir;
    }

    public void transferWater(GameManager gm) {
        System.out.println("DEBUG: Pump#1 starting transfer cycle.");

        if (!isOperational || incomingPipe == null || outgoingPipe == null) {
            System.out.println("DEBUG: Pump#1 not operational or disconnected. Skipping transfer.");
            return;
        }

        incomingPipe.transferWater(gm);

        if (!outgoingPipe.isLeaking() && outgoingPipe != null) {
            double waterToSend = reservoir.releaseWater("Pump#1");
            outgoingPipe.transferWater(waterToSend, gm);
            System.out.println("DEBUG: Pump#1 working - pushed " + waterToSend + " units through outgoing pipe.");
        } else {
            double generated = 10;
            double excess = reservoir.storeWaterAndReturnExcess(generated, "Pump#1");
            if (excess > 0) {
                gm.addLostWater(excess);
                System.out.println("DEBUG: Pump#1 stored water but Reservoir overflowed! Lost " + excess + " units.");
            } else {
                System.out.println("DEBUG: Pump#1 stored " + generated + " units in Reservoir.");
            }
        }
    }

    public void breakDown() {
        isOperational = false;
    }

    public void repair() {
        isOperational = true;
        System.out.println("The pump has been fixed successfully.");
    }

    public void changeDirection() {
        Pipe temp = incomingPipe;
        incomingPipe = outgoingPipe;
        outgoingPipe = temp;

        isOperational = false;
        System.out.println("Pump direction changed and now requires repair!");
    }
}
