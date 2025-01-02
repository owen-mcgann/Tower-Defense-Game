/**
 * Tile factory which creates Tile objects based on the specified type.
 */
public class TileFactory {

    /**
     * Creates a Tile object based on the provided type and its position.
     * @param type Determines the type of tile to create (e.g., land, water, enemy, border).
     * @param row  The row position of the tile.
     * @param col  The column position of the tile.
     * @param isEntrance Indicates if the tile is an entrance (used for enemy tiles).
     * @param isExit Indicates if the tile is an exit.
     * @return The created Tile object.
     */
    public Tile createTile(String type, int row, int col, boolean isEntrance, boolean isExit) {
        switch (type.toLowerCase()) {
            case "land":
                return new LandTile(row, col);
            case "water":
                return new WaterTile(row, col);
            case "enemy":
                return new EnemyTile(row, col, isEntrance, isExit);
            case "border":
                return new BorderTile(row, col);
            default:
                // WARNING! Returns a border tile by default
                return new BorderTile(row, col);
        }
    }
}
