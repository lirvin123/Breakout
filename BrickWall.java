package edu.macalester.comp124.breakout;

import java.util.ArrayList;
import java.util.List;

/**
 * A BrickWall is an array of Bricks that the ball must remove to win BreakOut. It draws these bricks with an arrayList
 * (using different colors). A BrickWall can remove a brick and check whether or not there are remaining bricks
 * on the screen.
 *
 * Author: Lily Irvin
 *
 * Acknowledgements: Some of this code (i.e. the arrayList, removeBricks method, and bricksStillExist method)
 * was inspired by the code from BubbleBlitz (written by the Comp 124 professors).
 */

public class BrickWall {

    private List<Brick> bricks;
    private int numBricks;
    private BreakoutGame canvas;

    /**
     * Constructor to create the brick wall of the BreakOut game. Takes in the requested number of bricks and the
     * canvas to put them on. Then, creates an ArrayList with the capacity of the number of bricks, and then calls
     * the draw bricks method.
     * @param number of bricks (int)
     * @param canvas to put bricks on (CanvasWindow)
     */
    public BrickWall(int number, BreakoutGame canvas) {
        numBricks = number;

        this.canvas = canvas;

        bricks = new ArrayList<>(numBricks);

        drawBricks();
    }

    /**
     * Draws bricks on canvas by created a brick object for each space in the ArrayList. Each row of bricks (new
     * rows are created by checking whether or not the x position has passed the right side of the window) is set
     * to a different color (grabbed from the Brick Class). Then, each brick is added to the canvas and the bricks
     * ArrayList.
     */
    public void drawBricks() {
        int xPosition = 12;
        int yPosition = 30;

        int c = 0;
        for (int x = 0; x < numBricks; x++) {
            Brick brick = new Brick(xPosition, yPosition, 80, 20);
            brick.setFillColor(brick.getColor(c));
            brick.setFilled(true);
            canvas.add(brick);
            bricks.add(brick);

            xPosition += 87;

            if (xPosition > 765) {
                xPosition = 12;
                yPosition += 25;
                c++;
            }
        }
    }

    /**
     * Takes in brick element, and removes it from the canvas and the ArrayList.
     * @param brick to be removed
     */
    public void removeBrick(Brick brick) {
        canvas.remove(brick);
        bricks.remove(brick);
    }

    /**
     * Checks whether or not bricks are still on the screen by checking the size of the ArrayList.
     * @return true if there are still bricks on the screen.
     */
    public boolean bricksStillExist() {
        return bricks.size() > 0;
    }

    /**
     * Returns how many bricks are in the ArrayList/on the screen.
     * @return number of bricks in ArrayList currently
     */
    public int getWallSize() {
        return bricks.size();
    }

    /**
     * @return how many bricks are currently in the ArrayList.
     */
    public String toString() {
        return "BrickWall size: " + getWallSize();
    }
}
