import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

public class ActiveElement extends Element{
    protected String type;           // Differentiates spring, pump, cistern (Analysis 4.3.8)
    protected List<Pipe> connectedPipes; // Tracks all connected pipes (Analysis 4.3.8)
    protected Pipe incomingPipe;     // Single input pipe (Analysis 4.3.8)
    protected Pipe outgoingPipe;     // Single output pipe (Analysis 4.3.8)

    // Constructor initializes pipe list (Analysis 4.1.1)
    public ActiveElement() {
        connectedPipes = new ArrayList<>();
    }

    // Connects pipe to active element (Planning 5.2.9)
    public void connectPipe(Pipe pipe1) {}
    public void disconnectPipe(Pipe pipe) {}
    public boolean checkIfOperational() { return isOperational; }
    
}
