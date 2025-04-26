public class Spring extends ActiveElement {
    private double waterOutputRate = 10.0; // units per turn

    public Spring() { type = "spring"; }

    // Generates water for system (Planning 5.1.2.12)
    public double generateWater() { return waterOutputRate; }

    
}
