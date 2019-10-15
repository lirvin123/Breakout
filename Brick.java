package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.*;

/**
 * A Brick is a Rectangle that the ball must remove in BreakOut. In this class, bricks can have many
 * different colors (which another class can select).
 *
 * Author: Lily Irvin
 *
 * Acknowledgements: This work was original.
 */

public class Brick extends Rectangle {

    private Color[] colors;

    /**
     * Constructor to create the rectangle object and initialize its instance variables.
     * The default creates a rectangle at position x,y with the specified width and height.
     * The rectangle is drawn with a 1 pixel black stroke outline by default. This constructor also
     * specifies the potential colors of a brick by putting them in a Color array.
     *
     * @param x position (double)
     * @param y position (double)
     * @param width of rectangle (double)
     * @param height of rectangle (double)
     */
    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);

        colors = new Color[6];

        colors[0] = Color.RED;
        colors[1] = Color.ORANGE;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.BLUE;
        colors[5] = Color.CYAN;
    }

    /**
     * Allows other classes/methods to ask for a color in the bricks Color Array.
     * @param index of array
     * @return Color
     */
    public Color getColor(int index) {
        return colors[index];
    }

    /**
     * @return current position of brick
     */
    public String toString() {
        return "Brick Position: " + getPosition();
    }
}
