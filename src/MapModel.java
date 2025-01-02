import javax.swing.border.Border;

/**
 * Stores data for the gameMap
 * Construct a game map by making a MapModel object and pass in the map type to auto construct it
 */

import javax.swing.border.Border;
import java.util.ArrayList;

/**
 * Stores data for the gameMap
 * Construct a game map by making a MapModel object and pass in the map type to auto construct it
 */

public class MapModel {
    // 2D array contain all the map tiles
    private Tile[][] locations = new Tile[17][17];

    private Roach roach;
    private int entranceRow;
    private int entranceCol;
    private ArrayList<int[]> enemyPath;

    /**
     * Constructor for new maps, keep in mind [y][x] coordinates
     * @param mapType easy, normal, hard, or extreme map
     */
    public MapModel(String mapType) {
        // Instantiates a new Tile Factory to manufacture our necessary tiles
        TileFactory theFactory = new TileFactory();

        // Fill the grid with border tiles
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations.length; j++) {
                BorderTile borderTile = (BorderTile) theFactory.createTile("border", i, j, false, false);
                locations[i][j] = borderTile;
            }
        }


        // EASY MAP
        if (mapType.equals("easy")) {
            // Sets entrance
            EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy", 0, 14, true, false);
            entranceTile.setEntrance(true);
            locations[0][14] = entranceTile;
            this.entranceRow = 0;
            this.entranceCol = 14;
            // Sets exit
            EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy", 16, 2, false, true);
            exitTile.setExit(true);
            locations[16][2] = exitTile;

            // Stock tiles to be placed anywhere
//            EnemyTile enemyTile = (EnemyTile) theFactory.createTile("enemy", 0, 0, true, false);
//            LandTile landTile = (LandTile) theFactory.createTile("land", 0, 0, false, false);

            // First row of map (Mostly land)
            for (int x = 1; x < 14; x++) {
                locations[1][x] = theFactory.createTile("land", 1, x, false, false);
            }
            locations[1][14] = theFactory.createTile("enemy", 1, 14, false, false);
            locations[1][15] = theFactory.createTile("land", 1, 15, false, false);
            // Second row of map (Mostly enemy)
            locations[2][1] = theFactory.createTile("land", 2, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[2][x] = theFactory.createTile("enemy", 2, x, false, false);
            }
            locations[2][15] = theFactory.createTile("land", 2, 15, false, false);
            // Third row of map (Mostly land)
            locations[3][1] = theFactory.createTile("land", 3, 1, false, false);
            locations[3][2] = theFactory.createTile("enemy", 3, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[3][x] = theFactory.createTile("land", 3, x, false, false);
            }
            // Fourth row of map (Mostly enemy)
            locations[4][1] = theFactory.createTile("land", 4, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[4][x] = theFactory.createTile("enemy", 4, x, false, false);
            }
            locations[4][15] = theFactory.createTile("land", 4, 15, false, false);
            // Fifth row of map (Mostly land)
            for (int x = 1; x < 14; x++) {
                locations[5][x] = theFactory.createTile("land", 5, x, false, false);
            }
            locations[5][14] = theFactory.createTile("enemy", 5, 14, false, false);
            locations[5][15] = theFactory.createTile("land", 5, 15, false, false);
            // Sixth row of map (Mostly enemy)
            locations[6][1] = theFactory.createTile("land", 6, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[6][x] = theFactory.createTile("enemy", 6, x, false, false);
            }
            locations[6][15] = theFactory.createTile("land", 6, 15, false, false);
            // Seventh row of map (Mostly land)
            locations[7][1] = theFactory.createTile("land", 7, 1, false, false);
            locations[7][2] = theFactory.createTile("enemy", 7, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[7][x] = theFactory.createTile("land", 7, x, false, false);
            }
            // Eighth row of map (Mostly enemy)
            locations[8][1] = theFactory.createTile("land", 8, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[8][x] = theFactory.createTile("enemy", 8, x, false, false);
            }
            locations[8][15] = theFactory.createTile("land", 8, 15, false, false);
            // Ninth row of map (Mostly land)
            for (int x = 1; x < 14; x++) {
                locations[9][x] = theFactory.createTile("land", 9, x, false, false);
            }
            locations[9][14] = theFactory.createTile("enemy", 9, 14, false, false);
            locations[9][15] = theFactory.createTile("land", 9, 15, false, false);
            // Tenth row of map (Mostly enemy)
            locations[10][1] = theFactory.createTile("land", 10, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[10][x] = theFactory.createTile("enemy", 10, x, false, false);
            }
            locations[10][15] = theFactory.createTile("land", 10, 15, false, false);
            // Eleventh row of map (Mostly land)
            locations[11][1] = theFactory.createTile("land", 11, 1, false, false);
            locations[11][2] = theFactory.createTile("enemy", 11, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[11][x] = theFactory.createTile("land", 11, x, false, false);
            }
            // Twelfth row of map (Mostly enemy)
            locations[12][1] = theFactory.createTile("land", 12, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[12][x] = theFactory.createTile("enemy", 12, x, false, false);
            }
            locations[12][15] = theFactory.createTile("land", 12, 15, false, false);
            // Thirteenth row of map (Mostly land)
            for (int x = 1; x < 14; x++) {
                locations[13][x] = theFactory.createTile("land", 13, x, false, false);
            }
            locations[13][14] = theFactory.createTile("enemy", 13, 14, false, false);
            locations[13][15] = theFactory.createTile("land", 13, 15, false, false);
            // Fourteenth row of map (Mostly enemy)
            locations[14][1] = theFactory.createTile("land", 14, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[14][x] = theFactory.createTile("enemy", 14, x, false, false);
            }
            locations[14][15] = theFactory.createTile("land", 14, 15, false, false);
            // Fifteenth row of map (Mostly land)
            locations[15][1] = theFactory.createTile("land", 15, 1, false, false);
            locations[15][2] = theFactory.createTile("enemy", 15, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[15][x] = theFactory.createTile("land", 15, x, false, false);
            }

        } // End of Easy Map if statement


        // NORMAL MAP
        if (mapType.equals("normal")) {
            // Sets entrance
            EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy", 0, 2, true, false);
            entranceTile.setEntrance(true);
            locations[0][2] = entranceTile;
            this.entranceRow = 0;
            this.entranceCol = 2;
            // Sets exit
            EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy", 16, 14, false, true);
            exitTile.setExit(true);
            locations[16][14] = exitTile;

            // Stock tiles to be placed anywhere
//            EnemyTile enemyTile = (EnemyTile) theFactory.createTile("enemy", 0, 0, true, false);
//            LandTile landTile = (LandTile) theFactory.createTile("land", 0, 0, false, false);
//            WaterTile waterTile = (WaterTile) theFactory.createTile("water", 0, 0, false, false);
            // First row of map (Mostly water)
            locations[1][1] = theFactory.createTile("water", 1, 1, false, false);
            locations[1][2] = theFactory.createTile("enemy", 1, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[1][x] = theFactory.createTile("water", 1, x, false, false);
            }
            // Second row of map (dashed land row)
            locations[2][1] = theFactory.createTile("water", 2, 1, false, false);
            locations[2][2] = theFactory.createTile("enemy", 2, 2, false, false);
            locations[2][3] = theFactory.createTile("water", 2, 3, false, false);
            locations[2][4] = theFactory.createTile("land", 2, 4, false, false);
            locations[2][5] = theFactory.createTile("land", 2, 5, false, false);
            locations[2][6] = theFactory.createTile("water", 2, 6, false, false);
            locations[2][7] = theFactory.createTile("land", 2, 7, false, false);
            locations[2][8] = theFactory.createTile("land", 2, 8, false, false);
            locations[2][9] = theFactory.createTile("water", 2, 9, false, false);
            locations[2][10] = theFactory.createTile("land", 2, 10, false, false);
            locations[2][11] = theFactory.createTile("land", 2, 11, false, false);
            locations[2][12] = theFactory.createTile("water", 2, 12, false, false);
            // little land
            locations[2][13] = theFactory.createTile("land", 2, 13, false, false);
            locations[2][14] = theFactory.createTile("water", 2, 14, false, false);
            locations[2][15] = theFactory.createTile("water", 2, 15, false, false);
            // Third row of map (Mostly water)
            locations[3][1] = theFactory.createTile("water", 3, 1, false, false);
            locations[3][2] = theFactory.createTile("enemy", 3, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[3][x] = theFactory.createTile("water", 3, x, false, false);
            }
            // Fourth row of map (Mostly enemy)
            locations[4][1] = theFactory.createTile("water", 4, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[4][x] = theFactory.createTile("enemy", 4, x, false, false);
            }
            locations[4][15] = theFactory.createTile("water", 4, 15, false, false);
            // Fifth row of map (Mostly water)
            for (int x = 1; x < 14; x++) {
                locations[5][x] = theFactory.createTile("water", 5, x, false, false);
            }
            locations[5][14] = theFactory.createTile("enemy", 5, 14, false, false);
            locations[5][15] = theFactory.createTile("water", 5, 15, false, false);
            // Sixth row of map (Big dashed land row 1)
            locations[6][1] = theFactory.createTile("water", 6, 1, false, false);
            locations[6][2] = theFactory.createTile("water", 6, 2, false, false);
            locations[6][3] = theFactory.createTile("land", 6, 3, false, false);
            locations[6][4] = theFactory.createTile("land", 6, 4, false, false);
            locations[6][5] = theFactory.createTile("water", 6, 5, false, false);
            locations[6][6] = theFactory.createTile("land", 6, 6, false, false);
            locations[6][7] = theFactory.createTile("land", 6, 7, false, false);
            locations[6][8] = theFactory.createTile("water", 6, 8, false, false);
            // big land
            locations[6][9] = theFactory.createTile("land", 6, 9, false, false);
            locations[6][10] = theFactory.createTile("land", 6, 10, false, false);
            locations[6][11] = theFactory.createTile("land", 6, 11, false, false);
            locations[6][12] = theFactory.createTile("land", 6, 12, false, false);
            locations[6][13] = theFactory.createTile("water", 6, 13, false, false);
            locations[6][14] = theFactory.createTile("enemy", 6, 14, false, false);
            locations[6][15] = theFactory.createTile("water", 6, 15, false, false);
            // Seventh row of map (Big dashed land row 2)
            locations[7][1] = theFactory.createTile("water", 7, 1, false, false);
            locations[7][2] = theFactory.createTile("water", 7, 2, false, false);
            locations[7][3] = theFactory.createTile("land", 7, 3, false, false);
            locations[7][4] = theFactory.createTile("land", 7, 4, false, false);
            locations[7][5] = theFactory.createTile("water", 7, 5, false, false);
            locations[7][6] = theFactory.createTile("land", 7, 6, false, false);
            locations[7][7] = theFactory.createTile("land", 7, 7, false, false);
            locations[7][8] = theFactory.createTile("water", 7, 8, false, false);
            // big land
            locations[7][9] = theFactory.createTile("land", 7, 9, false, false);
            locations[7][10] = theFactory.createTile("land", 7, 10, false, false);
            locations[7][11] = theFactory.createTile("land", 7, 11, false, false);
            locations[7][12] = theFactory.createTile("land", 7, 12, false, false);
            locations[7][13] = theFactory.createTile("water", 7, 13, false, false);
            locations[7][14] = theFactory.createTile("enemy", 7, 14, false, false);
            locations[7][15] = theFactory.createTile("water", 7, 15, false, false);
            // Eighth row of map (Mostly water)
            for (int x = 1; x < 14; x++) {
                locations[8][x] = theFactory.createTile("water", 8, x, false, false);
            }
            locations[8][14] = theFactory.createTile("enemy", 8, 14, false, false);
            locations[8][15] = theFactory.createTile("water", 8, 15, false, false);
            // Ninth row of map (Mostly enemy)
            locations[9][1] = theFactory.createTile("water", 9, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[9][x] = theFactory.createTile("enemy", 9, x, false, false);
            }
            locations[9][15] = theFactory.createTile("water", 9, 15, false, false);
            // Tenth row of map (Mostly water)
            locations[10][1] = theFactory.createTile("water", 10, 1, false, false);
            locations[10][2] = theFactory.createTile("enemy", 10, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[10][x] = theFactory.createTile("water", 10, 15, false, false);
            }
            // Eleventh row of map (Big dashed land row 1)
            locations[11][1] = theFactory.createTile("water", 11, 1, false, false);
            locations[11][2] = theFactory.createTile("enemy", 11, 2, false, false);
            locations[11][3] = theFactory.createTile("water", 11, 3, false, false);
            locations[11][4] = theFactory.createTile("land", 11, 4, false, false);
            locations[11][5] = theFactory.createTile("land", 11, 5, false, false);
            locations[11][6] = theFactory.createTile("water", 11, 6, false, false);
            locations[11][7] = theFactory.createTile("land", 11, 7, false, false);
            locations[11][8] = theFactory.createTile("land", 11, 8, false, false);
            locations[11][9] = theFactory.createTile("land", 11, 9, false, false);
            locations[11][10] = theFactory.createTile("water", 11, 10, false, false);
            locations[11][11] = theFactory.createTile("land", 11, 11, false, false);
            locations[11][12] = theFactory.createTile("land", 11, 12, false, false);
            locations[11][13] = theFactory.createTile("land", 11, 13, false, false);
            locations[11][14] = theFactory.createTile("water", 11, 14, false, false);
            locations[11][15] = theFactory.createTile("water", 11, 15, false, false);
            // Twelfth row of map (Big dashed land row 2)
            locations[12][1] = theFactory.createTile("water", 12, 1, false, false);
            locations[12][2] = theFactory.createTile("enemy", 12, 2, false, false);
            locations[12][3] = theFactory.createTile("water", 12, 3, false, false);
            locations[12][4] = theFactory.createTile("land", 12, 4, false, false);
            locations[12][5] = theFactory.createTile("land", 12, 5, false, false);
            locations[12][6] = theFactory.createTile("water", 12, 6, false, false);
            locations[12][7] = theFactory.createTile("land", 12, 7, false, false);
            locations[12][8] = theFactory.createTile("land", 12, 8, false, false);
            locations[12][9] = theFactory.createTile("land", 12, 9, false, false);
            locations[12][10] = theFactory.createTile("water", 12, 10, false, false);
            locations[12][11] = theFactory.createTile("land", 12, 11, false, false);
            locations[12][12] = theFactory.createTile("land", 12, 12, false, false);
            locations[12][13] = theFactory.createTile("land", 12, 13, false, false);
            locations[12][14] = theFactory.createTile("water", 12, 14, false, false);
            locations[12][15] = theFactory.createTile("water", 12, 15, false, false);
            // Thirteenth row of map (Mostly water)
            locations[13][1] = theFactory.createTile("water", 13, 1, false, false);
            locations[13][2] = theFactory.createTile("enemy", 13, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[13][x] = theFactory.createTile("water", 13, x, false, false);
            }
            // Fourteenth row of map (Mostly enemy)
            locations[14][1] = theFactory.createTile("water", 14, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[14][x] = theFactory.createTile("enemy", 14, x, false, false);
            }
            locations[14][15] = theFactory.createTile("water", 14, 15, false, false);
            // Fifteenth row of map (Mostly water)
            for (int x = 1; x < 14; x++) {
                locations[15][x] = theFactory.createTile("water", 15, x, false, false);
            }
            locations[15][14] = theFactory.createTile("enemy", 15, 14, false, false);
            locations[15][15] = theFactory.createTile("water", 15, 15, false, false);

        } // End of Normal Map if statement
        // HARD MAP
        if (mapType.equals("hard")) {
            // Sets entrance
            EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy", 0, 6, true, false);
            entranceTile.setEntrance(true);
            locations[0][6] = entranceTile;
            this.entranceRow = 0;
            this.entranceCol = 6;
            // Sets exit
            EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy", 16, 10, false, true);
            exitTile.setExit(true);
            locations[16][10] = exitTile;

            // Stock tiles to be placed anywhere
//            EnemyTile enemyTile = (EnemyTile) theFactory.createTile("enemy", 0, 0, true, false);
//            LandTile landTile = (LandTile) theFactory.createTile("land", 0, 0, false, false);
//            WaterTile waterTile = (WaterTile) theFactory.createTile("water", 0, 0, false, false);
            // First row of map (Mostly water)
            for (int x = 1; x < 16; x++) {
                locations[1][x] = theFactory.createTile("water", 1, x, false, false);
            }
            locations[1][6] = theFactory.createTile("enemy", 1, 6, false, false);
            // Second row of map (Mostly water)
            for (int x = 1; x < 16; x++) {
                locations[2][x] = theFactory.createTile("water", 2, x, false, false);
            }
            locations[2][6] = theFactory.createTile("enemy", 2, 6, false, false);
            // Third row of map (Big island row 1)
            for (int x = 1; x < 6; x++) {
                locations[3][x] = theFactory.createTile("water", 3, x, false, false);
            }
            locations[3][6] = theFactory.createTile("enemy", 3, 6, false, false);
            // 3 wide blue line in middle
            for (int x = 7; x < 10; x++) {
                locations[3][x] = theFactory.createTile("water", 3, x, false, false);
            }
            // big island
            for (int x = 10; x < 14; x++) {
                locations[3][x] = theFactory.createTile("land", 3, x, false, false);
            }
            locations[3][14] = theFactory.createTile("water", 3, 14, false, false);
            locations[3][15] = theFactory.createTile("water", 3, 15, false, false);
            // Fourth row of map (Big island row 2)
            locations[4][1] = theFactory.createTile("water", 4, 1, false, false);
            for (int x = 2; x < 7; x++) {
                locations[4][x] = theFactory.createTile("enemy", 4, x, false, false);
            }
            // 3 wide blue line in middle
            for (int x = 7; x < 10; x++) {
                locations[4][x] = theFactory.createTile("water", 4, x, false, false);
            }
            // big island
            for (int x = 10; x < 14; x++) {
                locations[4][x] = theFactory.createTile("land", 4, x, false, false);
            }
            locations[4][14] = theFactory.createTile("water", 4, 14, false, false);
            locations[4][15] = theFactory.createTile("water", 4, 15, false, false);
            // Fifth row of map (Big island row 3)
            locations[5][1] = theFactory.createTile("water", 5, 1, false, false);
            locations[5][2] = theFactory.createTile("enemy", 5, 2, false, false);
            for (int x = 3; x < 10; x++) {
                locations[5][x] = theFactory.createTile("water", 5, x, false, false);
            }
            // big island
            for (int x = 10; x < 14; x++) {
                locations[5][x] = theFactory.createTile("land", 5, x, false, false);
            }
            locations[5][14] = theFactory.createTile("water", 5, 14, false, false);
            locations[5][15] = theFactory.createTile("water", 5, 15, false, false);
            // Sixth row of map (Little island row 1)
            locations[6][1] = theFactory.createTile("water", 6, 1, false, false);
            locations[6][2] = theFactory.createTile("enemy", 6, 2, false, false);
            locations[6][3] = theFactory.createTile("water", 6, 3, false, false);
            // little island
            for (int x = 4; x < 7; x++) {
                locations[6][x] = theFactory.createTile("land", 6, x, false, false);
            }
            for (int x = 7; x < 16; x++) {
                locations[6][x] = theFactory.createTile("water", 6, x, false, false);
            }
            // Seventh row of map (Little island row 2)
            locations[7][1] = theFactory.createTile("water", 7, 1, false, false);
            locations[7][2] = theFactory.createTile("enemy", 7, 2, false, false);
            locations[7][3] = theFactory.createTile("water", 7, 3, false, false);
            // little island
            for (int x = 4; x < 7; x++) {
                locations[7][x] = theFactory.createTile("land", 7, x, false, false);
            }
            for (int x = 7; x < 16; x++) {
                locations[7][x] = theFactory.createTile("water", 7, x, false, false);
            }
            // Eighth row of map (Mostly water)
            locations[8][1] = theFactory.createTile("water", 8, 1, false, false);
            locations[8][2] = theFactory.createTile("enemy", 8, 2, false, false);
            for (int x = 3; x < 16; x++) {
                locations[8][x] = theFactory.createTile("water", 8, x, false, false);
            }
            // Ninth row of map (Mostly enemy)
            locations[9][1] = theFactory.createTile("water", 9, 1, false, false);
            for (int x = 2; x < 15; x++) {
                locations[9][x] = theFactory.createTile("enemy", 9, x, false, false);
            }
            locations[9][15] = theFactory.createTile("water", 9, 15, false, false);
            // Tenth row of map (Mostly water)
            for (int x = 1; x < 16; x++) {
                locations[10][x] = theFactory.createTile("water", 10, x, false, false);
            }
            locations[10][14] = theFactory.createTile("enemy", 10, 14, false, false);
            // Eleventh row of map (Little island row 1)
            for (int x = 1; x < 16; x++) {
                locations[11][x] = theFactory.createTile("water", 11, x, false, false);
            }
            for (int x = 10; x < 13; x++) {
                locations[11][x] = theFactory.createTile("land", 11, x, false, false);
            }
            locations[11][14] = theFactory.createTile("enemy", 11, 14, false, false);
            // Twelfth row of map (Double island row)
            locations[12][1] = theFactory.createTile("water", 12, 1, false, false);
            locations[12][2] = theFactory.createTile("water", 12, 2, false, false);
            for (int x = 3; x < 7; x++) {
                locations[12][x] = theFactory.createTile("land", 12, x, false, false);
            }
            // 3 wide blue line in middle
            for (int x = 7; x < 16; x++) {
                locations[12][x] = theFactory.createTile("water", 12, x, false, false);
            }
            for (int x = 10; x < 13; x++) {
                locations[12][x] = theFactory.createTile("land", 12, x, false, false);
            }
            locations[12][14] = theFactory.createTile("enemy", 12, 14, false, false);
            // Thirteenth row of map (Big island row 2)
            locations[13][1] = theFactory.createTile("water", 13, 1, false, false);
            locations[13][2] = theFactory.createTile("water", 13, 2, false, false);
            for (int x = 3; x < 7; x++) {
                locations[13][x] = theFactory.createTile("land", 13, x, false, false);
            }
            for (int x = 7; x < 16; x++) {
                locations[13][x] = theFactory.createTile("water", 13, x, false, false);
            }
            locations[13][14] = theFactory.createTile("enemy", 13, 14, false, false);
            // Fourteenth row of map (Big island row 3);
            locations[14][1] = theFactory.createTile("water", 14, 1, false, false);
            locations[14][2] = theFactory.createTile("water", 14, 2, false, false);
            for (int x = 3; x < 7; x++) {
                locations[14][x] = theFactory.createTile("land", 14, x, false, false);
            }
            // 3 wide blue line in middle
            for (int x = 7; x < 10; x++) {
                locations[14][x] = theFactory.createTile("water", 14, x, false, false);
            }
            for (int x = 10; x < 15; x++) {
                locations[14][x] = theFactory.createTile("enemy", 14, x, false, false);
            }
            locations[14][15] = theFactory.createTile("water", 14, 15, false, false);
            // Fifteenth row of map (Mostly water)
            for (int x = 1; x < 10; x++) {
                locations[15][x] = theFactory.createTile("water", 15, x, false, false);
            }
            locations[15][10] = theFactory.createTile("enemy", 15, 10,false, false);
            for (int x = 11; x < 16; x++) {
                locations[15][x] = theFactory.createTile("water", 15, x, false, false);
            }
        } // End of Hard Map if statement
        // EXTREME MAP
        if (mapType.equals("extreme")) {
            // Sets entrance
            EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy", 0, 8, true, false);
            entranceTile.setEntrance(true);
            locations[0][8] = entranceTile;
            this.entranceRow = 0;
            this.entranceCol = 8;
            // Sets exit
            EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy", 16, 8, false, true);
            exitTile.setExit(true);
            locations[16][8] = exitTile;

            // Stock tiles to be placed anywhere
//            EnemyTile enemyTile = (EnemyTile) theFactory.createTile("enemy", 0, 0, true, false);
//            LandTile landTile = (LandTile) theFactory.createTile("land", 0, 0, false, false);
//            WaterTile waterTile = (WaterTile) theFactory.createTile("water", 0, 0, false, false);
            // Fills the play area with water
            for (int x = 1; x < 16; x++) {
                for (int y = 1; y < 16; y++) {
                    locations[x][y] = theFactory.createTile("water", x, y, false, false);
                }
            }
            // Draws the enemy path strip down column 8
            for (int y = 1; y < 16; y++) {
                locations[y][8] = theFactory.createTile("enemy", y, 8, false, false);
            }
            // Manually adds islands
            // Big island
            for(int x = 2; x<4; x++){
                for(int y = 11; y< 14; y++){
                    locations[x][y] = theFactory.createTile("land", x, y, false, false);

                }
            }
            // Top left little island
            for(int x = 3; x<5; x++){
                for(int y = 4; y< 6; y++){
                    locations[x][y] = theFactory.createTile("land", x, y, false, false);
                }
            }
            // Tiniest island
            locations[8][11] = theFactory.createTile("land", 8, 11, false, false);
            // Bottom left little island;
            for(int x = 12; x<14; x++){
                for(int y = 3; y< 5; y++){
                    locations[x][y] = theFactory.createTile("land", x, y, false, false);
                }
            }
            // Bottom right tiny island
            locations[14][12] = theFactory.createTile("land", 14, 12, false, false);
            locations[14][13] = theFactory.createTile("land", 14, 13, false, false);
        } // End of EXTREME Map if statement

    }

    /**
     * Essential method that provides the game board once it is correctly constructed
     * @return the 2D Tile array of the game board
     */
    public Tile[][] getLocations() {
        return locations;
    }

    /**
     * Returns the Tile at the specified location
     * @param index1 2d array locations[index1][]
     * @param index2 2d array locations[][index2]
     * @return Tile at locations[index1][index2]
     */
    public Tile getTileAt(int index1, int index2) {
        return locations[index1][index2];
    }

    /**
     * Prints all locations in the map model for design and testing purposes
     */
    public void printLocations() {
        for (int i = 0; i < locations.length; i++) {
            for (int j =0; j < locations.length; j++) {
                // prints the type at the location bounded by | and |
                System.out.print("|"+ locations[i][j].getType() + "|");
            }
            System.out.println();
        }
    }


    public Tile[][] getMapTiles() {
        return locations;
    }

    // Method to get the width of the map
    public int getMapWidth() {
        return locations[0].length;
    }

    // Method to get the height of the map
    public int getMapHeight() {
        return locations.length;
    }

    // Method to get a specific tile
    public Tile getTile(int y, int x) {
        if (y >= 0 && y < locations.length && x >= 0 && x < locations[0].length) {
            return locations[y][x];
        }
        return null; // Return null if out of bounds
    }

    // Method to get tile type based on row and column
    public String getTileType(int row, int col) {
        if (row < 0 || col < 0 || row >= locations.length || col >= locations[0].length) {
            return "unknown"; // Handle out-of-bounds access
        }
        return locations[row][col].getType();
    }

    // Methods to get the row and column count
    public int getRowCount() {
        return locations.length;
    }

    public int getColCount() {
        return locations[0].length;
    }

    public int getEntranceRow(){
        return entranceRow;
    }
    public int getEntranceCol(){
        return entranceCol;
    }

    /**
     * Get the list of enemy path coordinates.
     * @return The enemy path as a list of row and column pairs.
     */
    public ArrayList<int[]> getEnemyPath() {
        return enemyPath;
    }



}