import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;

public class GameFrame {
    JFrame frame1 = new JFrame();
    Player player;
    private final Set<String> keyDown = new HashSet<>();
    public boolean running;

    /**
     * First constructor for GameFrame with 2 parameters
     * Adds a new Player (Character)
     * @param x sets the X location of GameFrame
     * @param y sets the Y location of GameFrame
     */
    @SuppressWarnings("unused")
    public GameFrame(int x, int y) {
        player = new Player(100, 100, 56, 80);
        start(x, y);
    }

    /**
     * Second constructor for GameFrame with 4 parameters
     * Adds a new Player (Character)
     * @param x sets the X location of GameFrame
     * @param y sets the Y location of GameFrame
     * @param width sets the Width of GameFrame
     * @param height sets the Height of GameFrame
     */
    @SuppressWarnings("unused")
    public GameFrame(int x, int y, int width, int height) {
        player = new Player(100, 100, 56, 80);
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

        c.add(player.character);
        frame1.setVisible(true);
        while(running) {
            act();
        }
    }

    /**
     * Runs the Move method once every 2 milliseconds
     * Controls the frames per seconds to make sure Character (Player) doesn't fly off the screen
     */
    public void act() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        move();
    }

    /**
     * Checks the KeyDown hashset and moves Character (Player) accordingly
     */
    public void move() {
        if(keyDown.contains("up")) {
            player.character.setLocation(player.character.getX(), player.character.getY() - 1);
        } else if(keyDown.contains("down")) {
            player.character.setLocation(player.character.getX(), player.character.getY() + 1);
        }
        if(keyDown.contains("left")) {
            player.character.setLocation(player.character.getX() - 1, player.character.getY());
        } else if(keyDown.contains("right")) {
            player.character.setLocation(player.character.getX() + 1, player.character.getY());
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
