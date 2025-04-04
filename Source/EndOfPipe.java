public class EndOfPipe {
    private ActiveElement connectedElement; // Element it's connected to (Analysis 4.3.11)
    private boolean isConnected;            // Connection status (Analysis 4.3.11)
    private Pipe parentPipe;                // Links to parent pipe (Analysis 4.3.11)

    public void connect(ActiveElement element) {}
    public void disconnect() {}
    public boolean isLeaking() { return !isConnected; }
    public ActiveElement getConnectedElement() { return connectedElement; }
}
