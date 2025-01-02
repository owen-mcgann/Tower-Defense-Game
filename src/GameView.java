import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.Thread;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class GameView extends JPanel {
    private Image backgroundImage;
    private Questions questions;
    private MapModel mapModel;
    private MapPanel mapPanel;
    private JLabel questionLabel;
    private JLabel questionCountLabel;  // Label to display total number of questions
    private JLabel answerResultLabel;   // Label to display correct/incorrect count
    private JLabel correctAnswersLabel;  // Label to display correct answer count
    private JLabel incorrectAnswersLabel;  // Label to display incorrect answer count
    private JTextField answerField;
    private JLabel feedbackLabel;
    private JLabel countdownLabel;  // Countdown/cool-down timer display
    private JLabel moneyLabel;  // Label to display the user's points/money
    private String currentQuestion;

    private Timer coolDownTimer;
    private Timer gameLoopTimer;

    private int coolDownSeconds = 5;
    private int points = 500;  // Variable to track points/money
    private int sessionId = 1;  // Assuming each player has a session ID. In a real scenario, this would be dynamic.
    private int correctAnswers = 0;  // Track correct answers
    private int incorrectAnswers = 0;  // Track incorrect answers


    private Tile entranceTile;
    private Tile exitTile;
    private List<Tile> enemyPath;  // This will store the path of enemy tiles
    private Tile[][] locations;    // Reference to the map of tiles
    private Image enemyTileImage;


    // List to store enemies
    private List<EnemyModel> enemies = new ArrayList<>();

    // Tower costs
    private final int DEFAULT_TOWER_COST = 500;
    private final int BOAT_TOWER_COST = 1500;
    private final int HEAVY_TOWER_COST = 3000;
    private final int LIGHTNING_TOWER_COST = 4500;
    private final int FLAME_TOWER_COST = 2000;
    private final int BUGM3LT3R_TOWER_COST = 10000;

    // Towers
    private JButton defaultTowerButton;
    private JButton boatTowerButton;
    private JButton heavyTowerButton;
    private JButton lightningTowerButton;
    private JButton flameTowerButton;
    private JButton bugm3lt3rButton;


    public String selectedTower = null;  // To store the currently selected tower
    public int selectedTowerCost = 0;
    private String selectedTowerName = null;
    private static final int MAX_LINE_LENGTH = 23;
    private String mapType;  // The selected map type (Easy, Medium, etc.)
    private String category; // The category of questions (Math, Geography, Chemistry)
    private List<Cannon> cannons = new ArrayList<>();
    private List<Mortar> mortars = new ArrayList<>();
    private List<Lightning> lightnings = new ArrayList<>();
    private List<Flame> flames = new ArrayList<>();
    private List<BUGM3LT3R> bUGM3LT3Rs = new ArrayList<>();
    private List<Boat> boats = new ArrayList<>();



    // ***** AUDIO PLAYERS *****
    // Background music
    WAVPlayer BGMUSIC_Player = new WAVPlayer("Audio/TE_BGMUSIC.wav");
    // Sound effect when bug dies [UNIMPLEMENTED]
    WAVPlayer bugDeath_Player = new WAVPlayer("Audio/bugDeath_SE.wav");
    // Sound effect when bug is hit [UNIMPLEMENTED]
    WAVPlayer bugHit_Player = new WAVPlayer("Audio/bugHit_SE.wav");
    // Sound effect when tower is bought [IMPLEMENTED IN placeTowerOnTile]
    WAVPlayer buyTower_Player = new WAVPlayer("Audio/buyTower_SE.wav");
    // Sound effect when a tower fires, several alternate sounds could be used [UNIMPLEMENTED]
    private WAVPlayer fire_Player;
    // Rest of your GameView constructor
    // Sound effect when the player runs out of health [UNIMPLEMENTED]
    WAVPlayer gameOver_Player = new WAVPlayer("Audio/gameOver_SE.wav");
    // Sound effect when the player beats all 20 waves [UNIMPLEMENTED]
    WAVPlayer levelWin_Player = new WAVPlayer("Audio/levelWin_SE.wav");
    // Sound effect when a question is answered correctly [UNIMPLEMENTED]

    private Timer timer;

    private int userHealth= 100;


    public GameView(String backgroundImagePath, Questions questions, String mapType) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.questions = questions;
        this.mapModel = new MapModel(mapType);
        this.backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        this.locations = new MapModel(mapType).getLocations();  // Initialize the map (locations)
        this.enemyPath = new ArrayList<>();
        this.mapType = mapType;
        BGMUSIC_Player.setVolume(0.8f);
        BGMUSIC_Player.play();

        // Set up the JFrame first
        JFrame frame = new JFrame("Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);

        // back button
        ImageIcon playButtonIcon = new ImageIcon("Images/MapButton.png");
        Image playButtonImage = playButtonIcon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedPlayButtonIcon = new ImageIcon(playButtonImage);

        JButton button = new JButton(resizedPlayButtonIcon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            new WorldMapView();  // Open WorldMapView when button is clicked
        });

        button.setBounds(560, 750, 250, 150);
        frame.add(button);  // Add the button to the frame

        // Load the total number of questions in the current category
        String category = questions.getClass().getSimpleName().replace("Questions", ""); // Extract category from the class name
        int questionCount = questions.getQuestionCountForCategory(category);
        System.out.println("Total questions in category '" + category + "': " + questionCount);


        mapPanel = new MapPanel(mapModel.getLocations(), backgroundImagePath, this);
        mapPanel.setBounds(300, 0, 800, 750);
        add(mapPanel);

        // Initialize UI components with question count and session-based tracking
        initializeUI(questionCount);

        // Add this component to the frame
        frame.add(this);
        frame.setSize(1400, 900);
        frame.setVisible(true);

        entranceTile = findEntranceTile(); // Find entrance tile
        exitTile = findExitTile(); // Find exit tile

        // Firing sound exception handling
        fire_Player = new WAVPlayer("Audio/fire1_SE.wav");
        fire_Player.setVolume(0.6f);  // Set volume to your preference

        // Initialize the UI and start enemy movement
        findEnemyPath();
        startGameLoop();
    }


    private void initializeUI(int questionCount) {
        // Display the total number of questions in the current category
        questionCountLabel = new JLabel("Total questions: " + questionCount);
        questionCountLabel.setBounds(10, 20, 300, 40);  // Increased height for better spacing
        questionCountLabel.setForeground(Color.WHITE);
        questionCountLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Bold and larger font
        add(questionCountLabel);

        // Money label to display points
        JLabel moneyTextLabel = new JLabel("Money:");
        moneyTextLabel.setBounds(1120, 50, 200, 40);  // Positioned on the right side
        moneyTextLabel.setForeground(Color.WHITE);
        moneyTextLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Bold and larger font for "Money"
        add(moneyTextLabel);

        // Label to display correct answers
        correctAnswersLabel = new JLabel("Correct answers: 0");
        correctAnswersLabel.setBounds(10, 70, 300, 40);  // Increased height and adjusted position
        correctAnswersLabel.setForeground(Color.WHITE);
        correctAnswersLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Bold and larger font
        add(correctAnswersLabel);

        // Label to display incorrect answers
        incorrectAnswersLabel = new JLabel("Incorrect answers: 0");
        incorrectAnswersLabel.setBounds(10, 120, 300, 40);  // Increased height and adjusted position
        incorrectAnswersLabel.setForeground(Color.WHITE);
        incorrectAnswersLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Bold and larger font
        add(incorrectAnswersLabel);

        // Display the question label header
        JLabel questionTextLabel = new JLabel("Question:");
        questionTextLabel.setBounds(10, 170, 600, 50);  // Adjusted position for more spacing
        questionTextLabel.setForeground(Color.WHITE);
        questionTextLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Bold and larger font for "Question"
        add(questionTextLabel);

        // Initialize the question label for displaying the current question
        questionLabel = new JLabel();
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));  // Font for the actual question
        questionLabel.setBounds(10, 230, 600, 100);  // Initial bounds, dynamically resized later
        add(questionLabel);

        // Initialize the answer field before displaying the question
        answerField = new JTextField();
        add(answerField);

        // Initialize feedbackLabel, countdownLabel to avoid null pointer issues
        feedbackLabel = new JLabel("");
        feedbackLabel.setForeground(Color.WHITE);
        add(feedbackLabel);

        countdownLabel = new JLabel("");
        countdownLabel.setForeground(Color.RED);
        add(countdownLabel);

        // Get the first random question and apply the efficient wrapping
        currentQuestion = questions.getAnyQuestion();
        displayQuestion(currentQuestion);  // Ensure this is called after all components are initialized

        // Set key listener for "Enter" key to submit the answer
        answerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        checkAnswer();
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // Money label
        moneyLabel = new JLabel("500");  // Initial money is 10000
        moneyLabel.setBounds(1220, 52, 200, 40);
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setFont(new Font("Arial", Font.PLAIN, 20));  // Slightly larger font for the amount of money
        add(moneyLabel);

        // Create tower buttons
        createTowerButtons();
        updateTowerButtons();
        displayQuestion(currentQuestion);
        //loadPoints();

    }

    // Efficient text wrapping for questions
    private String wrapTextEfficiently(String text, int maxWidth) {
        FontMetrics fontMetrics = questionLabel.getFontMetrics(questionLabel.getFont());

        String[] words = text.split(" ");
        StringBuilder wrappedText = new StringBuilder();
        int lineWidth = 0;

        for (String word : words) {
            int wordWidth = fontMetrics.stringWidth(word + " ");

            // If adding the word exceeds the label width, insert a line break
            if (lineWidth + wordWidth > maxWidth) {
                wrappedText.append("<br>");
                lineWidth = 0;
            }

            wrappedText.append(word).append(" ");
            lineWidth += wordWidth;
        }

        return wrappedText.toString();
    }

    // Display the question and adjust the layout
    private void displayQuestion(String questionText) {
        // Get maximum available width to avoid overlap with the map
        int maxWidth = 290;  // This leaves enough space before the map starts at x = 300
        int maxHeight = 400; // Limit the height to avoid pushing elements too far down

        // Wrap the question and apply it to the questionLabel
        String wrappedQuestion = wrapTextEfficiently(questionText, maxWidth);
        questionLabel.setText("<html>" + wrappedQuestion + "</html>");

        // Adjust the height based on the number of lines in the wrapped question
        Dimension questionLabelSize = questionLabel.getPreferredSize();
        questionLabelSize.height = Math.min(questionLabelSize.height, maxHeight); // Restrict the height
        questionLabel.setBounds(10, 230, questionLabelSize.width, questionLabelSize.height);

        // Dynamically adjust the position of the answer field below the question
        answerField.setBounds(10, questionLabel.getY() + questionLabelSize.height + 10, 300, 30);

        // Set bounds for feedback label with maximum width and height to avoid overlap
        feedbackLabel.setBounds(10, answerField.getY() + 40, maxWidth, 40);  // Ensure width is under 300 to avoid map overlap

        // Dynamically adjust the position of countdown label based on the feedback label
        countdownLabel.setBounds(10, feedbackLabel.getY() + 40, 300, 40);
    }




    // Move to the next question
    private void moveToNextQuestion() {
        currentQuestion = questions.getAnyQuestion();

        if (currentQuestion != null) {
            displayQuestion(currentQuestion);  // Display the new question and adjust layout

            answerField.setText("");  // Clear the input field for the next question
            answerField.setEnabled(true);  // Ensure the answer field is enabled for the next question
            answerField.requestFocus();  // Set focus to the answerField so user can type immediately
        } else {
            questionLabel.setText("No more questions available.");
            answerField.setEnabled(false);
        }
    }

    private Tile findEntranceTile() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j].isEntrance()) {
                    return locations[i][j];
                }
            }
        }
        return null;
    }

    private Tile findExitTile() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j].isExit()) {
                    return locations[i][j];
                }
            }
        }
        return null;
    }
    private void findEnemyPath() {
        Tile currentTile  = findEntranceTile();
        while(!currentTile.isExit()) {
            enemyPath.add(currentTile);
            int row = currentTile.getRow();
            int col = currentTile.getCol();
            Tile below = null;
            Tile right = null;
            Tile left = null;
            if (row + 1 < locations.length) {
                below = locations[row + 1][col];
            }
            if (col+1 < locations[row].length){
                right = locations[row][col+1];
            }
            if (col-1 >= 0){
                left = locations[row][col-1];
            }
            if(below != null && below.isEnemyTile() && !enemyPath.contains(below)) {
                currentTile = below;
                continue;
            }
            if(right != null && right.isEnemyTile() && !enemyPath.contains(right)) {
                currentTile = right;
                continue;
            }
            if(left != null && left.isEnemyTile() && !enemyPath.contains(left)) {
                currentTile = left;
                continue;
            }
        }
        // adding exit tile
        enemyPath.add(currentTile);
    }

    public void moveAllEnemies(){
        mapPanel.resetEnemyMap();
        List<EnemyModel> beingMoved = enemies;
        for (EnemyModel enemy : beingMoved){
            userHealth = enemy.moveToNextEnemyTile(mapModel, mapPanel, enemyPath, userHealth);
            //System.out.println(userHealth);
            mapPanel.repaint();
        }
    }


    // Method to handle placing a tower on a tile
    public void placeTowerOnTile(int row, int col) {
        if (selectedTower != null && points >= selectedTowerCost)
        {
            points -= selectedTowerCost;  // Deduct the cost
            updateMoneyLabel();  // Update the money label
            savePoints();

            // Create a Tower object based on the selected tower
            Tower tower = new Tower(selectedTower, getTowerImagePath(selectedTower));
            mapPanel.placeTower(row, col, tower);  // Place the tower on the map
            JButton towerButton = mapPanel.getButton(row, col); // Get the JButton for the tower
            Point towerLocation = towerButton.getLocation();  // Get the exact position of the button in the MapPanel
            // Extremely important tower creation
            switch (selectedTower) {
                case "Cannon Tower":
                    Cannon cannonTower = new Cannon(towerLocation.x, towerLocation.y);
                    cannons.add(cannonTower);
                    System.out.println("Cannon Tower Created at position " + cannonTower.getxLoc() + ","+ cannonTower.getyLoc());
                    System.out.println("Cannons.size() is " + cannons.size());
                case "Boat Tower":
                    Boat boatTower = new Boat(towerLocation.x, towerLocation.y);
                    boats.add(boatTower);
                    System.out.println("Boat Tower Created at position " + boatTower.getxLoc() + ","+ boatTower.getyLoc());
                    System.out.println("Boat.size() is " + boats.size());
                    break;
                case "Mortar Tower":
                    Mortar mortarTower = new Mortar(towerLocation.x, towerLocation.y);
                    mortars.add(mortarTower);  // Add to a new list of mortars
                    System.out.println("Mortar Tower Created at position " + mortarTower.getxLoc() + "," + mortarTower.getyLoc());
                    System.out.println("Mortars.size() is " + mortars.size());
                    break;
                case "Lightning Tower":
                    Lightning lightningTower = new Lightning(towerLocation.x, towerLocation.y);
                    lightnings.add(lightningTower);  // Add to a new list of lighting
                    System.out.println("Lightning Tower Created at position " + lightningTower.getxLoc() + "," + lightningTower.getyLoc());
                    System.out.println("Lightning.size() is " + lightnings.size());
                    break;
                case "Flame Tower":
                    Flame flameTower = new Flame(towerLocation.x, towerLocation.y);
                    flames.add(flameTower);  // Add to a new list of lighting
                    System.out.println("Flame Tower Created at position " + flameTower.getxLoc() + "," + flameTower.getyLoc());
                    System.out.println("Flames.size() is " + lightnings.size());
                    break;
                case "BUGM3LT3R":
                    BUGM3LT3R bUGM3LT3RTower = new BUGM3LT3R(towerLocation.x, towerLocation.y);
                    bUGM3LT3Rs.add(bUGM3LT3RTower);  // Add to a new list of lighting
                    System.out.println("BUGM3LT3R Tower Created at position " + bUGM3LT3RTower.getxLoc() + "," + bUGM3LT3RTower.getyLoc());
                    System.out.println("BUGM3LT3Rs.size() is " + lightnings.size());
                    break;
                default:
                    // No adjustment for unknown tower types
                    break;
            }

            // Play tower buy sound effect
            buyTower_Player.play();

            selectedTower = null;  // Reset selected tower
            updateTowerButtons();  // Update tower buttons
        } else {
            JOptionPane.showMessageDialog(null, "Not enough money to place " + selectedTower + "!");
        }
    }

    // Method to get the selected tower as a Tower object
    public Tower getSelectedTower() {
        if (selectedTower != null) {
            switch (selectedTower) {
                case "Cannon Tower":
                    return new Tower("Cannon Tower", "Images/TowerSprites/CannonTower.png");
                case "Boat Tower":
                    return new Tower("Boat Tower", "Images/TowerSprites/BoatTower.png");
                case "Mortar Tower":
                    return new Tower("Mortar Tower", "Images/TowerSprites/Mortar.png");
                case "Lightning Tower":
                    return new Tower("Lightning Tower", "Images/TowerSprites/LightningTower.png");
                case "Flame Tower":
                    return new Tower("Flame Tower", "Images/TowerSprites/FlameTower.png");
                case "BUGM3LT3R":
                    return new Tower("BUGM3LT3R", "Images/TowerSprites/BUGM3LT3R.png");
                default:
                    return null;
            }
        }
        return null;
    }


    // Method to get the image path of a tower based on its name
    private String getTowerImagePath(String towerName) {
        switch (towerName) {
            case "Cannon Tower":
                return "Images/TowerSprites/CannonTower.png";
            case "Boat Tower":
                return "Images/TowerSprites/BoatTower.png";
            case "Mortar Tower":
                return "Images/TowerSprites/Mortar.png";
            case "Lightning Tower":
                return "Images/TowerSprites/LightningTower.png";
            case "Flame Tower":
                return "Images/TowerSprites/FlameTower.png";
            case "BUGM3LT3R":
                return "Images/TowerSprites/BUGM3LT3R.png";
            default:
                return null;  // No image
        }
    }

    // Create tower buttons and place them under the money label
    private void createTowerButtons() {
        int baseY = 100;  // Base Y position under the money label
        int buttonHeight = 60;

        // Default Tower (with image)
        defaultTowerButton = createTowerButton("Cannon Tower", DEFAULT_TOWER_COST, "Images/TowerSprites/CannonTower.png", 1120, baseY);
        add(defaultTowerButton);

        // Boat Tower (with image, disabled on Easy map)
        boatTowerButton = createTowerButton("Boat Tower", BOAT_TOWER_COST, "Images/TowerSprites/BoatTower.png", 1120, baseY + buttonHeight);
        if (mapType.equalsIgnoreCase("Easy")) {
            boatTowerButton.setVisible(false);  // Hide if map type is "Easy"
        }
        add(boatTowerButton);

        // Heavy Tower (with image)
        heavyTowerButton = createTowerButton("Mortar Tower", HEAVY_TOWER_COST, "Images/TowerSprites/Mortar.png", 1120, baseY + 3 * buttonHeight);
        add(heavyTowerButton);

        // Lightning Tower (with image)
        lightningTowerButton = createTowerButton("Lightning Tower", LIGHTNING_TOWER_COST, "Images/TowerSprites/LightningTower.png", 1120, baseY + 4 * buttonHeight);
        add(lightningTowerButton);

        // Flame Tower (with image)
        flameTowerButton = createTowerButton("Flame Tower", FLAME_TOWER_COST, "Images/TowerSprites/FlameTower.png", 1120, baseY + 2 * buttonHeight);
        add(flameTowerButton);

        // BUGM3LT3R Tower (with image)
        bugm3lt3rButton = createTowerButton("BUGM3LT3R", BUGM3LT3R_TOWER_COST, "Images/TowerSprites/BUGM3LT3R.png", 1120, baseY + 5 * buttonHeight);
        add(bugm3lt3rButton);
    }

    // Method to create a tower button with an action listener
    private JButton createTowerButton(String name, int cost, String imagePath, int x, int y) {
        JButton button = new JButton(name + " - $" + cost);
        button.setBounds(x, y, 200, 60);
// Load the image icon
        ImageIcon originalIcon = new ImageIcon(imagePath);

        // Resize the image
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Set desired width and height
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Set the scaled image as the button icon
        button.setIcon(scaledIcon);

        // Adjust text position relative to the image
        button.setHorizontalTextPosition(SwingConstants.CENTER);  // Center text
        button.setVerticalTextPosition(SwingConstants.BOTTOM);    // Put text below image


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (points >= cost) {
                    selectedTower = name;  // Set the selected tower
                    selectedTowerCost = cost;  // Set the tower cost
                    System.out.println("Tower selected: " + selectedTower + ", Cost: " + selectedTowerCost);
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough money for " + name + "!");
                }
            }
        });
        return button;
    }

    // Update tower buttons, enabling/disabling them based on current points
    private void updateTowerButtons() {
        defaultTowerButton.setEnabled(points >= DEFAULT_TOWER_COST);
        boatTowerButton.setEnabled(points >= BOAT_TOWER_COST && !mapType.equalsIgnoreCase("Easy"));
        heavyTowerButton.setEnabled(points >= HEAVY_TOWER_COST);
        lightningTowerButton.setEnabled(points >= LIGHTNING_TOWER_COST);
        flameTowerButton.setEnabled(points >= FLAME_TOWER_COST);
        bugm3lt3rButton.setEnabled(points >= BUGM3LT3R_TOWER_COST);
    }


    // Method to update the money label
    private void updateMoneyLabel() {
        moneyLabel.setText(String.valueOf(points)); //Update label with current points
        savePoints();
    }
    // Method to update the user's points and refresh the money label
    private void updatePoints(int amount) {
        points += amount;  // Add the specified amount to the current points
        updateTowerButtons();  // Update button states
        savePoints();  // Save points after updating
        updateMoneyLabel();
    }

    // Method to save points to a file
    private void savePoints() {
        try (FileWriter writer = new FileWriter("pdfs/points.txt")) {
            writer.write(String.valueOf(points));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Timer enemySpawnTimer;
    private int currentWave = 1;
    private  ArrayList<EnemyModel> waveList = new ArrayList<EnemyModel>();
    private Wave incomingWave;

    private void setFirstWave(){
        int entranceRow = mapModel.getEntranceRow() * mapPanel.getWidth() / mapPanel.locations[0].length;
        int entranceCol = mapModel.getEntranceCol() * mapPanel.getHeight() / mapPanel.locations.length;
        incomingWave = new Wave(currentWave, mapModel, entranceRow, entranceCol);
        waveList = incomingWave.getWave();
    }
    // Method to spawn enemies at the start of the game
    private void spawnEnemies() {
        int entranceRow = mapModel.getEntranceRow() * mapPanel.getWidth() / mapPanel.locations[0].length;
        int entranceCol = mapModel.getEntranceCol() * mapPanel.getHeight() / mapPanel.locations.length;

        if (currentWave > 19) {
            System.out.println("All waves completed!");
            checkForVictory();
            return;
        }

        enemySpawnTimer = new Timer();
        enemySpawnTimer.scheduleAtFixedRate(new TimerTask() {
            private int enemyIndex = 0;

            @Override
            public void run() {
                if (enemyIndex < waveList.size()) {
                    EnemyModel enemy = waveList.get(enemyIndex);
                    enemy.setCurrentRow(entranceRow);
                    enemy.setCurrentCol(entranceCol);
                    enemies.add(enemy);
                    enemyIndex++;
                } else {
                    // Once the wave is finished, cancel the enemy spawn timer
                    enemySpawnTimer.cancel();
                    System.out.println("Wave " + currentWave + " finished");
                    currentWave++;

                    // Trigger a 10-second delay before the next wave starts
                    if (currentWave <= 20) {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                // Show wave notification and start the next wave after the 10-second delay
                                System.out.println("Next wave starting!");
                                Wave nextWave = new Wave(currentWave, mapModel, entranceRow, entranceCol);
                                waveList = nextWave.getWave();
                                spawnEnemies();  // Start the next wave
                            }
                        }, 10000);  // 10-second delay before the next wave starts
                    }
                }
            }
        }, 100, 100);  // 2-second delay between enemy spawns within a wave
    }



    // Start the game loop timer for continuous updates
    public void startGameLoop() {
        System.out.println("Game loop starting...");
        if (gameLoopTimer == null) {
            System.out.println("Creating new Timer");
            gameLoopTimer = new Timer();
        }
        setFirstWave();
        // Call the method to spawn enemies once the game starts
        spawnEnemies();

        gameLoopTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateGame();
            }
        }, 500, 100); // run every 100ms, i.e. 10 times per second
    }

    public void updateGame() {
        if (mapModel == null) {
            System.err.println("MapModel is not initialized");
            return;
        }

        // Update all cannons
        for (Cannon cannon : cannons) {
            // Fire at the first enemy in the list for now
            if (!enemies.isEmpty()) {
                cannon.fire(enemies, this);
            }
            cannon.updateProjectiles();  // Update each cannon's projectiles
        }

        // Update all mortars
        for (Mortar mortar : mortars) {
            if (!enemies.isEmpty()) {

                mortar.fire(enemies, this);  // Mortar fires at first enemy
            }
            mortar.updateProjectiles();  // Update each mortar's projectiles
        }

        // Update all lighting towers
        for (Lightning lightning : lightnings) {
            if (!enemies.isEmpty()) {
                lightning.fire(enemies, this);  // Lightning fires at first enemy
            }
            lightning.updateProjectiles();  // Update each lightning's projectiles
        }

        // Update all flame towers
        for (Flame flame : flames) {
            if (!enemies.isEmpty()) {
                flame.fire(enemies, this);  // Flame fires at first enemy
            }
            flame.updateProjectiles();  // Update each flames's projectiles
        }

        // Update all BUGM3LT3R towers
        for (BUGM3LT3R bUGM3LT3R : bUGM3LT3Rs) {
            if (!enemies.isEmpty()) {
                bUGM3LT3R.fire(enemies, this);  // BUGM3LT3R fires at first enemy
            }
            bUGM3LT3R.updateProjectiles();  // Update each BUGM3LT3R's projectiles
        }

        // Update all Boat towers
        for (Boat boat : boats) {
            if (!enemies.isEmpty()) {
                boat.fire(enemies, this);  // BUGM3LT3R fires at first enemy
            }
            boat.updateProjectiles();  // Update each BUGM3LT3R's projectiles
        }

        // Pass the cannons and mortars (with their projectiles) to the MapPanel for drawing
        mapPanel.setCannons(cannons);
        mapPanel.setMortars(mortars);
        mapPanel.setLightnings(lightnings);
        mapPanel.setFlames(flames);
        mapPanel.setBUGM3LT3Rs(bUGM3LT3Rs);
        mapPanel.setBoats(boats);

        // Move each enemy based on the tile map logic
        moveAllEnemies();

        // Force the screen to refresh and redraw the projectiles
        revalidate();
        repaint();
        checkForLoss();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Determine tile size based on the panel dimensions

        int tileWidth = mapPanel.getWidth() / mapModel.getLocations()[0].length;
        int tileHeight = mapPanel.getHeight() / mapModel.getLocations().length;

        if (enemies != null) {
             for (EnemyModel enemy : enemies) {
                // Convert map coordinates to screen coordinates
                int screenX = enemy.getCurrentCol() * tileWidth;
                int screenY = enemy.getCurrentRow() * tileHeight;
                enemy.draw(g, screenX, screenY, tileWidth, tileHeight);
            }
        }
        else {
            System.out.println("Enemies list is null in paintComponent");
        }
    }

    private void checkAnswer() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        String userAnswer = answerField.getText().trim();
        String correctAnswer = questions.getAnswer(currentQuestion);

        // Fetch the question ID based on the current question
        int questionId = questions.getQuestionId(currentQuestion);

        // Disable the answer field immediately when checking the answer
        answerField.setEnabled(false);

        boolean isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);
        if (isCorrect) {
            feedbackLabel.setText("Correct!");
            // Sound effect when a question is answered correctly [UNIMPLEMENTED]
            WAVPlayer questionCorrect_Player = new WAVPlayer("Audio/questionCorrect_SE.wav");
            questionCorrect_Player.setVolume(0.78f);
            questionCorrect_Player.play();;
            updatePoints(500);  // Award points for correct answer
            correctAnswers++;  // Increment correct answer count
            // Log the player's correct answer
            questions.logPlayerAnswer(sessionId, questionId, true);

            // Move to the next question immediately after correct answer
            moveToNextQuestion();
            answerField.setEnabled(true);  // Re-enable answer field
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + correctAnswer);
            incorrectAnswers++;  // Increment incorrect answer count

            // Log the player's incorrect answer
            questions.logPlayerAnswer(sessionId, questionId, false);

            // Start the cool-down timer and wait for 5 seconds before moving to the next question
            startCoolDown();
        }

        // Update the answer result labels
        updateAnswerResultLabel();
    }

    private void updateAnswerResultLabel() {
        // Update the correct and incorrect answers labels
        correctAnswersLabel.setText("Correct answers: " + correctAnswers);
        incorrectAnswersLabel.setText("Incorrect answers: " + incorrectAnswers);
    }

    private void startCoolDown()
    {
        coolDownSeconds = 5;
        countdownLabel.setText("Wait for " + coolDownSeconds + " seconds...");

        // Disable input during cool down
        answerField.setEnabled(false);

        coolDownTimer = new Timer();
        coolDownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (coolDownSeconds > 0) {
                    countdownLabel.setText("Wait for " + coolDownSeconds + " seconds...");
                    coolDownSeconds--;
                } else {
                    // After the cool-down ends, switch to the next question
                    countdownLabel.setText("");
                    coolDownTimer.cancel();
                    moveToNextQuestion();  // Switch to the next question only after the cool-down
                    answerField.setEnabled(true);  // Re-enable input after the cool-down
                }
            }
        }, 0, 1000);  // Execute every 1 second
    }


    public void removeEnemy(EnemyModel enemy) {
        enemies.remove(enemy);  // Remove the enemy from the list
        mapPanel.repaint();     // Repaint to reflect the change visually
    }


    public void checkForVictory() {
            showVictoryPopup();
    }

    private void showVictoryPopup() {
        // Create a custom JPanel for the popup
        JPanel panel = new JPanel(new BorderLayout());
        JLabel message = new JLabel("You win!");
        panel.add(message, BorderLayout.CENTER);

        // Add the button that redirects to the map screen
        JButton backButton = new JButton("Return to Map");
        backButton.addActionListener(e -> {
            // Close the current game window and go to the map screen
            new WorldMapView();
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            currentFrame.dispose();  // Close the current game window
        });

        panel.add(backButton, BorderLayout.SOUTH);

        // Show the popup
        JOptionPane.showMessageDialog(null, panel, "Victory", JOptionPane.PLAIN_MESSAGE);
    }


    public void checkForLoss() {
        if(userHealth <= 0){
            enemySpawnTimer.cancel();
            gameLoopTimer.cancel();
            showLossPopup();
        }
    }

    private void showLossPopup() {
        // Create a custom JPanel for the popup
        JPanel panel = new JPanel(new BorderLayout());
        JLabel message = new JLabel("You lost!");
        panel.add(message, BorderLayout.CENTER);

        // Add the button to go back to the map screen
        JButton backButton = new JButton("Return to World Map");
        backButton.addActionListener(e -> {
            // Close the current game window and go to the map screen
            new WorldMapView();
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            currentFrame.dispose();  // Close the current game window
        });

        panel.add(backButton, BorderLayout.SOUTH);

        // Show the popup
        JOptionPane.showMessageDialog(null, panel, "Game Over", JOptionPane.PLAIN_MESSAGE);
    }





}