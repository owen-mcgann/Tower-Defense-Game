

import org.junit.Test;
import org.junit.Assert;


public class MapModelTest {
    MapModel easyMap = new MapModel("easy");
    MapModel normalMap = new MapModel("normal");
    MapModel hardMap = new MapModel("hard");
    MapModel extremeMap = new MapModel("extreme");
    // Matches easyMap
    Tile[][] testTile = new Tile[17][17];
    @Test
    public void MapTests() {
        System.out.println("Testing maps are not null/empty...");
        Assert.assertNotEquals(testTile, easyMap);
        System.out.println("Testing map printLocation methods...");
        System.out.println("EASY MAP...");
        easyMap.printLocations();
        System.out.println("NORMAL MAP...");
        normalMap.printLocations();
        System.out.println("HARD MAP...");
        hardMap.printLocations();
        System.out.println("EXTREME MAP...");
        extremeMap.printLocations();
    }
}
