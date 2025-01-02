import org.junit.Test;
import org.junit.Assert;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WorldMapViewTest {

    @Test
    public void WorldMapViewTests() {
        // Create an instance of WorldMapView
        WorldMapView worldMapView = new WorldMapView();

        // Access the JFrame using the getter
        JFrame frame = worldMapView.getFrame();
        Assert.assertNotNull("JFrame should not be null", frame);
        Assert.assertEquals("The JFrame title should be 'World Map'", "World Map", frame.getTitle());
        System.out.println("JFrame is successfully created with the title 'World Map'.");

        // Access the buttons array using the getter
        JButton[] buttons = worldMapView.getButtons();
        Assert.assertNotNull("Buttons array should not be null", buttons);
        Assert.assertEquals("There should be 4 buttons", 4, buttons.length);
        System.out.println("Buttons array is correctly created with 4 buttons.");

        // Test each button's properties
        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            Assert.assertNotNull("Button should not be null", button);
            Assert.assertFalse("Button content area should not be filled", button.isContentAreaFilled());
            Assert.assertFalse("Button should not be opaque", button.isOpaque());
            Assert.assertFalse("Button should not have a border painted", button.isBorderPainted());
            System.out.println("Button " + (i + 1) + " has correct transparency settings.");
        }

        // Check the Math button's properties
        Assert.assertEquals("Math button X position is incorrect", 375, buttons[0].getX());
        Assert.assertEquals("Math button Y position is incorrect", 300, buttons[0].getY());
        Assert.assertEquals("Math button width is incorrect", 150, buttons[0].getWidth());
        Assert.assertEquals("Math button height is incorrect", 100, buttons[0].getHeight());
        System.out.println("Math button has correct location and size.");

        // Check the Geography button's properties
        Assert.assertEquals("Geography button X position is incorrect", 760, buttons[1].getX());
        Assert.assertEquals("Geography button Y position is incorrect", 380, buttons[1].getY());
        Assert.assertEquals("Geography button width is incorrect", 150, buttons[1].getWidth());
        Assert.assertEquals("Geography button height is incorrect", 100, buttons[1].getHeight());
        System.out.println("Geography button has correct location and size.");

        // Check the Chemistry button's properties
        Assert.assertEquals("Chemistry button X position is incorrect", 925, buttons[2].getX());
        Assert.assertEquals("Chemistry button Y position is incorrect", 620, buttons[2].getY());
        Assert.assertEquals("Chemistry button width is incorrect", 150, buttons[2].getWidth());
        Assert.assertEquals("Chemistry button height is incorrect", 100, buttons[2].getHeight());
        System.out.println("Chemistry button has correct location and size.");

        // Check the Back button's properties
        Assert.assertEquals("Back button X position is incorrect", 80, buttons[3].getX());
        Assert.assertEquals("Back button Y position is incorrect", 80, buttons[3].getY());
        Assert.assertEquals("Back button width is incorrect", 150, buttons[3].getWidth());
        Assert.assertEquals("Back button height is incorrect", 100, buttons[3].getHeight());
        System.out.println("Back button has correct location and size.");
    }
}
