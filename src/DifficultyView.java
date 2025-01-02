import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The DifficultyView class represents a screen where the user can select the difficulty level
 * for the game. It displays buttons for different difficulty levels and a background image.
 */
public class DifficultyView extends JPanel {

    /** Array of buttons representing difficulty options and the back button. */
    private JButton[] buttons;

    /** The background image displayed on the DifficultyView screen. */
    private Image backgroundImage;

    /** The scaled version of the background image to fit the panel. */
    private Image scaledBackgroundImage;

    /** The JFrame containing the DifficultyView panel. */
    private JFrame frame;

    /** A reference to the Questions object used in the game. */
    private Questions questions;

    /**
     * Constructs a DifficultyView panel with buttons for selecting game difficulty
     * and a background image.
     *
     * @param questions The Questions object used for the game session.
     */
    public DifficultyView(Questions questions) {
        this.questions = questions;  // Store the questions passed to DifficultyView
        backgroundImage = new ImageIcon("Images/LevelScreen.png").getImage();  // Load the background image

        frame = new JFrame("Select Difficulty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);  // Set frame size based on WelcomeScreenView
        setLayout(null);

        // Initialize the buttons array
        buttons = new JButton[5];
        String[] buttonImages = {
                "Images/EasyButton.png",
                "Images/MediumButton.png",
                "Images/HardButton.png",
                "Images/ExpertButton.png",
                "Images/BackButton.png"
        };

        // Button dimensions
        int buttonWidth = 250;
        int buttonHeight = 120;

        // Background images for different game difficulties
        String[] gameBackgrounds = {
                "Images/GameViewBackground.png",   // Easy map background
                "Images/GameViewBackground.png",   // Medium map background
                "Images/GameViewBackground.png",   // Hard map background
                "Images/GameViewBackground.png"    // Expert map background
        };

        // Map types corresponding to difficulty levels
        String[] mapTypes = {
                "easy",    // Easy map type
                "normal",  // Medium map type
                "hard",    // Hard map type
                "extreme"  // Expert map type
        };

        // Create the buttons and add action listeners
        for (int i = 0; i < buttons.length; i++) {
            ImageIcon originalIcon = new ImageIcon(buttonImages[i]);
            Image scaledImage = originalIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            buttons[i] = new JButton(scaledIcon);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setOpaque(false);

            // Set button position
            int buttonY = 150 + i * 150;  // Vertical position for each button
            buttons[i].setBounds(575, buttonY, buttonWidth, buttonHeight);

            // Add action listeners for difficulty buttons
            if (i < 4) {
                String backgroundPath = gameBackgrounds[i];  // Background image path for this difficulty
                String mapType = mapTypes[i];  // Map type corresponding to this difficulty

                buttons[i].addActionListener(e -> {
                    try {
                        new GameView(backgroundPath, questions, mapType);  // Open GameView with selected settings
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();  // Close the difficulty selection window
                });
            } else {
                // Add action listener for the back button
                buttons[i].addActionListener(e -> {
                    new WorldMapView();  // Navigate back to WorldMapView
                    frame.dispose();  // Close the current window
                });
            }

            add(buttons[i]);  // Add the button to the panel
        }

        // Add the DifficultyView panel to the frame
        frame.add(this);
        frame.setVisible(true);
    }

    /**
     * Paints the background image on the panel. The image is scaled to fit the panel size.
     *
     * @param g The Graphics object used to draw the background image.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (scaledBackgroundImage == null) {
            scaledBackgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        }

        g.drawImage(scaledBackgroundImage, 0, 0, this);  // Draw the scaled background image
    }

    /**
     * Gets the JFrame containing this DifficultyView.
     *
     * @return The JFrame used by the DifficultyView.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets the array of buttons used in this DifficultyView.
     *
     * @return An array of JButton objects representing difficulty levels and the back button.
     */
    public JButton[] getButtons() {
        return buttons;
    }
}
