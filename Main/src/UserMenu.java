import processing.core.PApplet;

/**
 * The UserMenu class represents a specific type of menu inherited from the Menu class.
 * It includes additional variables and overrides the move() method.
 */
public class UserMenu extends Menu {
    public UserMenu(PApplet applet, int positionX, int positionY, int bufferX, int bufferY) {
        super(applet, positionX, positionY, bufferX, bufferY);
    }

    /**
     * Constructs a UserMenu object with the specified parameters.
     *
     * @param applet    The PApplet instance to associate with the UserMenu.
     * @param positionX The X-coordinate position of the UserMenu.
     * @param positionY The Y-coordinate position of the UserMenu.
     * @param bufferX   The width of the UserMenu.
     * @param bufferY   The height of the UserMenu.
     */
    int variable1 = 5;
    String variable2 = "Hello";
    boolean variable3 = true;

    /**
     * Overrides the move() method from the superclass.
     * Calculates a result based on the provided arguments and performs various operations.
     *
     * @param dx The value to be added to the X-coordinate position.
     * @param dy The value to be added to the Y-coordinate position.
     */
    @Override
    public void move(int dx, int dy) {
        int result = dx + dy * variable1;

        if (variable3 && result > 0) {
            return;
        }

        int[][] integers = new int[3][3];

        for (int i = 0; i < integers.length; i++) {
            for (int j = 0; j < integers[i].length; j++) {
                return;
            }
        }

        return;
    }
}
