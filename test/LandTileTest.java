import org.junit.Assert;
import org.junit.Test;

public class LandTileTest {

    @Test
    public void testLandTileCreation() {
        // Create a new LandTile at position (3, 6)
        LandTile landTile = new LandTile(3, 6);

        // Verify that the row and column are set correctly
        Assert.assertEquals("Row should be 3", 3, landTile.getRow());
        Assert.assertEquals("Column should be 6", 6, landTile.getCol());

        // Verify that the tile is not part of the enemy path
        Assert.assertFalse("Land tile should not be an enemy tile", landTile.isEnemyTile());

        // Verify that the tile is not an entrance or exit
        Assert.assertFalse("Land tile should not be an entrance", landTile.isEntrance());
        Assert.assertFalse("Land tile should not be an exit", landTile.isExit());
    }

    @Test
    public void testLandTileType() {
        // Create a new LandTile
        LandTile landTile = new LandTile(4, 5);

        // Verify that the type of the tile is "land"
        Assert.assertEquals("Tile type should be 'land'", "land", landTile.getType());
    }

    @Test
    public void testLandTileImage() {
        // Create a new LandTile
        LandTile landTile = new LandTile(3, 6);

        // Verify that the image is not null and correctly assigned
        Assert.assertNotNull("Tile image should not be null", landTile.getTileImage());
    }
}
