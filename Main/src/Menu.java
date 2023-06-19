import processing.core.PApplet;

/**
 * Represents a menu box in a Processing application.
 */
public class Menu {
  private int positionX; // X-coordinate of the menu box
  private int positionY; // Y-coordinate of the menu box
  private int bufferX; // Width of the menu box
  private int bufferY; // Height of the menu box
  private boolean clicked; // Indicates if the menu box has been clicked
  private PApplet applet; // Reference to the parent PApplet

  
  /**
   * Constructs a new Menu object.
   *
   * @param applet The parent PApplet.
   * @param positionX The initial X-coordinate of the menu box.
   * @param positionY The initial Y-coordinate of the menu box.
   * @param bufferX The width of the menu box.
   * @param bufferY The height of the menu box.
   */
  public Menu(PApplet applet, int positionX, int positionY, int bufferX, int bufferY) {
    this.applet = applet;
    this.positionX = positionX;
    this.positionY = positionY;
    this.bufferX = bufferX;
    this.bufferY = bufferY;
    this.clicked = false;
  }

  /**
   * Moves the menu box by the specified amount.
   *
   * @param dx The amount to move in the X-direction.
   * @param dy The amount to move in the Y-direction.
   */
  public void move(int dx, int dy) {
    positionX += dx;
    positionY += dy;
  }

  /**
   * Draws the menu box.
   */
  public void drawbox() {
    applet.fill(255,0,0);
    applet.rect(positionX, positionY, bufferX, bufferY);
  }

   /**
   * Checks if the menu box has been clicked.
   *
   * @param mouseX The current X-coordinate of the mouse.
   * @param mouseY The current Y-coordinate of the mouse.
   * @param mousePressed Indicates if the mouse button is pressed.
   * @return true if the menu box has been clicked, false otherwise.
   */
  public boolean isClicked(int mouseX, int mouseY, boolean mousePressed) {
    if (mousePressed && !clicked &&
        mouseX > positionX && mouseX < positionX + bufferX &&
        mouseY > positionY && mouseY < positionY + bufferY) {
      clicked = true;
      return true;
    } else if (!mousePressed) {
      clicked = false;
    }
    return false;
  }

  /**
   * Displays information about the mouse and menu box.
   *
   * @param mouseX The current X-coordinate of the mouse.
   * @param mouseY The current Y-coordinate of the mouse.
   * @param mousePressed Indicates if the mouse button is pressed.
   */
  public void displayInfo(int mouseX, int mouseY, boolean mousePressed) {
    applet.fill(0,0,0);
    applet.textSize(15);
    applet.text("Mouse X: " + mouseX, 10, 20);
    applet.text("Mouse Y: " + mouseY, 10, 40);
    applet.text("Position X: " + positionX, 10, 60);
    applet.text("Position Y: " + positionY, 10, 80);
    applet.text("mousePressed: " + mousePressed, 10, 100);
  }

   /**
   * Displays the click information.
   *
   * @param mouseX The current X-coordinate of the mouse.
   */
  public void displayClick(int mouseX) {
    applet.fill(255,0,0);
    applet.text("Clicked: " + mouseX, 10, 200);
  }

   /**
   * Returns the X-coordinate of the menu box.
   *
   * @return The X-coordinate of the menu box.
   */
  public int getPositionX() {
    return positionX;
  }

  /**
   * Sets the X-coordinate of the menu box.
   *
   * @param positionX The new X-coordinate of the menu box.
   */
  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

   /**
   * Returns the Y-coordinate of the menu box.
   *
   * @return The Y-coordinate of the menu box.
   */
  public int getPositionY() {
    return positionY;
  }

  /**
   * Sets the Y-coordinate of the menu box.
   *
   * @param positionY The new Y-coordinate of the menu box.
   */
  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
  
}



