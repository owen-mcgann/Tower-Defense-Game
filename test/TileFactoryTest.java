import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileFactoryTest {
    private TileFactory factory;

    @Before
    public void setup(){
        factory = new TileFactory();
    }

    @Test
    public void createTile() {
        EnemyTile eTile= (EnemyTile) factory.createTile("enemy", 1, 2, true, false);
        Assert.assertNotNull("Enemy Tile should be created",eTile);
    }
}