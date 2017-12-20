
///////////// Michael Carracino /////////////
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.border.LineBorder;

public class PokemonStatsDisplayWindow {
  public void buildStatsDisplayWindow(Pokemon poke) throws IOException {

    ///////////// Frame /////////////
    JFrame pokemonFrame = new JFrame("Pokemon Info Page");
    pokemonFrame.setResizable(false);
    pokemonFrame.setLocationRelativeTo(null);
    pokemonFrame.setSize(800, 800);

    ///////////// Image /////////////
    String imagePath;
    if (poke.getIsShiny() == true) {
      imagePath = "sprites/pokemon/shiny/" + poke.getPokemonID() + ".png";

    } else {
      imagePath = "sprites/pokemon/" + poke.getPokemonID() + ".png";
    }
    BufferedImage image = ImageIO.read(new File(imagePath));
    JLabel imageLabel = new JLabel(
        new ImageIcon(image.getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
    imageLabel.setBounds(20, 20, 400, 400);
    imageLabel.setForeground(Color.WHITE);
    imageLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
    pokemonFrame.getContentPane().add(imageLabel);

    ///////////// Name /////////////
    String name = poke.getPokemonName();
    pokemonFrame.getContentPane().setLayout(null);
    JLabel lblName = new JLabel(
        name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
    lblName.setBounds(510, 21, 199, 52);
    lblName.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblName);

    ///////////// Type1 /////////////
    JLabel lblType1 = new JLabel(poke.getTypeList(0));
    lblType1.setHorizontalAlignment(SwingConstants.CENTER);
    lblType1.setBounds(606, 112, 135, 60);
    int[] typeB = poke.getTypeColor(poke.getTypeList(0));
    lblType1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
    lblType1.setBackground(new Color(typeB[0], typeB[1], typeB[2]));
    lblType1.setForeground(Color.WHITE);
    lblType1.setOpaque(true);
    lblType1.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblType1);

    ///////////// Type2 /////////////
    if (poke.getTypeList(1) != null) {
      JLabel lblType_2 = new JLabel(poke.getTypeList(1));
      lblType_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblType_2.setSize(135, 60);
      lblType_2.setLocation(441, 112);
      int[] typeA = poke.getTypeColor(poke.getTypeList(1));
      lblType_2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
      lblType_2.setBackground(new Color(typeA[0], typeA[1], typeA[2]));
      lblType_2.setForeground(Color.WHITE);
      lblType_2.setOpaque(true);
      lblType_2.setFont(new Font("Serif", Font.PLAIN, 40));
      GridBagConstraints gbc_lblType_2 = new GridBagConstraints();
      gbc_lblType_2.insets = new Insets(0, 0, 5, 5);
      gbc_lblType_2.gridx = 5;
      gbc_lblType_2.gridy = 0;
      pokemonFrame.getContentPane().add(lblType_2, gbc_lblType_2);
    }

    ///////////// Level /////////////
    JLabel lblLevel = new JLabel("Lvl: " + poke.getLevel());
    lblLevel.setBounds(441, 194, 135, 60);
    lblLevel.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblLevel);

    ///////////// Nature /////////////
    JLabel lblNature = new JLabel(poke.getPokemonNature());
    lblNature.setBounds(606, 193, 135, 60);
    lblNature.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblNature);

    ///////////// HP /////////////
    JLabel lblHp = new JLabel("HP: " + poke.getCurrentStat(0));
    lblHp.setBounds(30, 450, 150, 52);
    lblHp.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblHp);

    ///////////// Attack /////////////
    JLabel lblAttack = new JLabel("Att: " + poke.getCurrentStat(1));
    lblAttack.setBounds(30, 520, 150, 52);
    lblAttack.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblAttack);

    ///////////// Defense /////////////
    JLabel lblDefense = new JLabel("Def: " + poke.getCurrentStat(2));
    lblDefense.setBounds(30, 590, 150, 52);
    lblDefense.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblDefense);

    ///////////// SpAtt /////////////
    JLabel lblSpecialAttack = new JLabel("SpAtt: " + poke.getCurrentStat(3));
    lblSpecialAttack.setBounds(221, 450, 200, 52);
    lblSpecialAttack.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblSpecialAttack);

    ///////////// SpDef /////////////
    JLabel lblSpDef = new JLabel("SpDef: " + poke.getCurrentStat(4));
    lblSpDef.setBounds(221, 520, 200, 52);
    lblSpDef.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblSpDef);

    ///////////// Speed /////////////
    JLabel lblSpeed = new JLabel("Speed: " + poke.getCurrentStat(5));
    lblSpeed.setBounds(221, 590, 200, 52);
    lblSpeed.setFont(new Font("Serif", Font.PLAIN, 40));
    pokemonFrame.getContentPane().add(lblSpeed);
    pokemonFrame.toFront();
    pokemonFrame.setVisible(true);
  }
}