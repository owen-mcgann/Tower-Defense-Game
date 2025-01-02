import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Roach extends EnemyModel {
    private Image roachImage;  // To hold the roach's image
    private int currentRow;
    private int currentCol;


    // Constructor
    public Roach(int startRow, int startCol) {
        currentRow = startRow;
        currentCol = startCol;
        this.setHealth(50);   // Set initial health
        this.setDamage(10);   // Set initial damage
        this.roachImage = new ImageIcon("Images/BugSprites/cockroachPA.png").getImage();
    }


    // Getters
    public Image getRoachImage() {
        return roachImage;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    @Override
    public boolean isMetal() {
        return false;
    }

    /**
     * Method moving enemy to a new tile
     *
     * @param newRow row number of new tile
     * @param newCol column number of new tile
     */
    @Override
    public void moveTo(int newRow, int newCol) {
        currentRow  = newRow;
        currentCol = newCol;
    }

    // Utility method to check if a tile is an enemy tile
    private boolean isEnemyTile(MapModel mapModel, int row, int col) {
        // Check if the position is within bounds
        String tileType = mapModel.getTileType(row, col);
        return "enemy".equals(tileType);  // Return true if the tile is an "enemy" tile
    }


    // Draw the roach on the screen
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(roachImage, screenX, screenY, tileWidth, tileHeight, null);
    }

    @Override
    public Image getEnemyImage() {
        return roachImage;
    }

    public Image getImage() {
        return roachImage;
    }


}