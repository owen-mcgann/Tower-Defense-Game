import java.util.Scanner;
import javax.swing.*;

/**
 * The main entry point of the application.
 * This class initializes the application and launches the welcome screen view.
 */
public class Main {

    /**
     * The main method that serves as the entry point for the application.
     * It creates a {@link Scanner} object for reading input and then launches
     * the {@link WelcomeScreenView} using the Swing event dispatch thread.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create a Scanner object (currently not used in this version of the application)
        Scanner scanner = new Scanner(System.in);

        // Schedule the creation and showing of the WelcomeScreenView on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new WelcomeScreenView());
    }
}