import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class represents the main Sketch that extends the PApplet class.
 * It contains the main functionality of the game.
 */
public class Sketch extends PApplet {
  //Create all the variables
  private PImage menubuttons;
  private PImage nextbutton;
  private PImage backbutton;
  private PImage intromenu;
  private PImage introboard;
  private PImage chalkboard;
  private PImage signs;
  private PImage book;
  private PImage bucket;
  private PImage teacher;
  private Menu play_button;
  private Menu help_button;
  private Menu back_button;
  private Menu exit_button;
  private Menu next_button;
  private Menu game_one;
  private Menu game_two;
  private int menu = 0;
  private int shiftx = 0;
  private int shifty = 0;
  private boolean gameOneClicked = false;
  private boolean gameTwoClicked = false;
  private String foodWasteText;

  //User Name variables
  private String playerName = "";
  private boolean nameInputComplete = false;

  private PrintWriter writer;

  // Falling food variables
  private List<PImage> foodImages;
  private List<Food> foodItems;
  private float foodSpeed = 5;
  private int foodInterval = 60;
  private int foodTimer = 0;

  //Falling bomb variables
  private List<PImage> bombImages;
  private List<Bomb> bombItems;
  private float bombSpeed = 10;
  private int bombInterval = 60;
  private int bombTimer = 0;

  // Bucket variables
  private float bucketX;
  private float bucketY;

  // Points variables
  private static int points = 0;
  private boolean gameEnded = false;

  //Countdown 
  private int countdown = 3;

  /**
   * Sets the size of the Sketch screen.
   */
  public void settings() {
    size(962, 640);
  }

  /**
   * Performs the initial setup for the Sketch.
   * Loads images, creates menu buttons, initializes variables, and prepares the output file.
   */
  public void setup() {
    //Load images
    menubuttons = loadImage("images/menubuttons.png");
    backbutton = loadImage("images/backbutton.png");
    nextbutton = loadImage("images/nextbutton.png");
    intromenu = loadImage("images/intromenu.png");
    introboard = loadImage("images/introboard.png");
    chalkboard = loadImage("images/chalkboard.png");
    teacher = loadImage("images/teacher.png");
    signs = loadImage("images/signs.jpg");
    book = loadImage("images/book.png");
    bucket = loadImage("images/bucket.png");

    //Create menu buttons
    play_button = new Menu(this, 412, 385, 120, 35);
    help_button = new Menu(this, 412, 442, 120, 35);
    exit_button = new Menu(this, 412,500, 120, 35);
    back_button = new Menu(this, 25, 25, 50, 50);
    next_button = new Menu(this, 685, 515, 200, 80);
    game_one = new Menu(this, 510, 130, 200, 130);
    game_two = new Menu(this, 260, 285, 150, 130);

    //Create the writer to write to file
    try {
      writer = createWriter("user.txt");
    } catch (Exception e) {
      e.printStackTrace();
    }

    //Load the text about food waste from the file
    loadFoodWasteText();


    // Initialize the food images
    foodImages = new ArrayList<>();
    foodImages.add(loadImage("images/food1.png")); // Replace "food1.jpg" with your food image file names
    foodImages.add(loadImage("images/food2.png")); // Add more food image names as needed

    // Initialize the list of food items
    foodItems = new ArrayList<>();

    // Initialize the bomb images
    bombImages = new ArrayList<>();
    bombImages.add(loadImage("images/bomb1.png"));
    bombImages.add(loadImage("images/bomb2.png"));

    //Initialize the list of bomb items
    bombItems = new ArrayList<>();
    
    // Initialize the bucket position
    bucketX = width / 2; // Initial x position of the bucket at the center of the screen
    bucketY = height - bucket.height; // Initial y position of the bucket at the bottom of the screen
  }

   /**
   * The draw method is called repeatedly to update the game's state and display the visuals.
   */
  public void draw() {
    if (menu == 0) {
      // Code for menu screen
      background(intromenu);
      image(introboard, 270, 105);

      image(menubuttons, 395,380);

      //List of buttons for player to click in the main menu
      if (play_button.isClicked(mouseX,mouseY,mousePressed)){
        menu = 6; 
      }
      if (help_button.isClicked(mouseX,mouseY,mousePressed)){
        help_button.displayClick(mouseX);
        menu = 2;
      }  
      if (exit_button.isClicked(mouseX, mouseY, mousePressed)){
        menu = 5;
      }
      //Menu 1: Play
    } else if (menu == 1) {
      if (game_one.isClicked(mouseX, mouseY, mousePressed)) {
        menu = 3;
      } else if (game_two.isClicked(mouseX, mouseY, mousePressed)) {
        menu = 4;
      } else {
        background(signs);
        fill(0);
        textSize(30);
        text("Food Rain Rescue", 507, 205);
        text("Importance", 260, 325);
        text("Of", 320, 364);  
        text("Food Waste", 260, 400);

      }
      
      image(backbutton, 25, 25);
      if (back_button.isClicked(mouseX, mouseY, mousePressed)) {
        menu = 0;
      }

      //Menu 2: Help
    } else if (menu == 2) {
      // Code for menu 2
      background(255);
      image(chalkboard, 205, 45);
      image(teacher, 5, 270);
      
      fill(255);
      textSize(30);
      text("How To Play:", 230, 95);

      //Explain the game
      textSize(20);
      text("Food Rain Rescue: \nMove the box to catch the food before it hits the ground! \nEvery food you catch, you gain one point. If you miss a food, \none point is deducted. One point is also deducted if you \ncatch a bomb!", 230, 140);

      //Explain the second sign
      text("The Importance of Food Waste: \nRead why food waste is a problem and how you can prevent \nsuch issues!", 230, 330);

      image(backbutton, 25, 25);
      if (back_button.isClicked(mouseX, mouseY, mousePressed)) {
        menu = 0;
      }
    } 
    //Menu 3: The Food Rain Rescue
    if (menu == 3){
        background(0);
        if (countdown > 0){
          fill(255);
          textSize(80);
          text(countdown, width/2 - 20, height/2);
          if (frameCount % 60 == 0){
            countdown--;
          }
        } else {
          // Update and draw the falling food and bomb
          updateBomb();
          drawBomb();
          updateFood();
          drawFood();
          image(bucket, bucketX, bucketY);
          //Check for collision with the bucket
          checkCollision();
          // Display points
          fill(255);
          textSize(20);
          text("Points: " + points, 10, 30);
          // Check for game end
          if (points >= 10) {
            gameEnded = true;
            gameOver();
          } else if (points <= -10){
            gameEnded = true;
            gameLost();
          }
          
          //Checks if the first game is clicked
          if (game_one.isClicked(mouseX, mouseY, mousePressed)) {
            gameOneClicked = true; // Set the flag to true when game_one is clicked
            points = 0; // Reset points when starting a new game
            gameEnded = false; // Reset gameEnded flag
          }
    
          // Move the bucket based on arrow key presses
          if (!gameEnded && keyPressed) {
            if (keyCode == LEFT) {
              bucketX -= 8; // Move the bucket 5 pixels to the left
            } else if (keyCode == RIGHT) {
              bucketX += 8; // Move the bucket 5 pixels to the right
            }
          }
    
          // Constrain the bucket within the screen bounds
          bucketX = constrain(bucketX, 0, width - bucket.width);

        }
        
    }
    
    //Menu 4: The book of knowdledge
    if (menu == 4){
      background(255);
      image(book, 30, 5);
      
      fill(0);
      textSize(20);
      text(foodWasteText, 120, 55);
      text("- Resource Depletion: Food waste \nsquanders resources such as water, \nland and energy used in production \n-Hunger and poverty: Wasting food \nperpetuates global hunger and \npoverty by reducing the \navailability of nutritious food for \nthose in need. \n-Environmental impact:Rotting food \nproduces methane, a potent \ngreenhouse gas, contributing to \nclimate change and \nexacerbating environmental issues.", 115, 95);
      text("What can be done?", 615, 55);
      text("-Public awareness and education: \nPromote awareness about the \nimpacts of food waste through \ncampaigns, educational programs,\nand media to encourage behavioral \nchange.\n-Improved storage and transportation: \nEnhance infrastructure and technologies \nto reduce post-harvest losses and \nimprove the efficiency of food \ndistribution systems.\n-Consumer behavior changes: \nEncourage consumers to properly store\nand consume food to reduce waste.", 490, 95);

      image(backbutton, 25, 25);
      if (back_button.isClicked(mouseX, mouseY, mousePressed)) {
        menu = 1;
      }
    }

    //Menu 5: Exit the game
    if (menu == 5){
      exit();
    }

    //Menu 6: Asks users for their name
    if (menu == 6){
      background(255);

      fill(0);
      textSize(50);
      text("What is your name?: " + playerName, 25, 210);

      image(nextbutton, 685, 515);
      //Checks if the next_button is clicked
      if (next_button.isClicked(mouseX, mouseY, mousePressed)) {
        if (!nameInputComplete) {
          nameInputComplete = true;
          writer.println(playerName); // Write the name to the file
          writer.close(); // Close the PrintWriter
        }
        menu = 1;
      }

      image(backbutton, 25, 25);
      if (back_button.isClicked(mouseX, mouseY, mousePressed)) {
        menu = 0;
      }
    }

    play_button.displayInfo(mouseX, mouseY, mousePressed);
  }

  //Message if the player won the game
  private void gameOver(){
    fill(255);
    textSize(30);
    text("You Won! You scored 10 points.", width/2 - 200, height/2);
    text("Press ENTER to go back to Menu 1.", width/2 - 200, height/2 + 40);
  }
  
  //Message if the player loses the game
  private void gameLost(){
    fill(255);
    textSize(30);
    text("You Lost! You scored -10 points.", width/2 - 200, height/2);
    text("Press SPACE to restart.", width/2 - 200, height/2 + 40);
  }

  // Update the positions of the falling food
  private void updateFood() {
    // Increase the timer
    foodTimer++;

    // Create new food item if the timer reaches the interval
    if (foodTimer >= foodInterval && !gameEnded && points <10) {
      PImage foodImage = getRandomFoodImage();
      float x = random(width - foodImage.width);
      float y = -foodImage.height;
      Food food = new Food(foodImage, x, y);
      foodItems.add(food);
      foodTimer = 0; // Reset the timer
    }

    // Update the positions of existing food items
    for (int i = foodItems.size() - 1; i >= 0; i--) {
      Food food = foodItems.get(i);
      food.update(foodSpeed);

      // Remove food item if it goes off the screen
      if (food.getY() > height) {
        foodItems.remove(i);
        
        //if food item goes below the height of the screen, it subtractsa a point
        if (!gameEnded){
          points--;
        }
      }
    }
  }

  //Update the positions of the falling bombs
  private void updateBomb(){
    bombTimer++; //Increase the timer

    //Create new bomb items if the timer reaches the interval
    if (bombTimer >= bombInterval && !gameEnded && points < 10){
      PImage bombImage = getRandomBombImage();
      float x = random(width - bombImage.width);
      float y = -bombImage.height;
      Bomb bomb = new Bomb(bombImage, x, y);
      bombItems.add(bomb);
      bombTimer = 0; //Reset the timer
    }

    //Update the positions of the existing bomb items
    for (int i = bombItems.size() - 1; i >= 0; i--){
      Bomb bomb = bombItems.get(i);
      bomb.update(bombSpeed);
      
      //Remove bomb item if it goes off the screen
      if (bomb.getY() > height){
        bombItems.remove(i);
      }
    }
  }

  // Draw the falling food
  private void drawFood() {
    for (Food food : foodItems) {
      food.display();
    }
  }

  //Draw the falling bomb
  private void drawBomb(){
    for (Bomb bomb : bombItems){
      bomb.display();
    }
  }

  // Check for collision between the bucket and food items
  private void checkCollision() {
    for (int i = foodItems.size() - 1; i >= 0; i--) {
      Food food = foodItems.get(i);

      // Check for collision with the bucket
      if (collideRectRect(food.getX(), food.getY(), food.getWidth(), food.getHeight(), bucketX, bucketY, bucket.width, bucket.height)) {
        foodItems.remove(i);
        points++; // Increment points on collision with food
      }
    }

    for (int i = bombItems.size() - 1; i>=0; i--){
      Bomb bomb = bombItems.get(i);

      //Check for collision with the bucket
      if (collideRectRect(bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getHeight(), bucketX, bucketY, bucket.width, bucket.height)){
        bombItems.remove(i);
        points--;
      }
    }
    
  }

  // Get a random food image from the available options
  private PImage getRandomFoodImage() {
    int index = floor(random(foodImages.size()));
    return foodImages.get(index);
  }

  private PImage getRandomBombImage(){
    int index = floor(random(bombImages.size()));
    return bombImages.get(index);
  }

  // Food class to represent a falling food item
  private class Food {
    private PImage image;
    private float x;
    private float y;

    public Food(PImage image, float x, float y) {
      this.image = image;
      this.x = x;
      this.y = y;
    }

    public void update(float speed) {
      y += speed;
    }

    public void display() {
      image(image, x, y);
    }

    public float getX() {
      return x;
    }

    public float getY() {
      return y;
    }

    public int getWidth() {
      return image.width;
    }

    public int getHeight() {
      return image.height;
    }
  }

  //Bomb Class representing a falling bomb item
  private class Bomb{
    private PImage image;
    private float x;
    private float y;

    public Bomb(PImage image, float x, float y){
      this.image = image;
      this. x = x;
      this.y = y;
    }

    public void update(float speed){
      y += speed;
    }

    public void display(){
      image(image, x, y);
    }

    public float getX(){
      return x;
    }

    public float getY(){
      return y;
    }

    public int getWidth(){
      return image.width;
    }

    public int getHeight(){
      return image.height;
    }
  }

  /**
 * Checks if two rectangles collide with each other.
 *
 * @param x1 The x-coordinate of the first rectangle's top-left corner.
 * @param y1 The y-coordinate of the first rectangle's top-left corner.
 * @param w1 The width of the first rectangle.
 * @param h1 The height of the first rectangle.
 * @param x2 The x-coordinate of the second rectangle's top-left corner.
 * @param y2 The y-coordinate of the second rectangle's top-left corner.
 * @param w2 The width of the second rectangle.
 * @param h2 The height of the second rectangle.
 * @return true if the rectangles collide, false otherwise.
 */
  private boolean collideRectRect(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
    return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
  }

  /**
 * Handles the keyPressed event.
 */
  public void keyPressed() {
    if (keyCode == ENTER && gameEnded) {
      points = 0;
      gameEnded = false;
      menu = 0; // Go back to menu 1
    }

    if (keyCode == ' ' && gameEnded){
      if (gameEnded){
        menu = 3; //Restart the game
        points = 0; 
        gameEnded = false;
      }
    }

    if (menu == 6) {
      if (!nameInputComplete) {
        if (key != BACKSPACE && key != ENTER && playerName.length() < 30) {
          playerName += key; // Append the typed character to the name string
        } else if (key == BACKSPACE && playerName.length() > 0) {
          playerName = playerName.substring(0, playerName.length() - 1); // Remove the last character
        }
      }
    }

  }

  /**
 * Loads the text about food waste from a file.
 */
  private void loadFoodWasteText(){
    String filename = "foodwaste.txt";
    String [] lines = loadStrings(filename);
    foodWasteText = String.join("\n", lines);
  }
  
}
