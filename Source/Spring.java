public class Spring extends ActiveElement {
    private double waterOutputRate; // Rate of water generation (Analysis 4.3.9)

    public Spring() { type = "spring"; }

    // Generates water for system (Planning 5.1.2.12)
    public double generateWater() { return waterOutputRate; }

    
}
