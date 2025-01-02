/**
 * Represents a tile of water on the game board.
 * This class extends the Tile class and sets specific properties for water tiles.
 */
public class WaterTile extends Tile {
    private String type = "water";

    /**
     * Constructs a new WaterTile instance.
     *
     * @param row The row position of the tile on the game board.
     * @param col The column position of the tile on the game board.
     */
    public WaterTile(int row, int col) {
        super("Images/TileSprites/water pixel art.png", row, col, false, false, "water");
        // Passes image path, row, column, and default values for isEnemyTile and isExit to the parent constructor
    }

    /**
     * Returns the type of this tile.
     *
     * @return A string representing the type of the tile, which is "water".
     */
    @Override
    public String getType() {
        return type;
    }
}
