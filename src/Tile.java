import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * An individual square on the 17x17 game map.
 */
public abstract class Tile {
    private Image tileImage;  // Image of the tile
    private int row;          // Row of the tile
    private int col;          // Column of the tile
    private boolean isEnemyTile; // Flag to indicate if the tile is part of the enemy path
    private boolean isExit;      // Flag to indicate if the tile is an exit
    private boolean isEntrance;  // Flag to indicate if the tile is an entrance
    private String name;

    // Constructor that accepts the image path and sets the tile image
    public Tile(String imagePath, int row, int col, boolean isEnemyTile, boolean isExit, String name) {
        this.tileImage = new ImageIcon(imagePath).getImage();
        this.name = name != null ? name : "";
        this.row = row;
        this.col = col;
        this.isEnemyTile = isEnemyTile;
        this.isExit = isExit;
        this.name = name;
    }

    // Constructor that accepts an Image directly
    public Tile(Image tileImage, int row, int col, boolean isEnemyTile, boolean isExit) {
        this.tileImage = tileImage;
        this.row = row;
        this.col = col;
        this.isEnemyTile = isEnemyTile;
        this.isExit = isExit;
    }

    // Get the type of the tile (remains abstract, to be implemented in subclasses)
    public abstract String getType();

    // Get the image for this tile
    public Image getTileImage() {
        return tileImage;
    }

    // Get the row of the tile
    public int getRow() {
        return row;
    }

    // Get the column of the tile
    public int getCol() {
        return col;
    }

    // Check if this tile is part of the enemy path
    public boolean isEnemyTile() {
        return isEnemyTile;
    }

    // Check if this tile is an exit
    public boolean isExit() {
        return isExit;
    }

    // Check if this tile is an entrance
    public boolean isEntrance() {
        return isEntrance;
    }

    // Setter for isEntrance
    public void setEntrance(boolean isEntrance) {
        this.isEntrance = isEntrance;
    }

    // Setter for isExit (in case you need to modify exit as well)
    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
    public String getName() {
        return name;
    }

}
