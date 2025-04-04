public class Plumber extends Player {
    public Plumber() { team = "Plumber"; }

    public void pickUpNewPipe() { System.out.println("New pipe collected."); }
    public void pickUpNewPump() {System.out.println("A new pump has been picked up."); }
    public void fixBrokenPump(Pump pump) { pump.repair(); }
    public void fixLeakingPipe(Pipe pipe) { pipe.repair(); }
    public void extendPipeSystem(EndOfPipe end, ActiveElement element) { System.out.println("The pipe system has been extended successfully.");}
    public void insertNewPump(Pipe pipe, Pump pump) {System.out.println("A new pump has been inserted into the pipe.");}
}
