import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class PokedexWindow {
  private JComboBox<?> textField_Poke_ID;
  
  public PokedexWindow() throws Exception {
    Random rand = new Random();
    int randomPokemon = rand.nextInt(801) + 1;
    buildWindow(randomPokemon);
  }

   public PokedexWindow(int ID) throws Exception {
   buildWindow(ID);
   }

  @SuppressWarnings("unchecked")
  public void buildWindow(int ID) throws Exception {
    Pokemon pokedexPoke = new Pokemon(ID);
    JFrame pokedexFrame = new JFrame("Pokedex");
    pokedexPoke.setLevel(100);
    pokedexPoke.calculateAbsoluteMaxStats();
    pokedexPoke.calculateAbsoluteMinStats();
    pokedexFrame.setResizable(false);
    pokedexFrame.setSize(1000, 1000);
    pokedexFrame.setResizable(false);

    ///////////// Image /////////////
    String imagePath;
    imagePath = "sprites/pokemon/" + pokedexPoke.getPokemonID() + ".png";
    BufferedImage image = ImageIO.read(new File(imagePath));
    pokedexFrame.getContentPane().setLayout(null);
    JLabel imageLabel = new JLabel(
        new ImageIcon(image.getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
    imageLabel.setBounds(40, 40, 400, 400);
    imageLabel.setForeground(Color.WHITE);
    imageLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
    pokedexFrame.getContentPane().add(imageLabel);

    ///////////// Name /////////////
    String name = pokedexPoke.getPokemonName();
    name = name.substring(0, 1).toUpperCase()
        + name.substring(1, name.length());
    JLabel pokemonName = new JLabel(name, SwingConstants.CENTER);
    pokemonName.setFont(new Font("Serif", Font.PLAIN, 50));
    pokemonName.setBounds(480, 80, 280, 60);
    pokedexFrame.getContentPane().add(pokemonName);

    ///////////// TypeLabel locations /////////////

    ///////////// Type1 /////////////
    JLabel lblType_1 = new JLabel(pokedexPoke.getTypeList(0));
    lblType_1.setHorizontalAlignment(SwingConstants.CENTER);
    if (pokedexPoke.getTypeList(1) == null) {
      lblType_1.setBounds(800, 80, 120, 60);
    } else {
      lblType_1.setBounds(800, 45, 120, 60);
    }
    int[] typeA = pokedexPoke.getTypeColor(pokedexPoke.getTypeList(0));
    lblType_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
    lblType_1.setBackground(new Color(typeA[0], typeA[1], typeA[2]));
    lblType_1.setForeground(Color.WHITE);
    lblType_1.setOpaque(true);
    lblType_1.setFont(new Font("Serif", Font.PLAIN, 30));
    pokedexFrame.getContentPane().add(lblType_1);

    ///////////// Type2 /////////////
    if (pokedexPoke.getTypeList(1) != null) {
      JLabel lblType_2 = new JLabel(pokedexPoke.getTypeList(1));
      lblType_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblType_2.setBounds(800, 115, 120, 60);
      int[] typeB = pokedexPoke.getTypeColor(pokedexPoke.getTypeList(1));
      lblType_2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
      lblType_2.setBackground(new Color(typeB[0], typeB[1], typeB[2]));
      lblType_2.setForeground(Color.WHITE);
      lblType_2.setOpaque(true);
      lblType_2.setFont(new Font("Serif", Font.PLAIN, 30));
      GridBagConstraints gbc_lblType_2 = new GridBagConstraints();
      gbc_lblType_2.insets = new Insets(0, 0, 5, 5);
      gbc_lblType_2.gridx = 5;
      gbc_lblType_2.gridy = 0;
      pokedexFrame.getContentPane().add(lblType_2, gbc_lblType_2);
    }

    ///////////////// ID# /////////////////
    JLabel labelID = new JLabel("#" + ID);
    labelID.setFont(new Font("Serif", Font.PLAIN, 30));
    labelID.setBounds(590, 47, 60, 26);
    pokedexFrame.getContentPane().add(labelID);

    ///////////////// FlavorTextLabel /////////////////
    JLabel lblFlavorText = new JLabel("Flavor Text");
    lblFlavorText.setFont(new Font("Serif", Font.PLAIN, 40));
    lblFlavorText.setBounds(50, 461, 169, 26);
    pokedexFrame.getContentPane().add(lblFlavorText);

    ///////////////// FlavorText /////////////////
    JLabel lblNewLabel = new JLabel(
        "This is where the flavor text should be, but there is a formatting issue.");
    lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
    lblNewLabel.setSize(934, 101);
    lblNewLabel.setLocation(85, 578);
    lblFlavorText.setFont(new Font("Serif", Font.PLAIN, 35));
    lblFlavorText.setBounds(60, 507, 802, 82);
    pokedexFrame.getContentPane().add(lblNewLabel);

    ///////////////// HP Label /////////////////
    JLabel lbl_HP = new JLabel("HP", SwingConstants.CENTER);
    lbl_HP.setForeground(Color.BLACK);
    lbl_HP.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_HP.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_HP.setBounds(197, 700, 100, 40);
    pokedexFrame.getContentPane().add(lbl_HP);

    JLabel lbl_HP_Min = new JLabel(Integer.toString(pokedexPoke.getMinStat(0)),
        SwingConstants.CENTER);
    lbl_HP_Min.setForeground(Color.RED);
    lbl_HP_Min.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_HP_Min.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_HP_Min.setBounds(197, 739, 100, 40);
    pokedexFrame.getContentPane().add(lbl_HP_Min);

    JLabel lbl_HP_Max = new JLabel(Integer.toString(pokedexPoke.getMaxStat(0)),
        SwingConstants.CENTER);
    lbl_HP_Max.setForeground(Color.BLUE);
    lbl_HP_Max.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_HP_Max.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_HP_Max.setBounds(197, 778, 100, 40);
    pokedexFrame.getContentPane().add(lbl_HP_Max);

    ///////////////// Att Label /////////////////
    JLabel lbl_Attack = new JLabel("Att", SwingConstants.CENTER);
    lbl_Attack.setForeground(Color.BLACK);
    lbl_Attack.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Attack.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Attack.setBounds(296, 700, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Attack);

    JLabel lbl_Attack_Min = new JLabel(
        Integer.toString(pokedexPoke.getMinStat(1)), SwingConstants.CENTER);
    lbl_Attack_Min.setForeground(Color.RED);
    lbl_Attack_Min.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Attack_Min.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Attack_Min.setBounds(296, 739, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Attack_Min);

    JLabel lbl_Attack_Max = new JLabel(
        Integer.toString(pokedexPoke.getMaxStat(1)), SwingConstants.CENTER);
    lbl_Attack_Max.setForeground(Color.BLUE);
    lbl_Attack_Max.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Attack_Max.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Attack_Max.setBounds(296, 778, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Attack_Max);

    ///////////////// Def Label /////////////////
    JLabel lbl_Defense = new JLabel("Def", SwingConstants.CENTER);
    lbl_Defense.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Defense.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Defense.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Defense.setBounds(395, 700, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Defense);

    JLabel lbl_Defense_Min = new JLabel(
        Integer.toString(pokedexPoke.getMinStat(2)), SwingConstants.CENTER);
    lbl_Defense_Min.setForeground(Color.RED);
    lbl_Defense_Min.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Defense_Min.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Defense_Min.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Defense_Min.setBounds(395, 739, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Defense_Min);

    JLabel lbl_Defense_Max = new JLabel(
        Integer.toString(pokedexPoke.getMaxStat(2)), SwingConstants.CENTER);
    lbl_Defense_Max.setForeground(Color.BLUE);
    lbl_Defense_Max.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Defense_Max.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Defense_Max.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Defense_Max.setBounds(395, 778, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Defense_Max);

    ///////////////// SpAtt Label /////////////////
    JLabel lbl_Special_Attack = new JLabel("SpAtt", SwingConstants.CENTER);
    lbl_Special_Attack.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Special_Attack.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Special_Attack.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Special_Attack.setBounds(494, 700, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Special_Attack);

    JLabel lbl_Special_Attack_Min = new JLabel(
        Integer.toString(pokedexPoke.getMinStat(3)), SwingConstants.CENTER);
    lbl_Special_Attack_Min.setForeground(Color.RED);
    lbl_Special_Attack_Min.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Special_Attack_Min.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Special_Attack_Min.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Special_Attack_Min.setBounds(494, 739, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Special_Attack_Min);

    JLabel lbl_Special_Attack_Max = new JLabel(
        Integer.toString(pokedexPoke.getMaxStat(3)), SwingConstants.CENTER);
    lbl_Special_Attack_Max.setForeground(Color.BLUE);
    lbl_Special_Attack_Max.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Special_Attack_Max.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Special_Attack_Max.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Special_Attack_Max.setBounds(494, 778, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Special_Attack_Max);

    ///////////////// SpDefLabel /////////////////
    JLabel lbl_SpecialDefense = new JLabel("SpDef", SwingConstants.CENTER);
    lbl_SpecialDefense.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_SpecialDefense.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_SpecialDefense.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_SpecialDefense.setBounds(593, 700, 100, 40);
    pokedexFrame.getContentPane().add(lbl_SpecialDefense);

    JLabel lbl_SpecialDefense_Min = new JLabel(
        Integer.toString(pokedexPoke.getMinStat(4)), SwingConstants.CENTER);
    lbl_SpecialDefense_Min.setForeground(Color.RED);
    lbl_SpecialDefense_Min.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_SpecialDefense_Min.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_SpecialDefense_Min.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_SpecialDefense_Min.setBounds(593, 739, 100, 40);
    pokedexFrame.getContentPane().add(lbl_SpecialDefense_Min);

    JLabel lbl_SpecialDefense_Max = new JLabel(
        Integer.toString(pokedexPoke.getMaxStat(4)), SwingConstants.CENTER);
    lbl_SpecialDefense_Max.setForeground(Color.BLUE);
    lbl_SpecialDefense_Max.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_SpecialDefense_Max.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_SpecialDefense_Max.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_SpecialDefense_Max.setBounds(593, 778, 100, 40);
    pokedexFrame.getContentPane().add(lbl_SpecialDefense_Max);

    ///////////////// Speed /////////////////
    JLabel lbl_Speed = new JLabel("Speed", SwingConstants.CENTER);
    lbl_Speed.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Speed.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Speed.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Speed.setBounds(692, 700, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Speed);

    JLabel lbl_Speed_Min = new JLabel(
        Integer.toString(pokedexPoke.getMinStat(5)), SwingConstants.CENTER);
    lbl_Speed_Min.setForeground(Color.RED);
    lbl_Speed_Min.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Speed_Min.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Speed_Min.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Speed_Min.setBounds(692, 739, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Speed_Min);

    JLabel lbl_Speed_Max = new JLabel(
        Integer.toString(pokedexPoke.getMaxStat(5)), SwingConstants.CENTER);
    lbl_Speed_Max.setForeground(Color.BLUE);
    lbl_Speed_Max.setFont(new Font("Serif", Font.PLAIN, 30));
    lbl_Speed_Max.setBounds(new Rectangle(0, 700, 150, 0));
    lbl_Speed_Max.setBorder(new LineBorder(new Color(0, 0, 0)));
    lbl_Speed_Max.setBounds(692, 778, 100, 40);
    pokedexFrame.getContentPane().add(lbl_Speed_Max);

    ///////////////// Min Label /////////////////
    JLabel lblMin = new JLabel("Min", SwingConstants.CENTER);
    lblMin.setForeground(Color.RED);
    lblMin.setFont(new Font("Serif", Font.PLAIN, 30));
    lblMin.setBorder(new LineBorder(new Color(0, 0, 0)));
    lblMin.setBounds(98, 739, 100, 40);
    pokedexFrame.getContentPane().add(lblMin);

    ///////////////// Max Label /////////////////
    JLabel lblMax = new JLabel("Max", SwingConstants.CENTER);
    lblMax.setForeground(Color.BLUE);
    lblMax.setFont(new Font("Serif", Font.PLAIN, 30));
    lblMax.setBorder(new LineBorder(new Color(0, 0, 0)));
    lblMax.setBounds(98, 778, 100, 40);
    pokedexFrame.getContentPane().add(lblMax);

    ///////////////// Stats Label /////////////////
    JLabel Stats = new JLabel("Stats", SwingConstants.CENTER);
    Stats.setForeground(Color.BLACK);
    Stats.setFont(new Font("Serif", Font.PLAIN, 30));
    Stats.setBorder(new LineBorder(new Color(0, 0, 0)));
    Stats.setBounds(98, 700, 100, 40);
    pokedexFrame.getContentPane().add(Stats);

    ///////////////// Previous Button /////////////////
    if (ID >= 2) {
      JButton previousButton = new JButton("Previous");
      previousButton.setFont(new Font("Serif", Font.PLAIN, 40));
      if (ID == 802) {
        previousButton.setBounds(580, 300, 200, 60);
      } else {
        previousButton.setBounds(510, 300, 200, 60);
      }
      previousButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          try {
            new PokedexWindow(ID-1);
            pokedexFrame.dispose();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
      pokedexFrame.getContentPane().add(previousButton);
    }
    

    ///////////////// Next Button /////////////////
    if (ID <= 801) {
      JButton nextButton = new JButton("Next");
      nextButton.setFont(new Font("Serif", Font.PLAIN, 40));
      if (ID == 1) {
        nextButton.setBounds(640, 300, 200, 60);
      } else {
        nextButton.setBounds(710, 300, 200, 60);
      }
      nextButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          try {
            new PokedexWindow(ID+1);
            pokedexFrame.dispose();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
      pokedexFrame.getContentPane().add(nextButton);
    }

    String[] nameArray = createPokemonNameArray();
    textField_Poke_ID = new JComboBox(nameArray);
    textField_Poke_ID.setFont(new Font("Serif", Font.PLAIN, 50));
    textField_Poke_ID.setBounds(580, 480, 280, 60);
    textField_Poke_ID.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          new PokedexWindow(textField_Poke_ID.getSelectedIndex()+1);
          pokedexFrame.dispose();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    pokedexFrame.getContentPane().add(textField_Poke_ID);
    
    
    pokedexFrame.setVisible(true);
  }
  public String[] createPokemonNameArray() throws Exception {
    String[] temporaryArray = new String[802];
    FileReader file = new FileReader("pokemon.csv");
    BufferedReader reader = new BufferedReader(file);
    reader.readLine();
    for (int i = 0; i < 802; i++) {
      String line = reader.readLine();
      String[] splitLine = line.split(",");
      String name = splitLine[1];
      temporaryArray[i] = (name.substring(0, 1).toUpperCase()
          + name.substring(1, name.length()));
    }
    reader.close();
    return temporaryArray;
  }
}
