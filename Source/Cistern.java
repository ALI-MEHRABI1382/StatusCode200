import java.util.ArrayList;
import java.util.List;

public class Cistern extends ActiveElement {
    private double storedWater;     // Water collected (Analysis 4.3.10)
    private List<Pipe> pipeInventory; // Available pipes (Analysis 4.3.10)
    private List<Pump> pumpInventory; // Available pumps (Analysis 4.3.10)

    // Initializes cistern with inventories (Analysis 4.1.5)
    public Cistern() {
        type = "cistern";
        pipeInventory = new ArrayList<>();
        pumpInventory = new ArrayList<>();
    }

    public void storeWater(double amount) {
        storedWater += amount;
    }

    public double getStoredWater() {
        return storedWater;
    }
    
    public Pipe manufacturePipe() { return new Pipe(); }
    public Pump manufacturePump() { return new Pump(); }
}
