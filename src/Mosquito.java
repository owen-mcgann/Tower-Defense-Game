import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A lower health enemy that appears more often
 */
public class Mosquito extends EnemyModel{
    private int currentRow;
    private int currentCol;
    private Image mosquitoImage;
    Mosquito(int startRow, int startCol){
        currentRow = startRow;
        currentCol = startCol;
        this.setHealth(20);
        this.setDamage(10);
        this.mosquitoImage = new ImageIcon("Images/BugSprites/mosquitoPA.png").getImage();

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
        g.drawImage(mosquitoImage, screenX, screenY, tileWidth, tileHeight, null);
    }

    @Override
    public Image getEnemyImage() {
        return mosquitoImage;
    }

}