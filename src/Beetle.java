import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * "Tankier" enemy with more health than a standard roach
 */
public class Beetle extends EnemyModel{
    private Image beetleImage;
    private int currentRow;
    private int currentCol;
    Beetle(int startRow, int startCol){
        currentRow = startRow;
        currentCol = startCol;
        this.setHealth(100);
        this.setDamage(10);
        this.beetleImage = new ImageIcon("Images/BugSprites/beetlePA.png").getImage();
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
        System.out.println("moving beetle");
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
        g.drawImage(beetleImage, screenX, screenY, tileWidth, tileHeight, null);
    }

    @Override
    public Image getEnemyImage() {
        return beetleImage;
    }
}