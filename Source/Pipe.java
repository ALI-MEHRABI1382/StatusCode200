public class Pipe extends Element {
    protected EndOfPipe startEnd;
    protected EndOfPipe endEnd;
    private boolean isLeaking;

    private static int idCounter = 1;
    private int id;

    public Pipe() {
        isOperational = true;
        isLeaking = false;
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public void transferWater(double amount, GameManager gm) {
        System.out.println("DEBUG: Pipe#1 transferring " + amount + " units.");

        if (!isOperational || isLeaking || startEnd == null || endEnd == null) {
            gm.addLostWater(amount);
            System.out.println("DEBUG: Pipe#1 transfer failed (leak or not connected). Lost " + amount + " units.");
            return;
        }

        ActiveElement destination = endEnd.getConnectedElement();
        if (destination instanceof Cistern cistern) {
            cistern.storeWater(amount);
            System.out.println("DEBUG: Pipe#1 delivered " + amount + " units to Cistern!");
        } else if (destination instanceof Pump pump) {
            double overflow = pump.getReservoir().storeWaterAndReturnExcess(amount, "Pump#1");
            if (overflow > 0) {
                gm.addLostWater(overflow);
                System.out.println("DEBUG: Pump#1's Reservoir overflowed! Lost " + overflow + " units.");
            } else {
                System.out.println("DEBUG: Pipe#1 stored " + amount + " units in Pump#1's Reservoir.");
            }
        } else {
            gm.addLostWater(amount);
            System.out.println("DEBUG: Pipe#1 transfer failed (no valid destination). Lost " + amount + " units.");
        }
    }

    public void transferWater(GameManager gm) {
        transferWater(10, gm);
    }

    public void repair() {
        isLeaking = false;
        isOperational = true;
        System.out.println("The pipe has been fixed successfully.");
    }

    public boolean isLeaking() {
        return isLeaking;
    }

    public void setLeaking(boolean leaking) {
        isLeaking = leaking;
    }
}
