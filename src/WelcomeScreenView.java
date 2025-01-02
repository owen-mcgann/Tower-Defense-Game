import javax.swing.*;
import java.awt.*;

/**
 * Represents the welcome screen view of the application.
 * This screen provides an entry point to the main application by displaying a button that navigates to the World Map view.
 */
public class WelcomeScreenView {
    private JFrame frame;  // The main window frame for the welcome screen
    private JButton button;  // The button that triggers navigation to the World Map view

    /**
     * Constructs and initializes the WelcomeScreenView.
     * Sets up the JFrame, adds a background image, and creates a button that leads to the World Map view.
     */
    public WelcomeScreenView() {
        frame = new JFrame("Welcome Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setScreenSize(frame);

        // Create a JPanel with a background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Images/HomeScreen.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null);

        // Play button info
        ImageIcon playButtonIcon = new ImageIcon("Images/PlayButton.png");
        Image playButtonImage = playButtonIcon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedPlayButtonIcon = new ImageIcon(playButtonImage);

        button = new JButton(resizedPlayButtonIcon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            new WorldMapView();  // Open the World Map view
            frame.dispose();  // Close the welcome screen window
        });

        button.setBounds(500, 570, 350, 250);
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);

    }

    /**
     * Sets the size and location of the JFrame.
     *
     * @param frame The JFrame to set the size and location for.
     */
    public static void setScreenSize(JFrame frame) {
        frame.setSize(1400, 900);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
    }

    /**
     * Gets the JFrame for this view.
     *
     * @return The JFrame for the welcome screen.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets the JButton for the welcome screen.
     *
     * @return The JButton used to navigate to the World Map view.
     */
    public JButton getButton() {
        return button;
    }

    /**
     * The main method to launch the WelcomeScreenView.
     * This creates an instance of the WelcomeScreenView class to display the welcome screen.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new WelcomeScreenView();
    }
}
