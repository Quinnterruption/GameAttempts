import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Player {
    JLabel character = new JLabel();
    ImageIcon searchIcon;
    int animateOnce;
    int animateTimer;
    int frame = 1;
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
        Image newImg = searchIcon.getImage().getScaledInstance(64, 92,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg);

        charWidth = searchIcon.getIconWidth();
        charHeight = searchIcon.getIconHeight();

        character.setIcon(searchIcon);

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
        Image newImg = searchIcon.getImage().getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg);

        charWidth = searchIcon.getIconWidth();
        charHeight = searchIcon.getIconHeight();

        character.setIcon(searchIcon);

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
                if(!(Objects.equals(direction, "Front"))) {
                    frame = 2;
                } else {
                    frame = 3;
                }
            } else if (frame == 2) {
                searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/trans" + direction + "2.png")));
                frame = 3;
            } else if (frame == 3) {
                searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/trans" + direction + "3.png")));
                frame = 1;
            }
            Image newImg = searchIcon.getImage().getScaledInstance(charWidth, charHeight, java.awt.Image.SCALE_SMOOTH);
            searchIcon = new ImageIcon(newImg);
            character.setIcon(searchIcon);
        }
    }
}
