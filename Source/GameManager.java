import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Player> players;       // All players (Analysis 4.3.4)
    private List<ActiveElement> activeElements; // All active elements (Analysis 4.3.4)
    private double waterDelivered;      // Tracks plumber score (Analysis 4.3.4)
    private double waterLost;           // Tracks saboteur score (Analysis 4.3.4)
    private Timer timer;                // Game duration (Analysis 4.3.4)
    private Player currentPlayer;       // Tracks whose turn it is
    private Plumber plumber;            // Plumber instance
    private Saboteur saboteur;          // Saboteur instance
    private Menu menu;                  // Reference to Menu for UI calls
    private List<Pipe> pipes = new ArrayList<>();

    public GameManager() {
        players = new ArrayList<>();
        activeElements = new ArrayList<>();
        timer = new Timer(this);
        menu = new Menu(this); // Menu needs GameManager reference
    }
    public Menu getMenu() { return menu; }
    // Starts the game with team selection
    public void startGame(int teamChoice) {
        plumber = new Plumber();
        saboteur = new Saboteur();
        players.add(plumber);
        players.add(saboteur);

        if (teamChoice == 1) {
            currentPlayer = plumber;
            System.out.println("You are a Plumber! Your turn is first.");
        } else if (teamChoice == 2) {
            currentPlayer = saboteur;
            System.out.println("You are a Saboteur! Your turn is first.");
        } else {
            throw new IllegalArgumentException("Invalid team choice");
        }
        playTurn(); // Start the first turn
    }

    // Main turn loop
    private void playTurn() {
        int choice = menu.showPlayerMenu(currentPlayer);
        handlePlayerTurn(choice);

    }

    // Handles the current player's turn based on menu choice
    public void handlePlayerTurn(int choice) {
        if (choice == 1) {
            int moveChoice = menu.showMoveMenu(currentPlayer);
            handleMoveChoice(moveChoice);
        } else if (choice == 2) {
            exitGame();
        } else {
            System.out.println("Invalid option, try again.");
            playTurn(); // Retry the turn
        }
    }

    // Handles move menu choice
    public void handleMoveChoice(int choice) {
        if (currentPlayer instanceof Plumber) {
            switch (choice) {
                case 1: // Pipe
                    int pipeChoice = menu.showPipeActions(currentPlayer);
                    handlePipeAction(pipeChoice);
                    break;
                case 2: // Pump
                    int pumpChoice = menu.showPumpActions(currentPlayer);
                    handlePumpAction(pumpChoice);
                    break;
                case 3: // Cistern
                    int cisternChoice = menu.showCisternActions();
                    handleCisternAction(cisternChoice);
                    break;
                case 4: exitGame(); return;
                default: System.out.println("Invalid option, try again."); playTurn();
            }
        } else if (currentPlayer instanceof Saboteur) {
            switch (choice) {
                case 1: // Pipe
                    int pipeChoice = menu.showPipeActions(currentPlayer);
                    handlePipeAction(pipeChoice);
                    break;
                case 2: // Pump
                    int pumpChoice = menu.showPumpActions(currentPlayer);
                    handlePumpAction(pumpChoice);
                    break;
                case 3: exitGame(); return;
                default: System.out.println("Invalid option, try again."); playTurn();
            }
        }
    }

    // Handles pipe actions
  // Handles pipe actions
  public void handlePipeAction(int choice) {
    // ⚡ Instead of creating new pipe, pick one from pipes list
    Pipe pipe = pipes.get(0); // Always select pipe1 for now
    currentPlayer.move(pipe);

    if (currentPlayer instanceof Plumber) {
        Plumber p = (Plumber) currentPlayer;
        switch (choice) {
            case 1:
                p.fixLeakingPipe(pipe);
                switchPlayer();
                playTurn();
                break;
            case 2:
                exitGame();
                break;
            default:
                System.out.println("Invalid option, try again.");
                playTurn();
        }
    } else {
        Saboteur s = (Saboteur) currentPlayer;
        switch (choice) {
            case 1:
                s.puncturePipe(pipe);
                switchPlayer();
                playTurn();
                break;
            case 2:
                exitGame();
                break;
            default:
                System.out.println("Invalid option, try again.");
                playTurn();
        }
    }
}


// Handles pump actions
public void handlePumpAction(int choice) {
    Pump pump = new Pump();
    currentPlayer.move(pump);
    if (currentPlayer instanceof Plumber) {
        Plumber p = (Plumber) currentPlayer;
        switch (choice) {
            case 1: 
                p.fixBrokenPump(pump); 
                switchPlayer(); 
                playTurn(); 
                break;
            case 2: 
                p.extendPipeSystem(new EndOfPipe(), pump); 
                switchPlayer(); 
                playTurn(); 
                break;
            case 3: 
                exitGame(); // Exit program instead of switching player
                break;
            default: 
                System.out.println("Invalid option, try again."); 
                playTurn();
        }
    } else {
        Saboteur s = (Saboteur) currentPlayer;
        switch (choice) {
            case 1: 
                s.changePumpDirection(pump); 
                switchPlayer(); 
                playTurn(); 
                break;
            case 2: 
                exitGame(); // Exit program instead of switching player
                break;
            default: 
                System.out.println("Invalid option, try again."); 
                playTurn();
        }
    }
}

// Handles cistern actions (Plumber only)
public void handleCisternAction(int choice) {
    Cistern cistern = new Cistern();
    currentPlayer.move(cistern);
    Plumber p = (Plumber) currentPlayer;
    switch (choice) {
        case 1: 
            p.pickUpNewPipe(); 
            switchPlayer(); 
            playTurn(); 
            break;
        case 2: 
            p.pickUpNewPump(); 
            switchPlayer(); 
            playTurn(); 
            break;
        case 3: 
            exitGame(); // Exit program instead of switching player
            break;
        default: 
            System.out.println("Invalid option, try again."); 
            playTurn();
    }
}

    // Switches to the next player
    // Switches to the next player
public void switchPlayer() {
    if (currentPlayer instanceof Plumber) {
        currentPlayer = saboteur;
    } else {
        currentPlayer = plumber;
    }

    // ⏳ Count down the turn-based timer
    timer.startCountdown();
    if (timer.isTimeUp()) {
        determineWinner();
        exitGame();
    }

    simulateWaterFlow();
}


    // Exits the game
    public void exitGame() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public Player getCurrentPlayer() { return currentPlayer; }
    public List<Player> getPlayers() { return players; }
    public void simulateWaterFlow() {
        
        for (ActiveElement ae : activeElements) {
            if (ae instanceof Spring spring && spring.outgoingPipe != null) {
                double generated = spring.generateWater();
                 
                spring.outgoingPipe.transferWater(generated, this); // ✅ pass GameManager reference
            }
        }
    
        for (ActiveElement ae : activeElements) {
            if (ae instanceof Pump pump) {
                pump.transferWater(this); // ✅ pass GameManager reference
            }
        }
    
        calculateWaterDeliveredAndLost();

        System.out.println("Water delivered so far: " + waterDelivered);
        System.out.println("Water lost so far: " + waterLost);

    }
    

    public void addLostWater(double amount) {
        waterLost += amount;
    }
    public void determineWinner() {}
    public void handlePumpMalfunctions() {}
    public void monitorPipeSystem() {}
    public void calculateWaterDeliveredAndLost() {
        waterDelivered = 0;
        for (ActiveElement ae : activeElements) {
            if (ae instanceof Cistern cistern) {
                waterDelivered += cistern.getStoredWater(); // you may need to write this getter
            }
        }
    }
    public void setupPipeSystem() {}
    public void ensureTeamsHaveMinimumPlayers() {}

    public void startGame() {
        plumber = new Plumber();
        saboteur = new Saboteur();
        players.add(plumber);
        players.add(saboteur);
    
        currentPlayer = saboteur;
        System.out.println("Saboteur starts the game!");
    
        // TEMP: Run 3 turns of water flow test
        setupTestNetwork();
        simulateWaterFlow();
        simulateWaterFlow();
        simulateWaterFlow();
    
        System.out.println("Water flow test complete.\n");
    
        // CONTINUE normal game loop
        playTurn();
    }
    



    //Additional Classes (Not in The Documnetations )
    public void setupTestNetwork() {
        Spring spring = new Spring();
        Pump pump = new Pump();
        Cistern cistern = new Cistern();
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
    
        // Set up outgoing/incoming pipes
        spring.outgoingPipe = pipe1;
        pump.incomingPipe = pipe1;
        pump.outgoingPipe = pipe2;
    
        // Ensure pipes are not leaking
        pipe1.setLeaking(false);
        pipe2.setLeaking(false);
    
        // Create EndOfPipe for both start and end of each pipe
        EndOfPipe pipe1Start = new EndOfPipe();
        EndOfPipe pipe1End = new EndOfPipe();
        EndOfPipe pipe2Start = new EndOfPipe();
        EndOfPipe pipe2End = new EndOfPipe();
    
        // Connect ends properly
        pipe1End.connect(pump);        // pipe1 ends at pump
        pipe2End.connect(cistern);     // pipe2 ends at cistern
        pipe1Start.connect(spring);    // pipe1 starts from spring
        pipe2Start.connect(pump);      // pipe2 starts from pump
    
        // Attach ends to pipes
        pipe1.startEnd = pipe1Start;
        pipe1.endEnd = pipe1End;
        pipe2.startEnd = pipe2Start;
        pipe2.endEnd = pipe2End;
    
        // Register only ActiveElements (not pipes) into activeElements
        activeElements.add(spring);
        activeElements.add(pump);
        activeElements.add(cistern);
    
        // If you want to track pipes separately, add them to pipes list
        pipes.add(pipe1);
        pipes.add(pipe2);
    
        System.out.println("✅ Test network successfully set up!");
    }
    
    
}