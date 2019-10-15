package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;
import java.awt.*;

/**
 * A Paddle is a rectangle that the ball reflects off of in BreakOut. The paddle can move, but only in the X
 * direction.
 *
 * Author: Lily Irvin
 *
 * Acknowledgements: This work was original.
 */

public class Paddle extends Rectangle {
    /**
     * Constructor to create the rectangle object and initialize its instance variables.
     * The default creates a rectangle at position x,y with the specified width and height.
     * The rectangle is drawn with a 1 pixel black stroke outline by default.
     *
     * @param x position (double)
     * @param y position (double)
     * @param width width of rectangle (double)
     * @param height height of rectangle (double)
     */
    public Paddle(double x, double y, double width, double height) {
        super(x, y, width, height);

        setFillColor(Color.BLACK);
        setFilled(true);
    }

    /**
     * Moves the paddle in the x direction to wherever specified.
     * @param xPosition where to move (double)
     */
    public void move(double xPosition) {
        setPosition(xPosition, getY());
    }

    /**
     * @return current position of paddle
     */
    public String toString() {
        return "Paddle position: " + getPosition();
    }
}
