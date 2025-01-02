import org.junit.Assert;
import org.junit.Test;

public class WaterTileTest {

    @Test
    public void testWaterTileCreation() {
        // Create a new WaterTile at position (5, 5)
        WaterTile waterTile = new WaterTile(5, 5);

        // Test that the row and column positions are set correctly
        Assert.assertEquals("Row should be 5", 5, waterTile.getRow());
        Assert.assertEquals("Column should be 5", 5, waterTile.getCol());

        // Test that the tile is not part of the enemy path (isEnemyTile should be false)
        Assert.assertFalse("Water tile should not be an enemy tile", waterTile.isEnemyTile());

        // Test that the tile is not an entrance or exit
        Assert.assertFalse("Water tile should not be an entrance", waterTile.isEntrance());
        Assert.assertFalse("Water tile should not be an exit", waterTile.isExit());
    }

    @Test
    public void testWaterTileType() {
        // Create a new WaterTile
        WaterTile waterTile = new WaterTile(3, 4);

        // Test that the type of the tile is "water"
        Assert.assertEquals("Tile type should be 'water'", "water", waterTile.getType());
    }

    @Test
    public void testWaterTileImage() {
        // Create a new WaterTile
        WaterTile waterTile = new WaterTile(3, 4);

        // Test that the correct image is assigned (based on the path provided in the constructor)
        Assert.assertNotNull("Tile image should not be null", waterTile.getTileImage());
    }
}
