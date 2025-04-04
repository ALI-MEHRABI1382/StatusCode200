public abstract class Player {
    protected int playerId;
    protected String team;
    protected Element currentElement;

    public void move(Element newElement) {
        if (newElement.isStandable()) {
            currentElement = newElement;
            newElement.setOccupied(true, this);
            System.out.println("Moved to " + newElement.getClass().getSimpleName());
        }
    }
}