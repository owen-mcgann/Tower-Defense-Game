import java.util.ArrayList;

/**
 * A wave has a specific array list of enemies inside it that is assigned
 * based on which wave counter number it is constructed with
 */
public class Wave {
    // Can be waves 1-20
    private int waveCounter;
    private ArrayList<EnemyModel> waveList = new ArrayList<EnemyModel>();

    /**
     * Constructs a wave object that contains the correct wave that the game is on
     * @param waveCounter which wave, 1-20
     * @param mapModel provides the reference point for where the enemies spawn
     */

    public Wave(int waveCounter, MapModel mapModel, int startRow, int startCol) {
        this.waveCounter = waveCounter;
//        int entranceRow = mapModel.getEntranceRow() * mapPanel.getWidth() / mapPanel.locations[0].length;
//        int entranceCol = mapModel.getEntranceCol() * mapPanel.getHeight() / mapPanel.locations.length;
        int entranceRow = startRow;
        int entranceCol = startCol;

        // Wave counter determines what enemies are placed into a wave
        if (this.waveCounter == 1) {
            // 3 Roaches
            // 150 combined total health
            for(int i = 0; i<3; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 2 ) {
            // 4 Roaches
            // 200 combined total health
            for (int i = 0; i < 4; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 3) {
            // 6 roaches
            // 300 combined total health
            for (int i = 0; i < 6; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 4) {
            // 4 roaches
            // 1 beetle
            // 300 combined total health
            for (int i = 0; i < 4; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
            }
            waveList.add(new Beetle(entranceRow, entranceCol));
        }
        if (this.waveCounter == 5) {
            // 4 beetles
            // 400 combined total health
            for (int i = 0; i < 4; i++) {
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 6) {
            // 3 roaches
            // 3 beetles (every other)
            // 450 combined total health
            for (int i = 0; i < 3; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 7) {
            // 4 roaches
            // 4 beetles (every other)
            // 600 combined total health
            for (int i = 0; i < 4; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 8) {
            // 2 roaches
            // 6 beetles
            // 700 combined total health
            waveList.add(new Roach(entranceRow, entranceCol));
            waveList.add(new Roach(entranceRow, entranceCol));
            for (int i = 0; i < 6; i++) {
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 9) {
            // 8 beetles
            // 800 combined total health
            for (int i = 0; i < 8; i++) {
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 10) {
            // 45 mosquitos
            // 900 combined total health
            for (int i = 0; i < 45; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 11) {
            // 35 mosquitos
            // 10 roaches
            // 1200 combined total health
            for (int i = 0; i < 10; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
                waveList.add(new Roach(entranceRow, entranceCol));
            }
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 12) {
            // 35 mosquitos
            // 5 roaches
            // 5 beetles
            // 1450 combined total health
            for (int i = 0; i < 5; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
                waveList.add(new Roach(entranceRow, entranceCol));
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
            for (int i = 0; i < 30; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 13) {
            // 16 beetles
            // 1600 combined total health
            for (int i = 0; i < 16; i++) {
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 14) {
            // 50 mosquitos
            // 10 roaches
            // 5 beetles
            // 2000 combined total health
            for (int i = 0; i < 50; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
            for (int i = 0; i < 10; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 15) {
            // 10 metal roaches
            // 500 combined total health
            for (int i = 0; i < 10; i++) {
                waveList.add(new MetalRoach(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 16) {
            // 10 metal roaches
            // 10 roaches
            // 50 mosquitos
            // 2000 combined total health
            for (int i = 0; i < 10; i++) {
                waveList.add(new MetalRoach(entranceRow, entranceCol));
                waveList.add(new Roach(entranceRow, entranceCol));
            }
            for (int i = 0; i < 50; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 17) {
            // 10 metal roaches
            // 10 beetles
            // 10 roaches
            // 25 mosquitos
            // 2500 combined total health
            for (int i = 0; i < 10; i++) {
                waveList.add(new MetalRoach(entranceRow, entranceCol));
                waveList.add(new Beetle(entranceRow, entranceCol));
                waveList.add(new Roach(entranceRow, entranceCol));
            }
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
        }
        if (this.waveCounter == 18) {
            // 25 mosquitos
            // 5 beetles
            // 10 roaches
            // 5 metal roaches
            // 5 beetles
            // 10 roaches
            // 5 metal roaches
            // 3000 combined total health
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
            // Does this twice
            for (int x = 0; x < 2; x++) {
                for (int i = 0; i < 5; i++) {
                    waveList.add(new Beetle(entranceRow, entranceCol));
                }
                for (int i = 0; i < 10; i++) {
                    waveList.add(new Roach(entranceRow, entranceCol));
                }
                for (int i = 0; i < 5; i++) {
                    waveList.add(new MetalRoach(entranceRow, entranceCol));
                }
            }
        }
        if (this.waveCounter == 19) {
            // 15 beetles
            // 15 roaches
            // 15 metal roaches
            // 4000 combined total health
            // 1 praying mantis
            for (int i = 0; i < 15; i++) {
                waveList.add(new Beetle(entranceRow, entranceCol));
                waveList.add(new Roach(entranceRow, entranceCol));
                waveList.add(new MetalRoach(entranceRow, entranceCol));
            }
            waveList.add(new PrayingMantis(entranceRow, entranceCol));
        }
        if (this.waveCounter == 20) {
            // 25 mosquitos
            // 5 roaches
            // 5 metal roaches
            // 5 beetles
            // 1 praying mantis
            // 5 roaches
            // 5 metal roaches
            // 5 beetles
            // 25 mosquitos
            // 4000 combined total health
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new MetalRoach(entranceRow, entranceCol));
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
            waveList.add(new PrayingMantis(entranceRow, entranceCol));
            for (int i = 0; i < 5; i++) {
                waveList.add(new Roach(entranceRow, entranceCol));
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new MetalRoach(entranceRow, entranceCol));
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new Beetle(entranceRow, entranceCol));
            }
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito(entranceRow, entranceCol));
            }
        }
    }

    /**
     * Returns the ArrayList of enemies within a wave
     * @return the waveList
     */
    public ArrayList<EnemyModel> getWave(){
        return waveList;
    }
}