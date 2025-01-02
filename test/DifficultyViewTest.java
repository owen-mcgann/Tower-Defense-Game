import org.junit.Test;
import org.junit.Assert;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DifficultyViewTest {

    @Test
    public void testDifficultyViewComponents() {
        // Create an instance of Questions (mock or real, depending on your setup)
        Questions mockQuestions = new MathQuestions();  // Replace with appropriate subclass

        // Create an instance of DifficultyView
        DifficultyView difficultyView = new DifficultyView(mockQuestions);

        // Access the JFrame using the getter
        JFrame frame = difficultyView.getFrame();
        Assert.assertNotNull("JFrame should not be null", frame);
        Assert.assertEquals("The JFrame title should be 'Select Difficulty'", "Select Difficulty", frame.getTitle());
        System.out.println("JFrame is successfully created with the title 'Select Difficulty'.");

        // Access the buttons array
        JButton[] buttons = difficultyView.getButtons();
        Assert.assertNotNull("Buttons array should not be null", buttons);
        Assert.assertEquals("There should be 5 buttons", 5, buttons.length);  // Update for 5 buttons
        System.out.println("Buttons array is correctly created with 5 buttons.");

        // Test each button's properties
        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            Assert.assertNotNull("Button should not be null", button);
            Assert.assertFalse("Button content area should not be filled", button.isContentAreaFilled());
            Assert.assertFalse("Button should not be opaque", button.isOpaque());
            Assert.assertFalse("Button should not have a border painted", button.isBorderPainted());
            System.out.println("Button " + (i + 1) + " has correct transparency settings.");
        }

        // Check button positions and sizes for the difficulty buttons
        Assert.assertEquals("Easy button X position is incorrect", 575, buttons[0].getX());
        Assert.assertEquals("Easy button Y position is incorrect", 150, buttons[0].getY());
        Assert.assertEquals("Easy button width is incorrect", 250, buttons[0].getWidth());
        Assert.assertEquals("Easy button height is incorrect", 120, buttons[0].getHeight());
        System.out.println("Easy button has correct location and size.");

        Assert.assertEquals("Medium button X position is incorrect", 575, buttons[1].getX());
        Assert.assertEquals("Medium button Y position is incorrect", 300, buttons[1].getY());
        Assert.assertEquals("Medium button width is incorrect", 250, buttons[1].getWidth());
        Assert.assertEquals("Medium button height is incorrect", 120, buttons[1].getHeight());
        System.out.println("Medium button has correct location and size.");

        Assert.assertEquals("Hard button X position is incorrect", 575, buttons[2].getX());
        Assert.assertEquals("Hard button Y position is incorrect", 450, buttons[2].getY());
        Assert.assertEquals("Hard button width is incorrect", 250, buttons[2].getWidth());
        Assert.assertEquals("Hard button height is incorrect", 120, buttons[2].getHeight());
        System.out.println("Hard button has correct location and size.");

        Assert.assertEquals("Expert button X position is incorrect", 575, buttons[3].getX());
        Assert.assertEquals("Expert button Y position is incorrect", 600, buttons[3].getY());
        Assert.assertEquals("Expert button width is incorrect", 250, buttons[3].getWidth());
        Assert.assertEquals("Expert button height is incorrect", 120, buttons[3].getHeight());
        System.out.println("Expert button has correct location and size.");

        // Check the back button's properties
        Assert.assertEquals("Back button X position is incorrect", 575, buttons[4].getX());
        Assert.assertEquals("Back button Y position is incorrect", 750, buttons[4].getY());
        Assert.assertEquals("Back button width is incorrect", 250, buttons[4].getWidth());
        Assert.assertEquals("Back button height is incorrect", 120, buttons[4].getHeight());
        System.out.println("Back button has correct location and size.");

        // Ensure the buttons have action listeners (to transition to the next screen)
        for (int i = 0; i < buttons.length; i++) {
            Assert.assertTrue("Button " + (i + 1) + " should have at least one action listener", buttons[i].getActionListeners().length > 0);
        }
        System.out.println("All buttons have action listeners.");
    }
}
