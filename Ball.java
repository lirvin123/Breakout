package edu.macalester.comp124.breakout;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;
import java.awt.*;
import java.util.Random;

/**
 * A ball is an ellipse that moves around the BreakOut window. The ball can check whether it has hit a brick,
 * wall, or paddle, and can bounce off those objects.
 *
 * Author: Lily Irvin
 *
 * Acknowledgements: Preceptor Liban helped me fine tune the collisions method, and Professor Daniel Kluver helped
 * with the X and Y bounce methods.
 */

public class Ball extends Ellipse {

    private static final double BALL_RADIUS = 5;
    private double speedX, speedY;
    private Random random;

    /**
     * Constructor to create the ellipse object and intiialize its instance variables.
     * This default creates an ellipse at position x,y with the width and height (determined
     * by the radius -- a constant). The ellipse is drawn with a 1 pixel black stroke outline
     * by default. This constructor also specifies the velocity in the y direction, given by user.
     *
     * @param xPos upper left X position of ball
     * @param yPos upper left Y position of ball
     * @param speedY width of rectangle
     */

    public Ball(double xPos, double yPos, double speedY) {
        super(xPos, yPos, (2 * BALL_RADIUS), (2 * BALL_RADIUS));

        setFillColor(Color.BLACK);
        setFilled(true);

        this.speedY = speedY;
        random = new Random();
    }

    /**
     * Moves the ball by adding the speedX variable to the x position and the speedY variable to the y position.
     */

    public void move() {
        double newX = getX() + speedX;
        double newY = getY() + speedY;
        setPosition(newX, newY);
    }

    /**
     * Check whether ball has hit the top, right, or left side of the window, and bounces accordingly.
     * @param window ball is on
     */
    public void checkForWall(CanvasWindow window) {
        if (getY() <= 0) {
            YBounce();
        }

        if (getX() + 2 * BALL_RADIUS >= window.getWidth()) {
            XBounce();
        }

        if (getX() <= 0) {
            XBounce();
        }
    }

    /**
     * Takes an x and y position of the ball, and checks whether the ball has collided with the paddle or a
     * brick. If that is the case, it bounces accordingly.
     * @param xPosition passed by user (double)
     * @param yPosition passed by user (double)
     * @param window ball is on (Canvas Window)
     * @param wall of bricks in game (BrickWall)
     * @return true if the ball has hit the paddle or a brick
     */
    public boolean checkForCollision(double xPosition, double yPosition, CanvasWindow window, BrickWall wall) {
        if (window.getElementAt(xPosition, yPosition) instanceof Paddle) {
            return true;
        }

        if (window.getElementAt(xPosition, yPosition) instanceof Brick) {
            wall.removeBrick((Brick) window.getElementAt(xPosition, yPosition));
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Called at the beginning of a game/turn in breakout. Places the ball in the center of the screen,
     * then generates a random integer (within a range) as the speedX to simulate a random angle.
     */
    public void startBall() {
        setPosition(400, 400);
        speedX = random.nextInt(3) + 1;
        if (random.nextBoolean()) {
            speedX = -speedX;
        }
    }

    /**
     * Reverses the x velocity (called when the ball has hit the right or left side of the window, paddle, or a brick.
     */
    public void XBounce() {
        speedX = -(speedX);
    }

    /**
     * Reverses the y velocity (called when the ball has hit the top or bottom of the window, paddle, or a brick.
     */
    public void YBounce() {
        speedY = -(speedY);
    }

    /**
     * @return Current position fo the ball.
     */
    public String toString() {
        return "Ball Position: " + getPosition();
    }
}
