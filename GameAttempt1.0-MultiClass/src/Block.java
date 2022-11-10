import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Block {
    JLabel plat = new JLabel();
    ImageIcon searchIcon;
    Image image;
    int platWidth;
    int platHeight;

    /**
     * First constructor for Block with 2 parameters
     * @param x sets the X location of Plat (JLabel)
     * @param y sets the Y location of Plat (JLabel)
     */
    @SuppressWarnings("unused")
    public Block(int x, int y) {
        searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/grass.png")));
        image = searchIcon.getImage();
        Image newImg = image.getScaledInstance(84, 84,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg);

        platWidth = searchIcon.getIconWidth();
        platHeight = searchIcon.getIconHeight();

        plat.setIcon(searchIcon);

        plat.setBounds(x, y, 84, 84);
    }

    /**
     * Second constructor for Block with 3 parameters
     * @param x sets the X location of Plat (JLabel)
     * @param y sets the Y location of Plat (JLabel)
     * @param size sets the Width and Height of Plat (Only results in a square)
     */
    @SuppressWarnings("unused")
    public Block(int x, int y, int size) {
        searchIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/grass.png")));
        image = searchIcon.getImage();
        Image newImg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg);

        platWidth = searchIcon.getIconWidth();
        platHeight = searchIcon.getIconHeight();

        plat.setIcon(searchIcon);

        plat.setBounds(x, y, size, size);
    }
}