import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cannon extends TowerProperties implements TowerModelI {
    // List to keep track of all active projectiles
    private List<Projectile> projectiles;
    private EnemyModel theTarget;
    private int xLoc;
    private int yLoc;
    private long lastFiredTime;  // Keep track of the last time the cannon fired
    private long fireCooldown = 500;  // 1000 ms (1 second) cooldown between shots
    private boolean hasFiredOnce = false;  // Flag to check if the cannon has fired once
    private int entranceX; // Entrance tile X-coordinate
    private int entranceY; // Entrance tile Y-coordinate


    public Cannon(int xLoc, int yLoc) {
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
                        "Mortar Projectile", "Images/TowerSprites/MortarProjectile.png",
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
        // Iterate over the projectiles list directly, as modifying the list while iterating will be handled carefully.
        List<Projectile> activeProjectiles = new ArrayList<>();

        for (Projectile projectile : projectiles) {
            // Move the projectile
            projectile.move();

            // If the projectile is still active and on screen, add it to the active list
            if (projectile.isActive() && !isOffScreen(projectile)) {
                activeProjectiles.add(projectile);
            }
        }

        // Replace the original projectiles list with the filtered list of active projectiles
        projectiles = activeProjectiles;
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

    public int getyLoc() {
        return yLoc;
    }

    public int getxLoc() {
        return xLoc;
    }
    // Method to return the list of projectiles
    public List<Projectile> getProjectiles() {
        return projectiles;
    }


}