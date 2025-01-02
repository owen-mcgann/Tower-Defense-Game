import org.junit.Assert;
import org.junit.Test;

public class EnemyTileTest {

    @Test
    public void testEnemyTileCreation() {
        // Create a normal enemy tile without entrance or exit
        EnemyTile normalEnemy = new EnemyTile(0, 0, false, false);

        // Create an entrance tile
        EnemyTile entranceTile = new EnemyTile(1, 0, true, false);

        // Create an exit tile
        EnemyTile exitTile = new EnemyTile(2, 0, false, true);

        // Test that the normal enemy tile is not an entrance or exit
        Assert.assertFalse("Normal enemy tile should not be an entrance", normalEnemy.isEntrance());
        Assert.assertFalse("Normal enemy tile should not be an exit", normalEnemy.isExit());

        // Test that the entrance tile is correctly marked as an entrance
        Assert.assertTrue("Entrance tile should be an entrance", entranceTile.isEntrance());
        Assert.assertFalse("Entrance tile should not be an exit", entranceTile.isExit());

        // Test that the exit tile is correctly marked as an exit
        Assert.assertFalse("Exit tile should not be an entrance", exitTile.isEntrance());
        Assert.assertTrue("Exit tile should be an exit", exitTile.isExit());
    }

    @Test
    public void testEnemyTileType() {
        // Create an enemy tile
        EnemyTile enemyTile = new EnemyTile(0, 0, false, false);

        // Test that the type of the tile is "enemy"
        Assert.assertEquals("Tile type should be 'enemy'", "enemy", enemyTile.getType());
    }

    @Test
    public void testSetEntranceAndExit() {
        // Create an enemy tile
        EnemyTile enemyTile = new EnemyTile(0, 0, false, false);

        // Set the tile as an entrance and verify
        enemyTile.setEntrance(true);
        Assert.assertTrue("Tile should be set as entrance", enemyTile.isEntrance());

        // Set the tile as an exit and verify
        enemyTile.setExit(true);
        Assert.assertTrue("Tile should be set as exit", enemyTile.isExit());

        // Ensure that setting entrance to false works correctly
        enemyTile.setEntrance(false);
        Assert.assertFalse("Tile should not be an entrance anymore", enemyTile.isEntrance());

        // Ensure that setting exit to false works correctly
        enemyTile.setExit(false);
        Assert.assertFalse("Tile should not be an exit anymore", enemyTile.isExit());
    }
}
