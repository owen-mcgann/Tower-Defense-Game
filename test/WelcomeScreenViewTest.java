import org.junit.Test;
import org.junit.Assert;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WelcomeScreenViewTest {

    @Test
    public void WelcomeScreenTests() {
        // Create an instance of WelcomeScreenView
        WelcomeScreenView welcomeScreenView = new WelcomeScreenView();

        // Access the JFrame using the getter
        JFrame frame = welcomeScreenView.getFrame();
        Assert.assertNotNull("JFrame should not be null", frame);
        Assert.assertEquals("The JFrame title should be 'Welcome Screen'", "Welcome Screen", frame.getTitle());
        System.out.println("JFrame is successfully created with the title 'Welcome Screen'.");

        // Test the play button exists inside the frame
        JButton playButton = welcomeScreenView.getButton();
        Assert.assertNotNull("Play button should be created", playButton);
        System.out.println("Play button is successfully created.");

        // Check button's transparency settings
        Assert.assertFalse("Button content area should not be filled", playButton.isContentAreaFilled());
        Assert.assertFalse("Button should not be opaque", playButton.isOpaque());
        Assert.assertFalse("Button should not have a border painted", playButton.isBorderPainted());
        System.out.println("Play button has correct transparency settings.");

        // Check button's location and size
        Assert.assertEquals("Button X position is incorrect", 500, playButton.getX());
        Assert.assertEquals("Button Y position is incorrect", 570, playButton.getY());
        Assert.assertEquals("Button width is incorrect", 350, playButton.getWidth());
        Assert.assertEquals("Button height is incorrect", 250, playButton.getHeight());
        System.out.println("Play button has correct location and size.");

        // Ensure that the button has an action listener (indicating it will perform an action)
        Assert.assertTrue("Button should have at least one action listener", playButton.getActionListeners().length > 0);
        System.out.println("Play button has an action listener.");
    }
}
