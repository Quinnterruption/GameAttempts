import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Player {
    JLabel character = new JLabel();
    ImageIcon searchIcon;
    Image image;
    int animateOnce;
    int animateTimer;
    int frame;
    int charWidth;
    int charHeight;

    /**
     * First constructor for Player with 2 parameters
     * and creates Character
     * @param x sets the X location of Character
     * @param y sets the Y location of Character
     */
     @SuppressWarnings("unused")
    public Player(int x, int y) {
        searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/transFront2.png")));
        image = searchIcon.getImage();
        Image newImg = image.getScaledInstance(64, 92,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg);

        charWidth = searchIcon.getIconWidth();
        charHeight = searchIcon.getIconHeight();

        character.setIcon(searchIcon);
        frame = 1;

        character.setBounds(x, y, 64, 92);
    }

    /**
     * Second constructor for Player with 4 parameters
     * and creates Character
     * @param x sets the X location of Character
     * @param y sets the Y location of Character
     * @param width sets the Width of Character
     * @param height sets the Height of Character
     */
    @SuppressWarnings("unused")
    public Player(int x, int y, int width, int height) {
        searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/transFront2.png")));
        image = searchIcon.getImage();
        Image newImg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg);

        charWidth = searchIcon.getIconWidth();
        charHeight = searchIcon.getIconHeight();

        character.setIcon(searchIcon);
        frame = 1;

        character.setBounds(x, y, width, height);
    }

    /**
     * Creates an Animation for the Character based on the Direction being moved
     * @param direction sets the Direction for the conditions
     * @param startTime sets a base number to auto start
     */
    public void animate(String direction, int startTime, int percent) {
        animateTimer++;
        if((animateTimer + startTime) % percent == 0) {
            if (frame == 1) {
                searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/trans" + direction + "1.png")));
                image = searchIcon.getImage();
                Image newImg = image.getScaledInstance(charWidth, charHeight, java.awt.Image.SCALE_SMOOTH);
                searchIcon = new ImageIcon(newImg);
                frame = 2;
            } else if (frame == 2) {
                    searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/trans" + direction + "2.png")));
                    image = searchIcon.getImage();
                    Image newImg = image.getScaledInstance(charWidth, charHeight, java.awt.Image.SCALE_SMOOTH);
                    searchIcon = new ImageIcon(newImg);
                    frame = 3;
            } else if (frame == 3) {
                    searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/trans" + direction + "3.png")));
                    image = searchIcon.getImage();
                    Image newImg = image.getScaledInstance(charWidth, charHeight, java.awt.Image.SCALE_SMOOTH);
                    searchIcon = new ImageIcon(newImg);
                    frame = 1;
            }
            character.setIcon(searchIcon);
        }
    }
}
