import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class WaveTest {
    // Creates wave 1, 3 roaches
    Wave wave1 = new Wave(1, new MapModel("normal"), 0,0);
    // Creates a wave that contains two different types of enemies
    Wave wave6 = new Wave(6, new MapModel("extreme"),0,0);
    // Gets the actual ArrayList the wave object holds
    ArrayList<EnemyModel> wave1List = wave1.getWave();
    ArrayList<EnemyModel> wave6List = wave6.getWave();
    @Test
    public void MapTests() {
        System.out.println("Testing that waveList is not empty...");
        Assert.assertNotNull(wave1List);
        System.out.println("Testing that waveList contains enemies...");
        Assert.assertNotNull(wave1List.get(0));
        System.out.println("Testing that wave 1 contains exactly 3 enemies...");
        Assert.assertEquals(3, wave1List.size());
        System.out.println("Testing that waves can have beetles instead of roaches, i.e. wave size should be 6...");
        Assert.assertEquals(6, wave6List.size());

    }
}