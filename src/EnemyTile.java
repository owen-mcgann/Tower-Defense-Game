/**
 * The EnemyTile class represents a tile that is part of the enemy's path in the game.
 * It extends the Tile class and is marked as an enemy tile.
 */
public class EnemyTile extends Tile {

    /** The type of the tile, always set to "enemy". */
    private String type = "enemy";

    /**
     * Constructs an EnemyTile with the specified row, column, entrance, and exit status.
     *
     * @param row       The row position of the tile.
     * @param col       The column position of the tile.
     * @param isEntrance A boolean indicating if this tile is the entrance for enemies.
     * @param isExit    A boolean indicating if this tile is the exit for enemies.
     */
    public EnemyTile(int row, int col, boolean isEntrance, boolean isExit) {
        // Initialize the EnemyTile with an enemy path image, row, col, isEnemyTile always set to true, and custom exit status.
        super("Images/TileSprites/enemypath pixel art.png", row, col, true, isExit, "enemy");
        this.setEntrance(isEntrance);  // Set the tile as an entrance if isEntrance is true
    }

    /**
     * Returns the type of the tile, which is "enemy".
     *
     * @return The string "enemy".
     */
    @Override
    public String getType() {
        return type;
    }
}
