import javax.swing.*;
import java.awt.*;

public class Projectile {
    private String type;
    private Image imageIcon;
    private int damage;
    private int currentX;
    private int currentY;
    private int speed = 100;  // Adjust speed as needed
    private EnemyModel theTarget;
    private int hitThreshold = 15;  // Defines how close the projectile needs to be to hit the target
    private boolean active = true;  // Tracks if the projectile is still active
    private GameView gameView;

    // Coordinates of the entrance tile
    private int entranceX;
    private int entranceY;

    public Projectile(String type, String imagePath, int damage, int startX, int startY, EnemyModel theTarget, GameView gameView, int entranceX, int entranceY) {
        this.type = type;
        this.imageIcon = new ImageIcon(imagePath).getImage();  // Load image from the file path
        this.damage = damage;
        this.currentX = startX;
        this.currentY = startY;
        this.theTarget = theTarget;
        this.gameView = gameView;  // Store the reference to GameView
        this.entranceX = entranceX;  // Store entrance tile coordinates
        this.entranceY = entranceY;
    }

    public void move() {
        // If the target is null or dead, aim towards the entrance tile
        int targetX, targetY;

        if (theTarget == null || !theTarget.isAlive()) {
            // Aim towards entrance if no valid target
            targetX = entranceX;
            targetY = entranceY;
        } else {
            // Otherwise, aim at the target
            targetX = theTarget.getPixelX();
            targetY = theTarget.getPixelY();
        }

        int dx = targetX - currentX;
        int dy = targetY - currentY;

        // Normalize movement
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > 0) {
            currentX += (dx / distance) * speed;  // Update X position
            currentY += (dy / distance) * speed;  // Update Y position
        }

        // Check if the projectile has reached the target or entrance
        if (hasHitTarget(targetX, targetY)) {
            hitTarget();
        }
    }

    public void draw(Graphics g) {
        if (!active) return; // Don't draw inactive projectiles

        if (imageIcon != null) {
            // Draw the image with a fixed size (width: 50, height: 50)
            g.drawImage(imageIcon, currentX, currentY, 50, 50, null);
        } else {
            // Fallback: draw a red rectangle if the image isn't loaded
            g.setColor(Color.RED);
            g.fillRect(currentX, currentY, 50, 50);  // Simple red square as a placeholder
        }
    }

    // Collision detection method
    public boolean hasHitTarget(int targetX, int targetY) {
        // Check if the projectile is close enough to the target to count as a hit
        int dx = targetX - currentX;
        int dy = targetY - currentY;
        return Math.sqrt(dx * dx + dy * dy) <= hitThreshold;
    }

    // Handle what happens when the projectile hits the target or entrance
    private void hitTarget() {
        if (theTarget == null || !theTarget.isAlive()) {
            // If there's no valid target, deactivate the projectile when it hits the entrance
            active = false;
            return;
        }

        // Deal damage to the target
        theTarget.setHealth(theTarget.getHealth() - damage);

        // If the target's health drops to 0 or below, eliminate it
        if (!theTarget.isAlive()) {
            gameView.removeEnemy(theTarget);  // Remove the enemy from the game
        }

        // Deactivate the projectile once it has successfully hit the target
        active = false;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public boolean isActive() {
        return active;
    }
}
