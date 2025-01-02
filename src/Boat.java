import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Boat extends TowerProperties implements TowerModelI {
    // List to keep track of all active projectiles
    private List<Projectile> projectiles;
    private EnemyModel theTarget;
    private int xLoc;
    private int yLoc;
    private long lastFiredTime;  // Keep track of the last time the mortar fired
    private long fireCooldown = 1000;  // Mortar has a slower cooldown (1 second)
    private boolean hasFiredOnce = false;  // Flag to check if the mortar has fired once
    private int entranceX; // Entrance tile X-coordinate
    private int entranceY; // Entrance tile Y-coordinate

    public Boat(int xLoc, int yLoc) {
        this.projectiles = new ArrayList<>();
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.lastFiredTime = System.currentTimeMillis();  // Initialize the last fired time to now
        this.setDamage(20);
        this.entranceX = entranceX;
        this.entranceY = entranceY;
    }


    public void fire(List<EnemyModel> enemies, GameView gameView) {
        long currentTime = System.currentTimeMillis();

        // Check if enough time has passed since the last shot
        if (!hasFiredOnce || currentTime - lastFiredTime >= fireCooldown) {
            EnemyModel closestEnemy = null;
            double closestDistance = Double.MAX_VALUE;

            // Find the closest enemy to the tower
            for (EnemyModel enemy : enemies) {
                if (enemy.getHealth() > 0) {  // Only target active enemies
                    double distance = Math.sqrt(Math.pow(enemy.getCurrentCol() - xLoc, 2) + Math.pow(enemy.getCurrentRow() - yLoc, 2));
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEnemy = enemy;
                    }
                }
            }

            // If a valid target was found, fire at it
            if (closestEnemy != null) {
                // Create a projectile and pass in the GameView for interaction
                Projectile newProjectile = new Projectile(
                        "Mortar Projectile", "Images/TowerSprites/DefaultProjectile.png",
                        this.getDamage(), this.xLoc, this.yLoc, closestEnemy, gameView, entranceX, entranceY
                );
                projectiles.add(newProjectile);
                lastFiredTime = currentTime;
                hasFiredOnce = true;
            }
        }

        // Update all active projectiles after firing
        updateProjectiles();
    }



    /**
     * Updates the state of all projectiles (i.e., moves them).
     */
    public void updateProjectiles() {
        // Create a copy of the projectiles list
        List<Projectile> projectilesCopy = new ArrayList<>(projectiles);

        // Move projectiles and remove inactive ones
        for (Projectile projectile : projectilesCopy) {
            projectile.move();  // Move each projectile
        }

        // Remove inactive projectiles
        projectiles.removeIf(projectile -> !projectile.isActive());

        // Remove off-screen projectiles only if necessary
        projectiles.removeIf(this::isOffScreen);
    }



    /**
     * Draws all active projectiles.
     * @param g Graphics object for drawing
     */
    public void drawProjectiles(Graphics g) {
        // Create a copy of the projectiles list
        List<Projectile> projectilesCopy = new ArrayList<>(projectiles);

        // Iterate over the copy to draw the projectiles
        for (Projectile projectile : projectilesCopy) {
            projectile.draw(g);  // This should trigger the draw method for each projectile
        }
    }

    /**
     * Checks if a projectile is off the screen.
     */
    private boolean isOffScreen(Projectile projectile) {
        return projectile.getCurrentX() < 0 || projectile.getCurrentX() > 800 ||  // Adjust screen width
                projectile.getCurrentY() < 0 || projectile.getCurrentY() > 600;   // Adjust screen height
    }

    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }


}
