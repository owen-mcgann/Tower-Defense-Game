import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;  // Correct import for List

/**
 * Represents a panel displaying a map with tiles and placed towers.
 * The panel includes interactive tile buttons that handle user input for placing towers.
 */

public class MapPanel extends JPanel {
    public Tile[][] locations;
    public Image backgroundImage;
    public Tower[][] placedTowers; // Store the placed towers
    public EnemyModel[][] placedEnemies;
    private GameView gameView;
    private JButton[][] tileButtons;
    private List<Cannon> cannons;  // Add a field for cannons in MapPanel
    private List<Mortar> mortars;
    private List<Lightning> lightnings;
    private List<Flame> flames;
    private List<BUGM3LT3R> bUGM3LT3Rs;
    private List<Boat> boats;

    /**
     * Constructs a MapPanel with specified tile locations, background image path, and game view.
     * Initializes the map with interactive buttons for each tile.
     *
     * @param locations 2D array of {@link Tile} objects representing the map layout.
     * @param backgroundImagePath Path to the background image file.
     * @param gameView The parent {@link GameView} instance to interact with.
     */
    public MapPanel(Tile[][] locations, String backgroundImagePath, GameView gameView) {
        this.locations = locations;
        this.gameView = gameView;
        placedTowers = new Tower[locations.length][locations[0].length]; // Initialize the placedTowers array
        placedEnemies = new EnemyModel[locations.length][locations[0].length];

        // Load the background map image
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        setLayout(new GridLayout(locations.length, locations[0].length, 0, 0));  // No gaps
        // Use absolute positioning for placing buttons

        // Create buttons for each tile
        createTileButtons();
    }
    /**
     * Creates and initializes buttons for each tile in the map.
     * Sets up action listeners for handling tile clicks.
     */
    private void createTileButtons() {
        int tileWidth = 47;
        int tileHeight = 47;

        // Initialize the button array with the size of 'locations'
        tileButtons = new JButton[locations.length][locations[0].length];

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                // Create a new JButton for each tile
                JButton tileButton = new JButton();
                tileButton.setBounds(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                tileButton.setOpaque(false);
                tileButton.setContentAreaFilled(false);
                tileButton.setBorderPainted(false);
                tileButton.setFocusPainted(false);

                // Store the button in the array
                tileButtons[i][j] = tileButton;

                // Add action listener to handle click event
                int row = i, col = j;
                tileButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleTileClick(row, col);
                    }
                });

                // Add the button to the panel
                add(tileButton);


            }
        }
    }

    /**
     * Handles the click event for a tile button.
     * Checks the type of tile and places the selected tower if the tile is valid.
     *
     * @param row The row index of the clicked tile.
     * @param col The column index of the clicked tile.
     */
    private void handleTileClick(int row, int col) {
        try {
            Tile tile = locations[row][col];
            Tower selectedTower = gameView.getSelectedTower(); // Get the selected tower

            if (selectedTower == null) {
                System.out.println("No tower selected.");  // Debug statement
                throw new IllegalStateException("No tower is currently selected.");
            }

            System.out.println("Clicked tile at row: " + row + ", col: " + col);
            System.out.println("Selected Tower: " + selectedTower.getName());

            // Handle different tile types
            if (tile.getName().equals("enemy")) {
                throw new IllegalArgumentException("You can't place a tower on the enemy path.");
            } else if (tile.getName().equals("border")) {
                throw new IllegalArgumentException("You can't place a tower on the border.");
            } else if (tile.getName().equals("water") && !selectedTower.getName().equals("Boat Tower")) {
                throw new IllegalArgumentException("Only Boat Towers can be placed on water.");
            }
             else if (tile.getName().equals("land") && selectedTower.getName().equals("Boat Tower")) {
            throw new IllegalArgumentException("Boats Towers can only be placed in the water.");
                }

            // Place the tower on the map using GameView's method
            System.out.println("Calling gameView.placeTowerOnTile...");
            gameView.placeTowerOnTile(row, col);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error placing tower: " + e.getMessage());  // Debug error message
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetEnemyMap(){
        placedEnemies = new EnemyModel[locations.length][locations[0].length];
    }


    /**
     * Paints the component, including the background image and placed towers.
     *
     * @param g The {@link Graphics} object used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image scaled to the panel size
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Determine tile size based on the panel dimensions
        int tileWidth = getWidth() / locations[0].length;
        int tileHeight = getHeight() / locations.length;

        // Loop through the locations array and draw each tile
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                Tile tile = locations[i][j];
                if (tile != null) {
                    // Get the tile's image
                    Image tileImage = tile.getTileImage();

                    // Draw the tile image, scaling it to the tile size
                    g.drawImage(tileImage, j * tileWidth, i * tileHeight, tileWidth, tileHeight, this);
                }

                // Draw any placed tower on the tile
                Tower tower = placedTowers[i][j];
                if (tower != null) {
                    Image towerImage = tower.getTowerImage(); // Get the tower's image
                    g.drawImage(towerImage, j * tileWidth, i * tileHeight, tileWidth, tileHeight, this); // Draw the tower
                }
                EnemyModel enemy = placedEnemies[i][j];
                if (enemy != null) {
                    Image enemyImage = enemy.getEnemyImage();
                    g.drawImage(enemyImage, j * tileWidth, i * tileHeight, tileWidth, tileHeight, this);
                }
            }
        }

        // Draw projectiles from all cannons (use a copy to avoid ConcurrentModificationException)
        if (cannons != null) {
            List<Cannon> cannonsCopy = new ArrayList<>(cannons);  // Create a copy of the cannons list
            for (Cannon cannon : cannonsCopy) {
                cannon.drawProjectiles(g);  // Drawing projectiles for each cannon
            }
        }

        // Draw projectiles from all mortars (use a copy to avoid ConcurrentModificationException)
        if (mortars != null) {
            List<Mortar> mortarsCopy = new ArrayList<>(mortars);  // Create a copy of the mortars list
            for (Mortar mortar : mortarsCopy) {
                mortar.drawProjectiles(g);  // Drawing projectiles for each mortar
            }
        }

        if (lightnings != null) {
            List<Lightning> lightningsCopy = new ArrayList<>(lightnings);  // Create a copy of the cannons list
            for (Lightning lightning : lightningsCopy) {
                lightning.drawProjectiles(g);  // Drawing projectiles for each cannon
            }
        }
        if (flames != null) {
            List<Flame> flamesCopy = new ArrayList<>(flames);  // Create a copy of the cannons list
            for (Flame flame : flamesCopy) {
                flame.drawProjectiles(g);  // Drawing projectiles for each cannon
            }
        }
        if (bUGM3LT3Rs != null) {
            List<BUGM3LT3R> bUGM3LT3RsCopy = new ArrayList<>(bUGM3LT3Rs);  // Create a copy of the cannons list
            for (BUGM3LT3R bUGM3LT3R : bUGM3LT3RsCopy) {
                bUGM3LT3R.drawProjectiles(g);  // Drawing projectiles for each cannon
            }
        }
        if (boats != null) {
            List<Boat> boatsCopy = new ArrayList<>(boats);  // Create a copy of the cannons list
            for (Boat boat : boatsCopy) {
                boat.drawProjectiles(g);  // Drawing projectiles for each cannon
            }
        }
    }


    /**
     * Places a tower on a specific tile and repaints the map panel.
     *
     * @param row The row index of the tile where the tower should be placed.
     * @param col The column index of the tile where the tower should be placed.
     * @param tower The {@link Tower} to place on the tile.
     */
    public void placeTower(int row, int col, Tower tower) {
        placedTowers[row][col] = tower;  // Place the tower in the specified tile
        repaint();  // Redraw the map to include the new tower
    }



    /**
     * Gets the JButton associated with a specific tile.
     *
     * @param x The row index of the tile.
     * @param y The column index of the tile.
     * @return The {@link JButton} associated with the specified tile.
     */
    public JButton getButton(int x, int y){
        return tileButtons[x][y];
    }

    public void placeEnemy(int row, int col, EnemyModel enemy){
        placedEnemies[row][col] = enemy;
    }


    // Add a method to set cannons from GameView
    public void setCannons(List<Cannon> cannons) {
        this.cannons = cannons;
        repaint();  // Ensure that the panel repaints whenever the cannons are updated
    }
    // Method to set mortars from GameView
    public void setMortars(List<Mortar> mortars) {
        this.mortars = mortars;
        repaint();  // Ensure that the panel repaints whenever the mortars are updated
    }
    public void setLightnings(List<Lightning> lightnings) {
        this.lightnings = lightnings;
        repaint();  // Ensure that the panel repaints whenever the lightnings are updated
    }

    public void setFlames(List<Flame> flames) {
        this.flames = flames;
        repaint();  // Ensure that the panel repaints whenever the flames are updated
    }

    public void setBUGM3LT3Rs(List<BUGM3LT3R> bUGM3LT3Rs) {
        this.bUGM3LT3Rs = bUGM3LT3Rs;
        repaint();  // Ensure that the panel repaints whenever the BUGM3LT3Rs are updated
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
        repaint();  // Ensure that the panel repaints whenever the BUGM3LT3Rs are updated
    }
}