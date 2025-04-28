public class Saboteur extends Player {
    public Saboteur() { team = "Saboteur"; }

    // Punctures pipe, causes leak (Planning 5.2.5)
    public void puncturePipe(Pipe pipe) { 
        pipe.setLeaking(true); // ✅ Mark the pipe as leaking!
        System.out.println("Pipe punctured, now leaking.");
    }
    public void changePumpDirection(Pump pump) { pump.changeDirection(); }
}
