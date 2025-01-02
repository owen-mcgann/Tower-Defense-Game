/**
 * Represents a tile of type "land" in a grid or map.
 * This class extends the {@link Tile} class and sets specific properties for land tiles.
 */
public class LandTile extends Tile {

    /** The type of the tile, which is "land". */
    private String type = "land";

    /**
     * Constructs a {@code LandTile} object with specified row and column coordinates.
     * The tile image is set to represent land, and the properties for whether it is an enemy tile
     * or an exit tile are set to {@code false}.
     *
     * @param row The row position of the tile.
     * @param col The column position of the tile.
     */
    public LandTile(int row, int col) {
        super("Images/TileSprites/land grass pixel art.png", row, col, false, false, "land");
        // Set isEnemyTile and isExit to false
    }

    /**
     * Returns the type of this tile.
     *
     * @return The type of the tile, which is "land".
     */
    @Override
    public String getType() {
        return type;
    }
}
