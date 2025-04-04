import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private GameManager gameManager;    // Links to game logic
    private List<String> optionsAvailable; // Initial menu options
    private Scanner scanner;            // For user input

    public Menu(GameManager gm) {
        gameManager = gm;
        optionsAvailable = new ArrayList<>();
        optionsAvailable.add("1) Start Game");
        optionsAvailable.add("2) Exit");
        scanner = new Scanner(System.in);
    }

    // Displays initial menu and returns choice
    public void displayMenu() {
        System.out.println("Welcome to Pipes in the Desert!");
        for (String option : optionsAvailable) {
            System.out.println(option);
        }
        System.out.print("Enter a number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice == 1) {
            System.out.println("Choose your team: 1) Plumber 3) Saboteur");
            int teamChoice = scanner.nextInt();
            scanner.nextLine();
            gameManager.startGame(teamChoice);
        } else if (choice == 2) {
            gameManager.exitGame();
        } else {
            System.out.println("Invalid option, exiting.");
            gameManager.exitGame();
        }
    }

    // Shows the current player's menu and returns choice
    public int showPlayerMenu(Player player) {
        System.out.println("\n" + (player instanceof Plumber ? "Plumber's" : "Saboteur's") + " Turn:");
        System.out.println("1) Move");
        System.out.println("2) Exit");
        System.out.print("Enter a number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // Shows move options and returns choice
    public int showMoveMenu(Player player) {
        System.out.println("\nMove to:");
        System.out.println("1) Pipe");
        System.out.println("2) Pump");
        if (player instanceof Plumber) {
            System.out.println("3) Cistern");
            System.out.println("4) Exit");
        } else {
            System.out.println("3) Exit");
        }
        System.out.print("Enter a number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // Shows pipe actions and returns choice
    public int showPipeActions(Player player) {
        System.out.println("\nPipe Actions:");
        if (player instanceof Plumber) {
            System.out.println("1) Fix Pipe");
        } else {
            System.out.println("1) Puncture Pipe");
        }
        System.out.println("2) Exit");
        System.out.print("Enter a number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // Shows pump actions and returns choice
    public int showPumpActions(Player player) {
        System.out.println("\nPump Actions:");
        if (player instanceof Plumber) {
            System.out.println("1) Fix Pump");
            System.out.println("2) Extend Pipe System");
            System.out.println("3) Exit");
        } else {
            System.out.println("1) Change Pump Direction");
            System.out.println("2) Exit");
        }
        System.out.print("Enter a number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // Shows cistern actions and returns choice (Plumber only)
    public int showCisternActions() {
        System.out.println("\nCistern Actions:");
        System.out.println("1) Pick Up New Pipe");
        System.out.println("2) Pick Up New Pump");
        System.out.println("3) Exit");
        System.out.print("Enter a number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}