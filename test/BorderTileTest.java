import org.junit.Assert;
import org.junit.Test;

public class BorderTileTest {

    @Test
    public void testBorderTileCreation() {
        // Create a new BorderTile at position (1, 1)
        BorderTile borderTile = new BorderTile(1, 1);

        // Verify that the row and column are set correctly
        Assert.assertEquals("Row should be 1", 1, borderTile.getRow());
        Assert.assertEquals("Column should be 1", 1, borderTile.getCol());

        // Verify that the tile is not part of the enemy path
        Assert.assertFalse("Border tile should not be an enemy tile", borderTile.isEnemyTile());

        // Verify that the tile is not an entrance or exit
        Assert.assertFalse("Border tile should not be an entrance", borderTile.isEntrance());
        Assert.assertFalse("Border tile should not be an exit", borderTile.isExit());
    }

    @Test
    public void testBorderTileType() {
        // Create a new BorderTile
        BorderTile borderTile = new BorderTile(0, 0);

        // Verify that the type of the tile is "border"
        Assert.assertEquals("Tile type should be 'border'", "border", borderTile.getType());
    }

    @Test
    public void testBorderTileImage() {
        // Create a new BorderTile
        BorderTile borderTile = new BorderTile(2, 2);

        // Verify that the image is not null and correctly assigned
        Assert.assertNotNull("Tile image should not be null", borderTile.getTileImage());
    }
}
