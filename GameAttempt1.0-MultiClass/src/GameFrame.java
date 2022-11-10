import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;

public class GameFrame {
    JFrame frame1 = new JFrame();
    Player player;
    Block[] blocks;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int blkSize = 96;
    int blkLimit = (screenWidth / blkSize);
    Random random = new Random();
    private final Set<String> keyDown = new HashSet<>();
    public boolean running;
    int moveX;
    int moveY;

    /**
     * First constructor for GameFrame with 2 parameters
     * also adds a new Player (Character) and a new Block (Platform)
     * @param x sets the X location of GameFrame
     * @param y sets the Y location of GameFrame
     */
    @SuppressWarnings("unused")
    public GameFrame(int x, int y) {
        player = new Player(100, 100, 56, 80);
        blocks = new Block[blkLimit];
        for(int i = 0; i < blkLimit; i++) {
            blocks[i] = new Block((i * blkSize), random.nextInt(screenHeight - blkSize), blkSize);
        }
        start(x, y);
    }

    /**
     * Second constructor for GameFrame with 4 parameters
     * also adds a new Player (Character) and a new Block (Platform)
     * @param x sets the X location of GameFrame
     * @param y sets the Y location of GameFrame
     * @param width sets the Width of GameFrame
     * @param height sets the Height of GameFrame
     */
    @SuppressWarnings("unused")
    public GameFrame(int x, int y, int width, int height) {
        player = new Player(100, 100, 56, 80);
        blocks = new Block[blkLimit];
        for(int i = 0; i < blkLimit; i++) {
            blocks[i] = new Block((i * blkSize), random.nextInt(screenHeight - blkSize), blkSize);
        }
        start(x, y, width, height);
    }

    /**
     * Start method for GameFrame with 2 parameters
     * @param x sets the X location of Frame1 (GameFrame)
     * @param y sets the Y location of Frame1 (GameFrame)
     */
    @SuppressWarnings("unused")
    public void start(int x, int y) {
        running = true;

        frame1.setTitle("GameAttempt");
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setBounds(x, y, 1280, 720);
        frame1.addKeyListener(new MyKeyAdapter());

        Container c = frame1.getContentPane();

        for(int i = 0; i < blkLimit; i++) {
            c.add(blocks[i].plat);
        }
        c.add(player.character);
        frame1.setVisible(true);
        while(running) {
            act();
        }
    }

    /**
     * Start method for GameFrame with 4 parameters
     * @param x sets the X location of Frame1 (GameFrame)
     * @param y sets the Y location of Frame1 (GameFrame)
     * @param width sets the Width of Frame1 (GameFrame)
     * @param height sets the Height of Frame1 (GameFrame)
     */
    @SuppressWarnings("unused")
    public void start(int x, int y, int width, int height) {
        running = true;

        frame1.setTitle("GameAttempt");
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setBounds(x, y, width, height);
        frame1.addKeyListener(new MyKeyAdapter());

        Container c = frame1.getContentPane();

        for(int i = 0; i < blkLimit; i++) {
            c.add(blocks[i].plat);
        }
        c.add(player.character);
        frame1.setVisible(true);
        while(running) {
            act();
        }
    }

    /**
     * Runs once every 2 milliseconds and essentially sets FPS
     */
    public void act() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        move(1, 1);
    }

    /**
     * Checks the KeyDown hashset and moves Character (Player) accordingly
     * also runs the correct Animation for each movement
     *
     * @Notes the redundancy is necessary for having the correct Animations and Collisions
     *
     * @param xMove sets the movement speed for the X axis (Left and Right)
     * @param yMove sets the movement speed for the Y axis (Up and Down)
     */
    public void move(int xMove, int yMove) {
        moveX = xMove;
        moveY = yMove;
        collision();
        if (keyDown.contains("right")) {
            player.animateOnce = 0;
            collision();
            if (keyDown.contains("up")) {
                player.character.setLocation(player.character.getX() + xMove, player.character.getY() - yMove);
                player.animate("Right", 50, 75);
            } else if (keyDown.contains("down")) {
                player.character.setLocation(player.character.getX() + xMove, player.character.getY() + yMove);
                player.animate("Right", 50, 75);
            } else {
                player.character.setLocation(player.character.getX() + xMove, player.character.getY());
                player.animate("Right", 50, 75);
            }
        } else if (keyDown.contains("left")) {
            player.animateOnce = 0;
            collision();
            if (keyDown.contains("up")) {
                player.character.setLocation(player.character.getX() - xMove, player.character.getY() - yMove);
                player.animate("Left", 50, 75);
            } else if (keyDown.contains("down")) {
                player.character.setLocation(player.character.getX() - xMove, player.character.getY() + yMove);
                player.animate("Left", 50, 75);
            } else {
                player.character.setLocation(player.character.getX() - xMove, player.character.getY());
                player.animate("Left", 50, 75);
            }
        } else if (keyDown.contains("up")) {
            player.animateOnce = 0;
            player.character.setLocation(player.character.getX(), player.character.getY() - yMove);
            player.animate("Back", 150, 175);
        } else if (keyDown.contains("down")) {
            player.animateOnce = 0;
            player.character.setLocation(player.character.getX(), player.character.getY() + yMove);
            player.animate("Front", 150, 175);
        } else {
            while(player.animateOnce < 1) {
                player.animateTimer = 0;
                player.animateOnce++;
            }
            player.animate("Front", 275, 350);
        }
    }

    /**
     * Checks Collisions of Player (Character) and every Block (blocks[])
     */
    public void collision() {
        Rectangle playerRect = new Rectangle(player.character.getX(), player.character.getY(), player.charWidth, player.charHeight);

        for (int i = 0; i < blkLimit; i++) {
            Rectangle blockRect = new Rectangle(blocks[i].plat.getX(), blocks[i].plat.getY(), blocks[i].platWidth, blocks[i].platHeight);

            double w = 0.5 * (blockRect.width + playerRect.width);
            double h = 0.5 * (blockRect.height + playerRect.height);
            double dx = blockRect.getCenterX() - playerRect.getCenterX();
            double dy = blockRect.getCenterY() - playerRect.getCenterY();

            if (playerRect.intersects(blockRect)) {
                double wy = w * dy;
                double hx = h * dx;
                if (wy >= hx) {
                    if (wy >= -hx) { // On top of block
                        player.character.setLocation(player.character.getX(), player.character.getY() - moveY);
                    } else { // Right of block
                        player.character.setLocation(player.character.getX() + moveX, player.character.getY());
                    }
                } else {
                    if (wy >= -hx) { // Left of block
                        player.character.setLocation(player.character.getX() - moveX, player.character.getY());
                    } else { // Bottom of block
                        player.character.setLocation(player.character.getX(), player.character.getY() + moveY);
                    }
                }
                i = blkLimit;
            }
        }
    }

    /**
     * Add/remove the key that the User is pressing to KeyDown hashset
     */
    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed (KeyEvent e){
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP, KeyEvent.VK_W -> keyDown.add("up");
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> keyDown.add("down");
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> keyDown.add("left");
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> keyDown.add("right");
                default -> keyDown.clear();
            }
        }

        @Override
        public void keyReleased (KeyEvent e){
            int keyCode = e.getKeyCode();
            switch(keyCode) {
                case KeyEvent.VK_UP, KeyEvent.VK_W -> keyDown.remove("up");
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> keyDown.remove("down");
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> keyDown.remove("left");
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> keyDown.remove("right");
            }
        }
    }
}
