import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Represents a tower in the game with a name and an associated image.
 * The tower's image is loaded from a file path provided during construction.
 */
public class Tower {
    /** The name of the tower. */
    private String name;

    /** The image representing the tower. */
    private Image towerImage;

    /**
     * Constructs a Tower object with the specified name and image path.
     * The image is loaded from the given file path.
     *
     * @param name The name of the tower.
     * @param imagePath The file path to the image representing the tower.
     */
    public Tower(String name, String imagePath) {
        this.name = name;
        this.towerImage = new ImageIcon(imagePath).getImage();  // Load image from the file path
    }

    /**
     * Gets the name of the tower.
     *
     * @return The name of the tower.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the image representing the tower.
     *
     * @return The image of the tower.
     */
    public Image getTowerImage() {
        return towerImage;
    }
}
