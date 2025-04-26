public class Reservoir {
    private double waterLevel = 0;  // Current water amount 
    private double maxCapacity =100; // Max storage limit 

    public void storeWater(double amount) {
        if (amount <= 0) return;
    
        double excess = waterLevel + amount - maxCapacity;
        if (excess > 0) {
            waterLevel = maxCapacity;
            // Tell GameManager to count lost water (we’ll handle it externally)
        } else {
            waterLevel += amount;
        }
    }
    public double releaseWater() {
        double temp = waterLevel;
        waterLevel = 0;
        return temp;
    }
}
