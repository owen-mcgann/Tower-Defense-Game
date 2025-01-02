import java.awt.*;
import java.util.List;

/**
 * Interface representing the model of a tower in the game.
 * Defines the behavior that any tower model should implement.
 */
public interface TowerModelI {


    public void fire(List<EnemyModel> enemies, GameView gameView);

    public void updateProjectiles();

    public void drawProjectiles(Graphics g);
}
