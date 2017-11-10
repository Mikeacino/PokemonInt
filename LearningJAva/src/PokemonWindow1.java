import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PokemonWindow1 {
  public PokemonWindow1(Pokemon poke) throws IOException {
    JFrame frame = new JFrame();
    frame.setResizable(false);
    frame.setSize(800, 800);
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();

    String path = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/"
        + "pokemon/" + poke.getPokemonID() + ".png";
    URL url = new URL(path);
    BufferedImage image = ImageIO.read(url);
    JLabel imageLabel = new JLabel(
        new ImageIcon(image.getScaledInstance(400, 400, Image.SCALE_DEFAULT)));

    gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0,
        0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        Double.MIN_VALUE };
    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        0.0, Double.MIN_VALUE };
    frame.getContentPane().setLayout(gridBagLayout);

    ///////////// Name /////////////
    String name = poke.getPokemonName();
    JLabel lblName = new JLabel(
        name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
    lblName.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblName = new GridBagConstraints();
    gbc_lblName.insets = new Insets(0, 0, 5, 5);
    gbc_lblName.gridx = 1;
    gbc_lblName.gridy = 0;
    frame.getContentPane().add(lblName, gbc_lblName);

    ///////////// Level /////////////
    JLabel lblLevel = new JLabel("Lvl: " + poke.getLevel());
    lblLevel.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblLevel = new GridBagConstraints();
    gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
    gbc_lblLevel.gridx = 1;
    gbc_lblLevel.gridy = 2;
    frame.getContentPane().add(lblLevel, gbc_lblLevel);

    ///////////// Sprite /////////////
    GridBagConstraints gbc_imageLabel = new GridBagConstraints();
    gbc_imageLabel.insets = new Insets(0, 0, 5, 5);
    gbc_imageLabel.gridx = 1;
    gbc_imageLabel.gridy = 1;
    frame.getContentPane().add(imageLabel, gbc_imageLabel);

    ///////////// Type1 /////////////
    JLabel lblType = new JLabel(poke.getTypeList(0));
    lblType.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblType = new GridBagConstraints();
    gbc_lblType.insets = new Insets(0, 0, 5, 5);
    gbc_lblType.gridx = 2;
    gbc_lblType.gridy = 0;
    frame.getContentPane().add(lblType, gbc_lblType);

    ///////////// Type2 /////////////
    JLabel lblType_1 = new JLabel(poke.getTypeList(1));
    lblType_1.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblType_1 = new GridBagConstraints();
    gbc_lblType_1.anchor = GridBagConstraints.NORTH;
    gbc_lblType_1.insets = new Insets(0, 0, 5, 5);
    gbc_lblType_1.gridx = 2;
    gbc_lblType_1.gridy = 1;
    frame.getContentPane().add(lblType_1, gbc_lblType_1);

    ///////////// Nature /////////////
    JLabel lblNature = new JLabel(poke.getPokemonNature());
    lblNature.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblNature = new GridBagConstraints();
    gbc_lblNature.insets = new Insets(0, 0, 5, 5);
    gbc_lblNature.gridx = 1;
    gbc_lblNature.gridy = 3;
    frame.getContentPane().add(lblNature, gbc_lblNature);

    ///////////// HP /////////////
    JLabel lblHp = new JLabel("HP: " + poke.getCurrentStat(0));
    lblHp.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblHp = new GridBagConstraints();
    gbc_lblHp.insets = new Insets(0, 0, 5, 5);
    gbc_lblHp.gridx = 2;
    gbc_lblHp.gridy = 2;
    frame.getContentPane().add(lblHp, gbc_lblHp);

    ///////////// Attack /////////////
    JLabel lblAttack = new JLabel("Att: " + poke.getCurrentStat(1));
    lblAttack.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblAttack = new GridBagConstraints();
    gbc_lblAttack.insets = new Insets(0, 0, 5, 5);
    gbc_lblAttack.gridx = 2;
    gbc_lblAttack.gridy = 3;
    frame.getContentPane().add(lblAttack, gbc_lblAttack);

    ///////////// Defense /////////////
    JLabel lblDefense = new JLabel("Def: " + poke.getCurrentStat(2));
    lblDefense.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblDefense = new GridBagConstraints();
    gbc_lblDefense.insets = new Insets(0, 0, 5, 5);
    gbc_lblDefense.gridx = 2;
    gbc_lblDefense.gridy = 4;
    frame.getContentPane().add(lblDefense, gbc_lblDefense);

    ///////////// SpAtt /////////////
    JLabel lblSpecialAttack = new JLabel("SpAtt: " + poke.getCurrentStat(3));
    lblSpecialAttack.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblSpecialAttack = new GridBagConstraints();
    gbc_lblSpecialAttack.insets = new Insets(0, 0, 5, 5);
    gbc_lblSpecialAttack.gridx = 5;
    gbc_lblSpecialAttack.gridy = 2;
    frame.getContentPane().add(lblSpecialAttack, gbc_lblSpecialAttack);

    ///////////// SpDef /////////////
    JLabel lblSpDef = new JLabel("SpDef: " + poke.getCurrentStat(4));
    lblSpDef.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblSpdef = new GridBagConstraints();
    gbc_lblSpdef.insets = new Insets(0, 0, 5, 5);
    gbc_lblSpdef.gridx = 5;
    gbc_lblSpdef.gridy = 3;
    frame.getContentPane().add(lblSpDef, gbc_lblSpdef);

    ///////////// Speed /////////////
    JLabel lblSpeed = new JLabel("Speed: " + poke.getCurrentStat(5));
    lblSpeed.setFont(new Font("Serif", Font.PLAIN, 40));
    GridBagConstraints gbc_lblSpeed = new GridBagConstraints();
    gbc_lblSpeed.insets = new Insets(0, 0, 5, 5);
    gbc_lblSpeed.gridx = 5;
    gbc_lblSpeed.gridy = 4;
    frame.getContentPane().add(lblSpeed, gbc_lblSpeed);
    frame.toFront();
    frame.setVisible(true);
  }
}