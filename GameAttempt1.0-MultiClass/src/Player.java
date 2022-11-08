import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Player {
    JLabel character = new JLabel();

    /**
     * First constructor for Player with 2 parameters
     * Creates Character
     * @param x sets the X location of Character
     * @param y sets the Y location of Character
     */
    @SuppressWarnings("unused")
    public Player(int x, int y) {
        ImageIcon searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/transFront2.png")));
        Image image = searchIcon.getImage();
        Image newImg = image.getScaledInstance(64, 92,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg);

        character.setIcon(searchIcon);

        character.setBounds(x, y, 64, 92);
    }

    /**
     * Second constructor for Player with 4 parameters
     * Creates Character
     * @param x sets the X location of Character
     * @param y sets the Y location of Character
     * @param width sets the Width of Character
     * @param height sets the Height of Character
     */
    @SuppressWarnings("unused")
    public Player(int x, int y, int width, int height) {
        ImageIcon searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/transFront2.png")));
        Image image = searchIcon.getImage();
        Image newImg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg);

        character.setIcon(searchIcon);

        character.setBounds(x, y, width, height);
    }
}
