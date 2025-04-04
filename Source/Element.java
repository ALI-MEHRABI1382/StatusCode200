public abstract class Element {
    protected boolean isOperational; // Tracks if element is functional (Analysis 4.3.13)
    protected boolean isOccupied;    // Indicates player presence (Analysis 4.3.13)
    protected Player associatedPlayer; // Links player to element (Analysis 4.3.13)

    // Checks if element is operational, used in water flow simulation (Analysis 4.3.13)
    public boolean checkOperational() { return isOperational; }

    // Updates occupancy status when player moves (Planning 5.2.3)
    public void setOccupied(boolean occupied, Player player) {
        this.isOccupied = occupied;
        this.associatedPlayer = player;
    }

    public Player getAssociatedPlayer() { return associatedPlayer; }
    public void updateStatus(boolean operational) { this.isOperational = operational; }
    public boolean isStandable() { return true; } // Defines movable elements (Analysis 4.3.13)
}
