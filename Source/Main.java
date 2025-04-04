import java.util.*;

public class Main {
    public static void main(String[] args) {
    // Initializes game manager and menu (Analysis 4.3.14)
    GameManager gameManager = new GameManager();
    Menu menu = new Menu(gameManager);
    
    // Starts the game by showing the menu (Planning 5.2.1)
    menu.displayMenu();
    }
}
