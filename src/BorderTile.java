/**
 * The BorderTile class represents a specific type of tile in the game that
 * serves as a border, which players cannot cross. It extends the Tile class.
 */
public class BorderTile extends Tile {

    /** The type of the tile, always set to "border". */
    private String type = "border";

    /**
     * Constructs a BorderTile with the specified row and column position.
     *
     * @param row The row position of the tile.
     * @param col The column position of the tile.
     */
    public BorderTile(int row, int col) {
        // Initialize the BorderTile with the image path, row, col, isEnemyTile and isExit set to false.
        super("Images/TileSprites/new border.png", row, col, false, false, "border");
    }

    /**
     * Returns the type of the tile, which is "border".
     *
     * @return The string "border".
     */
    @Override
    public String getType() {
        return type;
    }
}
