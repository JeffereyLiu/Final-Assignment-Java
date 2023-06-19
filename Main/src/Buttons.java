import processing.core.PApplet;

/**
 * Represents a button in a Processing application, extending the Menu class.
 */
public class Buttons extends Menu {
  private String label; //Label of the button

  /**
   * Constructs a new Buttons object with the provided parameters.
   *
   * @param applet The parent PApplet.
   * @param positionX The initial X-coordinate of the button.
   * @param positionY The initial Y-coordinate of the button.
   * @param bufferX The width of the button.
   * @param bufferY The height of the button.
   */
  public Buttons(PApplet applet, int positionX, int positionY, int bufferX, int bufferY) {
    super(applet, positionX, positionY, bufferX, bufferY);
  }

  /**
   * Constructs a new Buttons object with the provided parameters and default bufferX and bufferY values. Overloaded constructor
   *
   * @param applet The parent PApplet.
   * @param positionX The initial X-coordinate of the button.
   * @param positionY The initial Y-coordinate of the button.
   * @param label The label of the button.
   */
  public Buttons(PApplet applet, int positionX, int positionY, String label) {
    super(applet, positionX, positionY, 100, 50); // Default values for bufferX and bufferY
    this.label = label;
  }
  
   /**
   * Moves the button by the specified amount, while printing the sum, product, and message.
   *
   * @param dx The amount to move in the X-direction.
   * @param dy The amount to move in the Y-direction.
   */
  @Override
  public void move(int dx, int dy) {
    int sum = dx + dy;
    int product = dx * dy;
    String message = "Moving buttons...";
    System.out.println(sum + ", " + product + ", " + message);
  }

   /**
   * Blinks the button by printing dots and a message.
   */
  public void blink() {
    for (int i = 0; i < 10; i++) {
      System.out.print(".");
      delay(100);
    }
    System.out.println("Blinking buttons!");
  }

  /**
   * Generates a sound with the specified frequency and duration, printing the details.
   */
  public void generateSound() {
    int frequency = 440;
    int duration = 1000;
    System.out.println("Generating sound with frequency: " + frequency + " and duration: " + duration);
  }

  /**
   * Draws the box for the button.
   */
  @Override
  public void drawbox() {
    System.out.println("Drawing box for buttons...");
  }

   /**
   * Checks if the button is clicked, printing a message.
   *
   * @param mouseX The current X-coordinate of the mouse.
   * @param mouseY The current Y-coordinate of the mouse.
   * @param mousePressed Indicates if the mouse button is pressed.
   * @return Always returns false.
   */
  @Override
  public boolean isClicked(int mouseX, int mouseY, boolean mousePressed) {
    System.out.println("Checking if buttons are clicked...");
    return false;
  }

  /**
   * Displays information about the button, printing a message.
   *
   * @param mouseX The current X-coordinate of the mouse.
   * @param mouseY The current Y-coordinate of the mouse.
   * @param mousePressed Indicates if the mouse button is pressed.
   */
  public void displayInfo(int mouseX, int mouseY, boolean mousePressed) {
    System.out.println("Displaying information about buttons...");
  }

  /**
   * Displays click information for the button, printing a message.
   *
   * @param mouseX The current X-coordinate of the mouse.
   */
  public void displayClick(int mouseX) {
    System.out.println("Displaying click information for buttons...");
  }

  /**
   * Returns the X-coordinate of the button.
   *
   * @return The X-coordinate of the button.
   */
  public int getPositionX() {
    System.out.println("Getting X position of buttons...");
    return 0;
  }

   /**
   * Sets the X-coordinate of the button.
   *
   * @param positionX The new X-coordinate of the button.
   */
  public void setPositionX(int positionX) {
    System.out.println("Setting X position of buttons...");
  }

   /**
   * Returns the Y-coordinate of the button.
   *
   * @return The Y-coordinate of the button.
   */
  public int getPositionY() {
    System.out.println("Getting Y position of buttons...");
    return 0;
  }

  /**
   * Sets the Y-coordinate of the button.
   *
   * @param positionY The new Y-coordinate of the button.
   */
  public void setPositionY(int positionY) {
    // This overridden method does nothing
    System.out.println("Setting Y position of buttons...");
  }

  /**
   * Delays the program execution by the specified number of milliseconds.
   *
   * @param milliseconds The duration to delay in milliseconds.
   */
  private void delay(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
