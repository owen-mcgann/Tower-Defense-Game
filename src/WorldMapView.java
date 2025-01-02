import javax.swing.*;
import java.awt.*;

/**
 * Represents the World Map view of the application.
 * This screen provides options to navigate to different categories of questions and includes a back button to return to the welcome screen.
 */
public class WorldMapView extends JPanel {
    private JButton[] buttons;  // Array of buttons for different categories and navigation
    private Image backgroundImage;  // Background image for the World Map view
    private JFrame frame;  // The main window frame for the World Map view

    /**
     * Constructs and initializes the WorldMapView.
     * Sets up the JFrame, background image, and buttons for various categories and navigation.
     */
    public WorldMapView() {
        // Load the background image
        backgroundImage = new ImageIcon("Images/MapScreen.png").getImage();

        frame = new JFrame("World Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);  // Set the size and location of the frame
        setLayout(null);  // Use no layout manager

        buttons = new JButton[4];

        // Button 1: Math
        buttons[0] = createButton("Images/MathButton.png", 200, 100);
        buttons[0].setBounds(375, 300, 150, 100);  // Set position for Math button

        // Button 2: Geography
        buttons[1] = createButton("Images/GeographyButton.png", 200, 100);
        buttons[1].setBounds(760, 380, 150, 100);  // Set position for Geography button

        // Button 3: Chemistry
        buttons[2] = createButton("Images/ScienceButton.png", 200, 100);
        buttons[2].setBounds(925, 620, 150, 100);  // Set position for Chemistry button

        // Button 4: Back Button
        buttons[3] = createButton("Images/BackButton.png", 200, 100);
        buttons[3].setBounds(80, 80, 150, 100);  // Set position for Back button

        // Add the buttons and their actions
        buttons[0].addActionListener(e -> {
            // Load Math questions from the database and pass them to DifficultyView
            Questions mathQuestions = new MathQuestions();
            new DifficultyView(mathQuestions);  // Pass the math questions to DifficultyView
            frame.dispose();  // Close the current window
        });

        buttons[1].addActionListener(e -> {
            // Load Geography questions from the database and pass them to DifficultyView
            Questions geographyQuestions = new GeographyQuestions();
            new DifficultyView(geographyQuestions);  // Pass the geography questions to DifficultyView
            frame.dispose();  // Close the current window
        });

        buttons[2].addActionListener(e -> {
            // Load Chemistry questions from the database and pass them to DifficultyView
            Questions chemistryQuestions = new ChemistryQuestions();
            new DifficultyView(chemistryQuestions);  // Pass the chemistry questions to DifficultyView
            frame.dispose();  // Close the current window
        });

        buttons[3].addActionListener(e -> {
            new WelcomeScreenView();  // Open the Welcome Screen view
            frame.dispose();  // Close the current window
        });

        // Add each button to the panel
        for (JButton button : buttons) {
            add(button);
        }

        frame.add(this);
        frame.setVisible(true);  // Make the frame visible
    }

    /**
     * Creates a JButton with a resized image and no border.
     *
     * @param imagePath The path to the image file for the button icon.
     * @param width The width to scale the button image to.
     * @param height The height to scale the button image to.
     * @return The JButton created with the specified image.
     */
    private JButton createButton(String imagePath, int width, int height) {
        ImageIcon buttonIcon = new ImageIcon(imagePath);
        Image scaledImage = buttonIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(resizedIcon);
        button.setOpaque(false);                   // Make the button background transparent
        button.setContentAreaFilled(false);        // Disable the content area filling
        button.setBorderPainted(false);            // Disable the button's border
        button.setFocusPainted(false);             // Disable the focus border when clicked
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Gets the JFrame for this view.
     *
     * @return The JFrame for the World Map view.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets the array of buttons for this view.
     *
     * @return An array of JButton objects used in the World Map view.
     */
    public JButton[] getButtons() {
        return buttons;
    }

    /**
     * The main method to launch the WorldMapView.
     * This creates an instance of the WorldMapView class to display the World Map view.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new WorldMapView();
    }
}
