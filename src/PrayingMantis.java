import javax.swing.*;
import java.awt.*;

/**
 * Boss enemy that appears on the final wave
 */
public class PrayingMantis extends EnemyModel{
    private int currentRow;
    private int currentCol;
    private Image mantisImage;
    PrayingMantis(int startRow, int startCol){
        currentRow = startRow;
        currentCol = startCol;
        this.setHealth(1000);
        this.setDamage(10);
        this.mantisImage = new ImageIcon("Images/BugSprites/mantisPA.png").getImage();
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

    /**
     * Abstract method drawing the enemy on the board
     *
     * @param g
     * @param screenX
     * @param screenY
     * @param tileWidth
     * @param tileHeight
     */
    @Override
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(mantisImage, screenX, screenY, tileWidth, tileHeight, null);
    }

    @Override
    public Image getEnemyImage() {
        return mantisImage;
    }
}