import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import java.util.Random;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.border.LineBorder;

/**
 * Creates the window for the Pokedex.
 * 
 * @author Michael Carracino
 */
public class PokedexWindow {
  /**
   * Randomly selects a Pokedex page to display. Works with the 'Random' button.
   */
  public void randomWindow() {
    Random rand = new Random();
    int randomPokemon = rand.nextInt(801) + 1;
    try {
      buildWindow(randomPokemon);
    } catch (Exception e) {
      ErrorWindow error = new ErrorWindow();
      error.buildWindow(e);
    }
  }

  /**
   * Builds the Pokedex page of a known Pokemon.
   * 
   * @param pokemonId
   *          The ID of a specific Pokemon.
   */
  public void buildWindow(int pokemonId) {
    try {
      Pokemon pokedexPoke = new Pokemon(pokemonId);
      pokedexPoke.setLevel(100);
      pokedexPoke.calculateAbsoluteMaxStats();
      pokedexPoke.calculateAbsoluteMinStats();
      pokedexPoke.findFlavorText(pokemonId);
      JFrame pokedexFrame = new JFrame("Pokedex");
      pokedexFrame.setResizable(false);
      pokedexFrame.setSize(1500, 1500);
      pokedexFrame.setResizable(false);

      ///////////// Image /////////////
      String imagePath;
      if (pokemonId < 719) {
        imagePath = "sprites/pokemon/model/" + pokedexPoke.getPokemonID()
            + ".png";
      } else {
        imagePath = "sprites/pokemon/" + pokedexPoke.getPokemonID() + ".png";
      }
      BufferedImage image = ImageIO.read(new File(imagePath));
      pokedexFrame.getContentPane().setLayout(null);
      JLabel imageLabel = new JLabel(new ImageIcon(
          image.getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
      imageLabel.setBounds(40, 300, 500, 500);
      imageLabel.setForeground(Color.WHITE);
      imageLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      pokedexFrame.getContentPane().add(imageLabel);

      ///////////// Name /////////////
      String name = pokedexPoke.getPokemonName();
      name = name.substring(0, 1).toUpperCase()
          + name.substring(1, name.length());
      JLabel pokemonName = new JLabel(name, SwingConstants.CENTER);
      pokemonName.setFont(new Font("Serif", Font.PLAIN, 65));
      pokemonName.setBounds(115, 40, 350, 80);
      pokedexFrame.getContentPane().add(pokemonName);

      ///////////// Type1 /////////////
      JLabel typeOneLabel = new JLabel(pokedexPoke.getTypeList(0));
      typeOneLabel.setHorizontalAlignment(SwingConstants.CENTER);
      if (pokedexPoke.getTypeList(1) == null) {
        typeOneLabel.setBounds(470, 70, 120, 60);
      } else {
        typeOneLabel.setBounds(470, 30, 120, 60);
      }
      int[] typeA = pokedexPoke.getTypeColor(pokedexPoke.getTypeList(0));
      typeOneLabel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
      typeOneLabel.setBackground(new Color(typeA[0], typeA[1], typeA[2]));
      typeOneLabel.setForeground(Color.WHITE);
      typeOneLabel.setOpaque(true);
      typeOneLabel.setFont(new Font("Serif", Font.PLAIN, 30));
      pokedexFrame.getContentPane().add(typeOneLabel);

      ///////////// Type2 /////////////
      if (pokedexPoke.getTypeList(1) != null) {
        JLabel typeTwoLabel = new JLabel(pokedexPoke.getTypeList(1));
        typeTwoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        typeTwoLabel.setBounds(470, 110, 120, 60);
        int[] typeB = pokedexPoke.getTypeColor(pokedexPoke.getTypeList(1));
        typeTwoLabel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
        typeTwoLabel.setBackground(new Color(typeB[0], typeB[1], typeB[2]));
        typeTwoLabel.setForeground(Color.WHITE);
        typeTwoLabel.setOpaque(true);
        typeTwoLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        GridBagConstraints typeTwoLabelGbc = new GridBagConstraints();
        typeTwoLabelGbc.insets = new Insets(0, 0, 5, 5);
        typeTwoLabelGbc.gridx = 5;
        typeTwoLabelGbc.gridy = 0;
        pokedexFrame.getContentPane().add(typeTwoLabel, typeTwoLabelGbc);
      }

      ///////////////// ID# /////////////////
      JLabel labelId = new JLabel("#" + pokemonId);
      labelId.setFont(new Font("Serif", Font.PLAIN, 30));
      labelId.setBounds(50, 89, 60, 26);
      pokedexFrame.getContentPane().add(labelId);

      ///////////////// FlavorText /////////////////
      JLabel lblNewLabel = new JLabel(
          "<html>" + pokedexPoke.getFlavorText() + "<html>");
      lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
      lblNewLabel.setMaximumSize(new Dimension(830, 101));
      lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
      lblNewLabel.setSize(830, 150);
      lblNewLabel.setLocation(605, 300);
      pokedexFrame.getContentPane().add(lblNewLabel);

      ///////////////// HP Label /////////////////
      JLabel labelHp = new JLabel("HP", SwingConstants.CENTER);
      labelHp.setForeground(Color.BLACK);
      labelHp.setFont(new Font("Serif", Font.PLAIN, 30));
      labelHp.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelHp.setBounds(754, 519, 100, 40);
      pokedexFrame.getContentPane().add(labelHp);

      JLabel labelHpBase = new JLabel(pokedexPoke.getBaseStats(0) + "",
          SwingConstants.CENTER);
      labelHpBase.setFont(new Font("Serif", Font.PLAIN, 30));
      labelHpBase.setBounds(new Rectangle(0, 700, 150, 0));
      labelHpBase.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelHpBase.setBounds(754, 558, 100, 40);
      pokedexFrame.getContentPane().add(labelHpBase);

      JLabel labelHpMin = new JLabel(
          Integer.toString(pokedexPoke.getMinStat(0)), SwingConstants.CENTER);
      labelHpMin.setForeground(Color.RED);
      labelHpMin.setFont(new Font("Serif", Font.PLAIN, 30));
      labelHpMin.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelHpMin.setBounds(754, 597, 100, 40);
      pokedexFrame.getContentPane().add(labelHpMin);

      JLabel labelHpMax = new JLabel(
          Integer.toString(pokedexPoke.getMaxStat(0)), SwingConstants.CENTER);
      labelHpMax.setForeground(Color.BLUE);
      labelHpMax.setFont(new Font("Serif", Font.PLAIN, 30));
      labelHpMax.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelHpMax.setBounds(754, 636, 100, 40);
      pokedexFrame.getContentPane().add(labelHpMax);

      ///////////////// Att Label /////////////////
      JLabel labelAttack = new JLabel("Att", SwingConstants.CENTER);
      labelAttack.setForeground(Color.BLACK);
      labelAttack.setFont(new Font("Serif", Font.PLAIN, 30));
      labelAttack.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelAttack.setBounds(853, 519, 100, 40);
      pokedexFrame.getContentPane().add(labelAttack);

      JLabel labelAttackBase = new JLabel(pokedexPoke.getBaseStats(1) + "",
          SwingConstants.CENTER);
      labelAttackBase.setFont(new Font("Serif", Font.PLAIN, 30));
      labelAttackBase.setBounds(new Rectangle(0, 700, 150, 0));
      labelAttackBase.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelAttackBase.setBounds(853, 558, 100, 40);
      pokedexFrame.getContentPane().add(labelAttackBase);

      JLabel labelAttackMin = new JLabel(
          Integer.toString(pokedexPoke.getMinStat(1)), SwingConstants.CENTER);
      labelAttackMin.setForeground(Color.RED);
      labelAttackMin.setFont(new Font("Serif", Font.PLAIN, 30));
      labelAttackMin.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelAttackMin.setBounds(853, 597, 100, 40);
      pokedexFrame.getContentPane().add(labelAttackMin);

      JLabel labelAttackMax = new JLabel(
          Integer.toString(pokedexPoke.getMaxStat(1)), SwingConstants.CENTER);
      labelAttackMax.setForeground(Color.BLUE);
      labelAttackMax.setFont(new Font("Serif", Font.PLAIN, 30));
      labelAttackMax.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelAttackMax.setBounds(853, 636, 100, 40);
      pokedexFrame.getContentPane().add(labelAttackMax);

      ///////////////// Def Label /////////////////
      JLabel labelDefense = new JLabel("Def", SwingConstants.CENTER);
      labelDefense.setFont(new Font("Serif", Font.PLAIN, 30));
      labelDefense.setBounds(new Rectangle(0, 700, 150, 0));
      labelDefense.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelDefense.setBounds(952, 519, 100, 40);
      pokedexFrame.getContentPane().add(labelDefense);

      JLabel labelDefenseBase = new JLabel(pokedexPoke.getBaseStats(2) + "",
          SwingConstants.CENTER);
      labelDefenseBase.setFont(new Font("Serif", Font.PLAIN, 30));
      labelDefenseBase.setBounds(new Rectangle(0, 700, 150, 0));
      labelDefenseBase.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelDefenseBase.setBounds(952, 558, 100, 40);
      pokedexFrame.getContentPane().add(labelDefenseBase);

      JLabel labelDefenseMin = new JLabel(
          Integer.toString(pokedexPoke.getMinStat(2)), SwingConstants.CENTER);
      labelDefenseMin.setForeground(Color.RED);
      labelDefenseMin.setFont(new Font("Serif", Font.PLAIN, 30));
      labelDefenseMin.setBounds(new Rectangle(0, 700, 150, 0));
      labelDefenseMin.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelDefenseMin.setBounds(952, 597, 100, 40);
      pokedexFrame.getContentPane().add(labelDefenseMin);

      JLabel labelDefenseMax = new JLabel(
          Integer.toString(pokedexPoke.getMaxStat(2)), SwingConstants.CENTER);
      labelDefenseMax.setForeground(Color.BLUE);
      labelDefenseMax.setFont(new Font("Serif", Font.PLAIN, 30));
      labelDefenseMax.setBounds(new Rectangle(0, 700, 150, 0));
      labelDefenseMax.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelDefenseMax.setBounds(952, 636, 100, 40);
      pokedexFrame.getContentPane().add(labelDefenseMax);

      ///////////////// SpAtt Label /////////////////
      JLabel labelSpAtt = new JLabel("SpAtt", SwingConstants.CENTER);
      labelSpAtt.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpAtt.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpAtt.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpAtt.setBounds(1051, 519, 100, 40);
      pokedexFrame.getContentPane().add(labelSpAtt);

      JLabel labelSpAttBase = new JLabel(pokedexPoke.getBaseStats(3) + "",
          SwingConstants.CENTER);
      labelSpAttBase.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpAttBase.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpAttBase.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpAttBase.setBounds(1051, 558, 100, 40);
      pokedexFrame.getContentPane().add(labelSpAttBase);

      JLabel labelSpAttMin = new JLabel(
          Integer.toString(pokedexPoke.getMinStat(3)), SwingConstants.CENTER);
      labelSpAttMin.setForeground(Color.RED);
      labelSpAttMin.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpAttMin.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpAttMin.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpAttMin.setBounds(1051, 597, 100, 40);
      pokedexFrame.getContentPane().add(labelSpAttMin);

      JLabel labelSpAttMax = new JLabel(
          Integer.toString(pokedexPoke.getMaxStat(3)), SwingConstants.CENTER);
      labelSpAttMax.setForeground(Color.BLUE);
      labelSpAttMax.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpAttMax.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpAttMax.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpAttMax.setBounds(1051, 636, 100, 40);
      pokedexFrame.getContentPane().add(labelSpAttMax);

      ///////////////// SpDefLabel /////////////////
      JLabel labelSpDef = new JLabel("SpDef", SwingConstants.CENTER);
      labelSpDef.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpDef.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpDef.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpDef.setBounds(1150, 519, 100, 40);
      pokedexFrame.getContentPane().add(labelSpDef);

      JLabel labelSpDefBase = new JLabel(pokedexPoke.getBaseStats(4) + "",
          SwingConstants.CENTER);
      labelSpDefBase.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpDefBase.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpDefBase.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpDefBase.setBounds(1150, 558, 100, 40);
      pokedexFrame.getContentPane().add(labelSpDefBase);

      JLabel labelSpDefMin = new JLabel(
          Integer.toString(pokedexPoke.getMinStat(4)), SwingConstants.CENTER);
      labelSpDefMin.setForeground(Color.RED);
      labelSpDefMin.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpDefMin.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpDefMin.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpDefMin.setBounds(1150, 597, 100, 40);
      pokedexFrame.getContentPane().add(labelSpDefMin);

      JLabel labelSpDefMax = new JLabel(
          Integer.toString(pokedexPoke.getMaxStat(4)), SwingConstants.CENTER);
      labelSpDefMax.setForeground(Color.BLUE);
      labelSpDefMax.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpDefMax.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpDefMax.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpDefMax.setBounds(1150, 636, 100, 40);
      pokedexFrame.getContentPane().add(labelSpDefMax);

      ///////////////// Speed /////////////////
      JLabel labelSpeed = new JLabel("Speed", SwingConstants.CENTER);
      labelSpeed.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpeed.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpeed.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpeed.setBounds(1249, 519, 100, 40);
      pokedexFrame.getContentPane().add(labelSpeed);

      JLabel labelSpeedBase = new JLabel(pokedexPoke.getBaseStats(5) + "",
          SwingConstants.CENTER);
      labelSpeedBase.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpeedBase.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpeedBase.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpeedBase.setBounds(1249, 558, 100, 40);
      pokedexFrame.getContentPane().add(labelSpeedBase);

      JLabel labelSpeedMin = new JLabel(
          Integer.toString(pokedexPoke.getMinStat(5)), SwingConstants.CENTER);
      labelSpeedMin.setForeground(Color.RED);
      labelSpeedMin.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpeedMin.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpeedMin.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpeedMin.setBounds(1249, 597, 100, 40);
      pokedexFrame.getContentPane().add(labelSpeedMin);

      JLabel labelSpeedMax = new JLabel(
          Integer.toString(pokedexPoke.getMaxStat(5)), SwingConstants.CENTER);
      labelSpeedMax.setForeground(Color.BLUE);
      labelSpeedMax.setFont(new Font("Serif", Font.PLAIN, 30));
      labelSpeedMax.setBounds(new Rectangle(0, 700, 150, 0));
      labelSpeedMax.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelSpeedMax.setBounds(1249, 636, 100, 40);
      pokedexFrame.getContentPane().add(labelSpeedMax);

      ///////////////// Min Label /////////////////
      JLabel lblMin = new JLabel("Min", SwingConstants.CENTER);
      lblMin.setForeground(Color.RED);
      lblMin.setFont(new Font("Serif", Font.PLAIN, 30));
      lblMin.setBorder(new LineBorder(new Color(0, 0, 0)));
      lblMin.setBounds(655, 597, 100, 40);
      pokedexFrame.getContentPane().add(lblMin);

      ///////////////// Max Label /////////////////
      JLabel lblMax = new JLabel("Max", SwingConstants.CENTER);
      lblMax.setForeground(Color.BLUE);
      lblMax.setFont(new Font("Serif", Font.PLAIN, 30));
      lblMax.setBorder(new LineBorder(new Color(0, 0, 0)));
      lblMax.setBounds(655, 636, 100, 40);
      pokedexFrame.getContentPane().add(lblMax);

      ///////////////// Stats Label /////////////////
      JLabel labelStats = new JLabel("Stats", SwingConstants.CENTER);
      labelStats.setForeground(Color.BLACK);
      labelStats.setFont(new Font("Serif", Font.PLAIN, 30));
      labelStats.setBorder(new LineBorder(new Color(0, 0, 0)));
      labelStats.setBounds(655, 519, 100, 40);
      pokedexFrame.getContentPane().add(labelStats);

      ///////////////// Base Label /////////////////
      JLabel lblBase = new JLabel("Base", SwingConstants.CENTER);
      lblBase.setFont(new Font("Serif", Font.PLAIN, 30));
      lblBase.setBounds(new Rectangle(0, 700, 150, 0));
      lblBase.setBorder(new LineBorder(new Color(0, 0, 0)));
      lblBase.setBounds(655, 558, 100, 40);
      pokedexFrame.getContentPane().add(lblBase);

      ///////////////// Previous Button /////////////////
      JButton previousButton = new JButton("Previous");
      previousButton.setFont(new Font("Serif", Font.PLAIN, 40));
      previousButton.setBounds(680, 21, 200, 60);
      previousButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          try {
            buildWindow(pokemonId - 1);
            pokedexFrame.dispose();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
      if (pokemonId == 1) {
        previousButton.setEnabled(false);
      } else {
        previousButton.setEnabled(true);
      }
      pokedexFrame.getContentPane().add(previousButton);

      ///////////////// Random Button /////////////////
      JButton randomBtn = new JButton("Random");
      randomBtn.setFont(new Font("Serif", Font.PLAIN, 40));
      randomBtn.setBounds(900, 21, 200, 60);
      randomBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          try {
            randomWindow();
            pokedexFrame.dispose();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
      pokedexFrame.getContentPane().add(randomBtn);

      ///////////////// Next Button /////////////////
      JButton nextButton = new JButton("Next");
      nextButton.setFont(new Font("Serif", Font.PLAIN, 40));
      nextButton.setBounds(1120, 21, 200, 60);
      nextButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          try {
            buildWindow(pokemonId + 1);
            pokedexFrame.dispose();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
      if (pokemonId == 802) {
        nextButton.setEnabled(false);
      } else {
        nextButton.setEnabled(true);
      }
      pokedexFrame.getContentPane().add(nextButton);

      ///////////////// Name Button /////////////////
      String[] nameArray = createPokemonNameArray();
      JComboBox<String> textFieldPokeId = new JComboBox<String>(nameArray);
      textFieldPokeId.setFont(new Font("Serif", Font.PLAIN, 50));
      textFieldPokeId.setBounds(700, 100, 600, 60);
      textFieldPokeId.setSelectedIndex(pokemonId - 1);
      textFieldPokeId.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          try {
            buildWindow(textFieldPokeId.getSelectedIndex() + 1);
            pokedexFrame.dispose();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
      pokedexFrame.getContentPane().add(textFieldPokeId);

      ///////////////// Weight /////////////////
      JLabel lblWeight = new JLabel(
          "Wt: " + pokedexPoke.getPokemonWeight() + " kg");
      lblWeight.setFont(new Font("Serif", Font.PLAIN, 40));
      lblWeight.setBounds(60, 146, 350, 50);
      pokedexFrame.getContentPane().add(lblWeight);

      ///////////////// Height /////////////////
      JLabel lblHeight = new JLabel(
          "Ht: " + pokedexPoke.getPokemonHeight() + " m");
      lblHeight.setFont(new Font("Serif", Font.PLAIN, 40));
      lblHeight.setBounds(70, 193, 350, 50);
      pokedexFrame.getContentPane().add(lblHeight);

      ///////////////// Move Table /////////////////
      pokedexPoke.getPokemonMoveMap(pokemonId);
      String[] colNames = { "Name", "Type", "Power", "pp", "Accuracy",
          "DamageType" };
      JTable moveTable = new JTable(pokedexPoke.getPokemonMoveArray(pokemonId),
          colNames);
      moveTable.setFont(new Font("Serif", Font.PLAIN, 40));
      moveTable.setRowHeight(70);
      JScrollPane scrollPane = new JScrollPane(moveTable);
      scrollPane.setBounds(50, 850, 1400, 550);
      scrollPane.setVisible(true);

      pokedexFrame.getContentPane().add(scrollPane);

      pokedexFrame.setVisible(true);
    } catch (Exception e) {
      ErrorWindow error = new ErrorWindow();
      error.buildWindow(e);
    }
  }

  /**
   * Creates an array of all Pokemon names with some formatting to make it look
   * nice. the array is needed to make the drop down menu of all Pokemon.
   * 
   * @return the array with the names of very Pokemon.
   */
  public String[] createPokemonNameArray() {
    String[] temporaryArray = new String[802];
    int countId = 1;
    String currentId;
    String line;
    String[] splitLine;
    String name;
    try {
      FileReader file = new FileReader("pokemon.csv");
      BufferedReader reader = new BufferedReader(file);
      reader.readLine();
      for (int i = 0; i < 802; i++) {
        currentId = String.format("%03d", countId);
        line = reader.readLine();
        if (line != null) {
          splitLine = line.split(",");
          name = splitLine[1];
          temporaryArray[i] = (currentId + ": "
              + name.substring(0, 1).toUpperCase()
              + name.substring(1, name.length()));
          countId++;
        }
      }
      reader.close();
    } catch (Exception e) {
      ErrorWindow error = new ErrorWindow();
      error.buildWindow(e);
    }
    return temporaryArray;

  }
}
