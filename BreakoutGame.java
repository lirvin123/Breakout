package edu.macalester.comp124.breakout;


import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsText;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Scanner;

/**
 * Main program for the breakout game.
 *
 * Author: Lily Irvin
 *
 * Acknowledgements: Preceptor Liban me with the getElementAt() calls, and Professor
 * Kluver helped me get the collisions method call to work in this class.
 */
public class BreakoutGame extends CanvasWindow {

    private Paddle paddle;
    private Ball ball;
    private BrickWall wall;
    private GraphicsText victory, loss;
    private static final int BALL_PAUSE = 4;
    private static final int TURN_PAUSE = 3000;
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;

    /**
     * Constructor to create the graphics of the BreakOut Game. Creates the window, and adds a ball, paddle, and
     * brick wall. Also creates graphics text objects for winning and losing, and adds a mouse motion listener to
     * animate the paddle.
     */
    private BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);

        ball = new Ball(400, 400, BALL_PAUSE);
        add(ball);

        wall = new BrickWall(54, this);

        paddle = new Paddle(370, 700, 60, 10);
        add(paddle);

        Font font = new Font("SanSerif", Font.BOLD, 50);
        victory = new GraphicsText("Victory!", 300, 400);
        victory.setFont(font);
        loss = new GraphicsText("You've lost.", 250, 400);
        loss.setFont(font);

        addMouseMotionListener(new moveListener());
    }

    /**
     * Runs a BreakOut game program. Once the run method completes, it asks whether the user would like to play again
     * and acts accordingly (nothing if no, resets and runs again if yes).
     * @param args main method
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BreakoutGame prog = new BreakoutGame();

        prog.run();

        while(true) {
            System.out.println("Enter \"Yes\" to play again: ");
            if (scanner.next().equals("Yes")) {
                prog.reset();
                prog.run();
            }
            else {
                System.out.println("Thanks for playing. You may exit the screen.");
                break;
            }
        }
    }

    /**
     * Runs one game (3 turns). First it starts the ball at the center, then moves the ball. After each move, it checks
     * whether the ball has hit the wall, paddle, or a brick. If the ball hits the bottom of the screen, a new turn begins
     * (which is kept track of by an accumulator). After three turns, or after all the bricks are gone, the game ends.
     * Then the method checks (and relays) whether or not the player has won or lost).
     */
    private void run() {
        int turns = 1;
        pause(TURN_PAUSE);
        ball.startBall();

        while (wall.bricksStillExist()) {
            ball.move();

            ball.checkForWall(this);
            checkForIntersection();

            // Check if ball goes below paddle
            if (ball.getY() >= getHeight()) {
                ball.startBall();
                turns ++;
                if (turns > 3) {
                    break;
                }
                pause(TURN_PAUSE);
            }
            pause(BALL_PAUSE);
        }
        remove(ball);
        result();
    }

    /**
     * Calls the ball's collision method for four points on the ball (compass points -- top, bottom, right, left). If
     * it collides with an object, the appropriate bounce method is called (based on which point intersected an object).
     */
    private void checkForIntersection() {
        if (ball.checkForCollision(ball.getX() + ball.getWidth(), ball.getY() - 2, this, wall)) {
            ball.YBounce();
        }
        else if( ball.checkForCollision(ball.getX() + ball.getWidth(),ball.getY() + ball.getHeight() + 2, this, wall)) {
            ball.YBounce();
        }
        else if(ball.checkForCollision(ball.getX() - 1, ball.getY() + ball.getHeight(), this, wall)) {
            ball.XBounce();
        }
        else if (ball.checkForCollision(ball.getX() + ball.getWidth() + 2, ball.getY() + ball.getHeight(), this, wall)) {
            ball.XBounce();
        }
    }

    /**
     * After a game is complete, this method checks whether or not the player has won or lost (based on if there are
     * still bricks on the screen). Then, the correct message is displayed.
     */
    private void result() {
        if (wall.bricksStillExist()) {
            add(loss);
        }

        else {
            add(victory);
        }
    }

    /**
     * Resets the window by removing all objects, then adding a ball, paddle, and brick wall again.
     */
    private void reset() {
        removeAll();
        add(ball);
        add(paddle);
        wall.drawBricks();
    }

    /**
     * @return How many bricks are currently on the screen, and the position of the ball and paddle.
     */
    public String toString() {
        return "Number of Bricks still on screen: " + wall.getWallSize() + " Ball position: " + ball.getPosition()
                + " Paddle position: " + paddle.getPosition();
    }

    /**
     * Creates a mouse motion listener that animates the paddle.
     */
    private class moveListener implements MouseMotionListener {

        /**
         * This method is not implemented in this class.
         * @param e mouse event
         */
        @Override
        public void mouseDragged(MouseEvent e) {}

        /**
         * When the mouse is moved, the paddle is moved accordingly. The paddle will move so that the
         * middle of the paddle is on the same x plane as the mouse.
         * @param e mouse event (mouse is moved)
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            paddle.move(e.getX() - paddle.getWidth()/2);
        }
    }
}
