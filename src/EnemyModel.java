import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class EnemyModel {
    protected int currentRow;
    protected int currentCol;
    private int health;
    private int damage;
    private int pathIndex = 0;  // Tracks the enemy's position along the path
    private boolean reachedExit = false;

    private int currentX;  // Pixel X position
    private int currentY;  // Pixel Y position
    private int speed = 5;  // Adjust speed as needed

    // Getter for pixel X position
    public int getPixelX() {
        return currentX;
    }

    // Getter for pixel Y position
    public int getPixelY() {
        return currentY;
    }

    // Getter for row
    public int getCurrentRow() {
        return currentRow;
    }

    // Getter for column
    public int getCurrentCol() {
        return currentCol;
    }

    // Setter for row
    public void setCurrentRow(int value) {
        this.currentRow = value;
    }

    // Setter for column
    public void setCurrentCol(int value) {
        this.currentCol = value;
    }

    // Set health
    public void setHealth(int health) {
        this.health = health;
    }

    // Set damage
    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Get health
    public int getHealth() {
        return health;
    }

    // Get damage
    public int getDamage() {
        return damage;
    }

    // Abstract method to check if enemy is metal (for special attacks)
    public abstract boolean isMetal();

    /**
     * Moves the enemy along the predefined path by updating its row, col, and pixel positions.
     *
     * @param mapModel   The map the enemy is moving on.
     * @param mapPanel   The panel representing the map.
     * @param enemyPath  The path that the enemy will follow.
     */
    public int moveToNextEnemyTile(MapModel mapModel, MapPanel mapPanel, List<Tile> enemyPath, int userHealth) {
        if (mapModel == null) {
            throw new IllegalArgumentException("MapModel cannot be null");
        }

        // Check if there are more tiles in the path to move towards
        if (pathIndex < enemyPath.size()) {
            Tile currentTile = enemyPath.get(pathIndex);
            // Update the row and column based on the next tile in the path
            setCurrentRow(currentTile.getRow());
            setCurrentCol(currentTile.getCol());

            // Place the enemy at the new location
            mapPanel.placeEnemy(currentRow, currentCol, this);

            // Move the enemy in pixel coordinates towards the target tile
            moveTowardsTile(currentTile);

            pathIndex++;  // Move to the next path tile
        } else {
            reachedExit = true;  // The enemy has reached the exit
        }
        if(reachedExit){
            userHealth -= this.getDamage();
            this.setDamage(0);
        }
        return userHealth;

    }

    /**
     * Checks if the enemy has reached the target tile based on its pixel position.
     */



    /**
     * Move the enemy smoothly towards a tile (pixel-based movement).
     * This allows for more accurate projectile targeting.
     *
     * @param targetTile The tile the enemy is moving toward.
     */
    public void moveTowardsTile(Tile targetTile) {
        // Calculate pixel position based on tile row, col, and size
        int tileSize = 50;  // Assuming each tile is 50x50 pixels

        int targetX = targetTile.getCol() * tileSize;
        int targetY = targetTile.getRow() * tileSize;

        int dx = targetX - currentX;
        int dy = targetY - currentY;

        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > 0) {
            // Normalize and move the enemy by speed in the direction of the target tile
            currentX += (dx / distance) * speed;
            currentY += (dy / distance) * speed;
        }
    }



    // Decreases the enemy's health
    public void decreaseHealth(int damage) {
        health -= damage;
    }

    // Abstract method to move the enemy to a specific row and column (can be overridden for special behavior)
    public abstract void moveTo(int newRow, int newCol);

    // Abstract method to draw the enemy
    public abstract void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight);

    // Abstract method to get the enemy image
    public abstract Image getEnemyImage();

    // Check if the enemy has reached the exit
    public boolean hasReachedExit() {
        return reachedExit;
    }

    // Check if the enemy is still alive
    public boolean isAlive() {
        return health > 0;
    }
}
