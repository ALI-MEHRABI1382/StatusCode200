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

    public GameManager() {
        players = new ArrayList<>();
        activeElements = new ArrayList<>();
        timer = new Timer();
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
    public void handlePipeAction(int choice) {
        Pipe pipe = new Pipe();
        currentPlayer.move(pipe);
        if (currentPlayer instanceof Plumber) {
            Plumber p = (Plumber) currentPlayer;
            switch (choice) {
                case 1: p.fixLeakingPipe(pipe); switchPlayer(); playTurn(); break;
                case 2: switchPlayer(); playTurn(); break;
                default: System.out.println("Invalid option, try again."); playTurn();
            }
        } else {
            Saboteur s = (Saboteur) currentPlayer;
            switch (choice) {
                case 1: s.puncturePipe(pipe); switchPlayer(); playTurn(); break;
                case 2: switchPlayer(); playTurn(); break;
                default: System.out.println("Invalid option, try again."); playTurn();
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
                case 1: p.fixBrokenPump(pump); switchPlayer(); playTurn(); break;
                case 2: p.extendPipeSystem(new EndOfPipe(), pump); switchPlayer(); playTurn(); break;
                case 3: switchPlayer(); playTurn(); break;
                default: System.out.println("Invalid option, try again."); playTurn();
            }
        } else {
            Saboteur s = (Saboteur) currentPlayer;
            switch (choice) {
                case 1: s.changePumpDirection(pump); switchPlayer(); playTurn(); break;
                case 2: switchPlayer(); playTurn(); break;
                default: System.out.println("Invalid option, try again."); playTurn();
            }
        }
    }

    // Handles cistern actions (Plumber only)
    public void handleCisternAction(int choice) {
        Cistern cistern = new Cistern();
        currentPlayer.move(cistern);
        Plumber p = (Plumber) currentPlayer;
        switch (choice) {
            case 1: p.pickUpNewPipe(); switchPlayer(); playTurn(); break;
            case 2: p.pickUpNewPump(); switchPlayer(); playTurn(); break;
            case 3: switchPlayer(); playTurn(); break;
            default: System.out.println("Invalid option, try again."); playTurn();
        }
    }

    // Switches to the next player
    public void switchPlayer() {
        if (currentPlayer instanceof Plumber) {
            currentPlayer = saboteur;
        } else {
            currentPlayer = plumber;
        }
    }

    // Exits the game
    public void exitGame() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public Player getCurrentPlayer() { return currentPlayer; }
    public List<Player> getPlayers() { return players; }
    public void simulateWaterFlow() {}
    public void determineWinner() {}
    public void handlePumpMalfunctions() {}
    public void monitorPipeSystem() {}
    public void calculateWaterDeliveredAndLost() {}
    public void setupPipeSystem() {}
    public void ensureTeamsHaveMinimumPlayers() {}
}