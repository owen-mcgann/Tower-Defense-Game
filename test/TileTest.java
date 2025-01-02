import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.awt.Image;
import javax.swing.ImageIcon;

public class TileTest {

    // Mock subclass for Tile to implement the abstract getType() method
    class TestTile extends Tile {
        public TestTile(String imagePath, int row, int col, boolean isEnemyTile, boolean isExit, String name) {
            super(imagePath, row, col, isEnemyTile, isExit, name);
        }

        public TestTile(Image tileImage, int row, int col, boolean isEnemyTile, boolean isExit) {
            super(tileImage, row, col, isEnemyTile, isExit);
        }

        @Override
        public String getType() {
            return "test"; // Return a dummy type for testing purposes
        }
    }

    private TestTile testTile;
    private Image testImage;

    @Before
    public void setUp() {
        // Use a dummy image path and set up a mock tile for testing
        testImage = new ImageIcon("Images/TestImage.png").getImage();
        testTile = new TestTile("Images/TestImage.png", 5, 10, true, false, "TestTile");
    }

    @Test
    public void testTileInitialization() {
        // Verify that the row and column are initialized correctly
        Assert.assertEquals("Row should be 5", 5, testTile.getRow());
        Assert.assertEquals("Column should be 10", 10, testTile.getCol());

        // Verify that the tile type is "test"
        Assert.assertEquals("Tile type should be 'test'", "test", testTile.getType());

        // Verify the tile name
        Assert.assertEquals("Tile name should be 'TestTile'", "TestTile", testTile.getName());
    }

    @Test
    public void testTileImage() {
        // Verify that the image is not null and correctly assigned
        Assert.assertNotNull("Tile image should not be null", testTile.getTileImage());
    }

    @Test
    public void testEnemyTileFlag() {
        // Verify that the tile is flagged as an enemy tile
        Assert.assertTrue("Tile should be flagged as an enemy tile", testTile.isEnemyTile());
    }

    @Test
    public void testEntranceAndExitFlags() {
        // Set entrance and exit flags and verify their behavior
        testTile.setEntrance(true);
        Assert.assertTrue("Tile should be an entrance", testTile.isEntrance());

        testTile.setExit(true);
        Assert.assertTrue("Tile should be an exit", testTile.isExit());

        // Set entrance and exit to false and verify
        testTile.setEntrance(false);
        Assert.assertFalse("Tile should not be an entrance", testTile.isEntrance());

        testTile.setExit(false);
        Assert.assertFalse("Tile should not be an exit", testTile.isExit());
    }

    @Test
    public void testConstructorWithImage() {
        // Test the second constructor that takes an image directly
        TestTile tileWithImage = new TestTile(testImage, 3, 7, false, true);

        // Verify the row, column, and exit flag
        Assert.assertEquals("Row should be 3", 3, tileWithImage.getRow());
        Assert.assertEquals("Column should be 7", 7, tileWithImage.getCol());
        Assert.assertTrue("Tile should be flagged as an exit", tileWithImage.isExit());
        Assert.assertFalse("Tile should not be flagged as an enemy tile", tileWithImage.isEnemyTile());

        // Verify the image is assigned correctly
        Assert.assertEquals("Tile image should be assigned correctly", testImage, tileWithImage.getTileImage());
    }
}
