import javax.swing.JFrame;
import java.awt.Font;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import java.awt.Color;

public class StatCalcWindow {
  private JTextField levelField;
  private JTextField textField_EV_HP;
  private JTextField textField_EV_Att;
  private JTextField textField_EV_Def;
  private JTextField textField_EV_SpAtt;
  private JTextField textField_EV_SpDef;
  private JTextField textField_EV_SpD;
  private JTextField textField_IV_HP;
  private JTextField textField_IV_Att;
  private JTextField textField_IV_Def;
  private JTextField textField_IV_SpAtt;
  private JTextField textField_IV_SpDef;
  private JTextField textField_IV_SpD;
  private JComboBox textField_Poke_ID;

  public StatCalcWindow() throws Exception {
    String[] natureNames = { "Hardy", "Bold", "Modest", "Calm", "Timid",
        "Lonely", "Docile", "Mild", "Gentle", "Hasty", "Adamant", "Impish",
        "Serious", "Careful", "Jolly", "Naughty", "Lax", "Rash", "Bashful",
        "Naive", "Brave", "Relaxed", "Quiet", "Sassy", "Quirky" };

    JFrame statCalcFrame = new JFrame();
    statCalcFrame.getContentPane().setMinimumSize(new Dimension(10, 10));
    statCalcFrame.setResizable(false);
    statCalcFrame.setLocationRelativeTo(null);
    statCalcFrame.setSize(1000, 900);
    statCalcFrame.getContentPane().setLayout(null);

    JLabel lblPokemon = new JLabel("Pokemon");
    lblPokemon.setBounds(50, 50, 255, 60);
    lblPokemon.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblPokemon);

    String[] nameArray = createPokemonNameArray();
    textField_Poke_ID = new JComboBox(nameArray);
    textField_Poke_ID.setFont(new Font("Serif", Font.PLAIN, 50));
    textField_Poke_ID.setBounds(326, 52, 200, 60);
    statCalcFrame.getContentPane().add(textField_Poke_ID);

    ///////////////// Level /////////////////
    JLabel lblLevel = new JLabel("Level");
    lblLevel.setBounds(550, 50, 255, 65);
    lblLevel.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblLevel);

    levelField = new JTextField();
    levelField.setBounds(691, 50, 200, 60);
    levelField.setText("100");
    levelField.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(levelField);
    levelField.setColumns(30);

    ///////////////// Nature /////////////////
    JLabel lblNature = new JLabel("Nature");
    lblNature.setBackground(Color.WHITE);
    lblNature.setBounds(50, 131, 255, 65);
    lblNature.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblNature);

    JComboBox<?> natureComboBox = new JComboBox(natureNames);
    natureComboBox.setBounds(326, 128, 200, 71);
    natureComboBox.setPreferredSize(new Dimension(30, 30));
    natureComboBox.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(natureComboBox);

    ///////////////// IV Title /////////////////
    JLabel lblIvs = new JLabel("IVs");
    lblIvs.setBounds(298, 214, 72, 65);
    lblIvs.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblIvs);

    ///////////////// EV Title /////////////////
    JLabel lblEvs = new JLabel("EVs");
    lblEvs.setBounds(449, 214, 86, 65);
    lblEvs.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblEvs);

    ///////////////// HP /////////////////
    JLabel lblHp = new JLabel("HP");
    lblHp.setBounds(139, 303, 126, 65);
    lblHp.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblHp);

    textField_IV_HP = new JTextField();
    textField_IV_HP.setBounds(300, 300, 70, 70);
    textField_IV_HP.setText("31");
    textField_IV_HP.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_IV_HP);
    textField_IV_HP.setColumns(10);

    textField_EV_HP = new JTextField();
    textField_EV_HP.setBounds(449, 300, 90, 70);
    textField_EV_HP.setText("4");
    textField_EV_HP.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_EV_HP);
    textField_EV_HP.setColumns(5);

    ///////////////// Attack /////////////////
    JLabel lblAttack = new JLabel("Att");
    lblAttack.setBounds(139, 383, 65, 65);
    lblAttack.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblAttack);

    textField_IV_Att = new JTextField();
    textField_IV_Att.setBounds(300, 380, 70, 70);
    textField_IV_Att.setText("31");
    textField_IV_Att.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_IV_Att);
    textField_IV_Att.setColumns(10);

    textField_EV_Att = new JTextField();
    textField_EV_Att.setBounds(449, 380, 90, 70);
    textField_EV_Att.setText("252");
    textField_EV_Att.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_EV_Att);
    textField_EV_Att.setColumns(10);

    ///////////////// Defense /////////////////
    JLabel lblDefense = new JLabel("Def");
    lblDefense.setBounds(139, 463, 73, 65);
    lblDefense.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblDefense);

    textField_IV_Def = new JTextField();
    textField_IV_Def.setBounds(300, 460, 70, 70);
    textField_IV_Def.setText("31");
    textField_IV_Def.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_IV_Def);
    textField_IV_Def.setColumns(10);

    textField_EV_Def = new JTextField();
    textField_EV_Def.setBounds(449, 460, 90, 70);
    textField_EV_Def.setText("0");
    textField_EV_Def.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_EV_Def);
    textField_EV_Def.setColumns(10);

    ///////////////// SpAttack /////////////////
    JLabel lblSpattack = new JLabel("SpAtt");
    lblSpattack.setBounds(139, 543, 118, 65);
    lblSpattack.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblSpattack);

    textField_IV_SpAtt = new JTextField();
    textField_IV_SpAtt.setBounds(300, 540, 70, 70);
    textField_IV_SpAtt.setText("31");
    textField_IV_SpAtt.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_IV_SpAtt);
    textField_IV_SpAtt.setColumns(10);

    textField_EV_SpAtt = new JTextField();
    textField_EV_SpAtt.setBounds(449, 540, 90, 70);
    textField_EV_SpAtt.setText("0");
    textField_EV_SpAtt.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_EV_SpAtt);
    textField_EV_SpAtt.setColumns(10);

    ///////////////// SpDefense /////////////////
    JLabel lblSpdef = new JLabel("SpDef");
    lblSpdef.setBounds(135, 623, 126, 65);
    lblSpdef.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblSpdef);

    textField_IV_SpDef = new JTextField();
    textField_IV_SpDef.setBounds(300, 620, 70, 70);
    textField_IV_SpDef.setText("31");
    textField_IV_SpDef.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_IV_SpDef);
    textField_IV_SpDef.setColumns(10);

    textField_EV_SpDef = new JTextField();
    textField_EV_SpDef.setBounds(449, 620, 90, 70);
    textField_EV_SpDef.setText("0");
    textField_EV_SpDef.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_EV_SpDef);
    textField_EV_SpDef.setColumns(10);

    ///////////////// Speed /////////////////
    JLabel lblSpeed = new JLabel("Speed");
    lblSpeed.setBounds(139, 703, 122, 65);
    lblSpeed.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(lblSpeed);

    textField_IV_SpD = new JTextField();
    textField_IV_SpD.setBounds(300, 700, 70, 70);
    textField_IV_SpD.setText("31");
    textField_IV_SpD.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_IV_SpD);
    textField_IV_SpD.setColumns(10);

    textField_EV_SpD = new JTextField();
    textField_EV_SpD.setBounds(449, 700, 90, 70);
    textField_EV_SpD.setText("252");
    textField_EV_SpD.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(textField_EV_SpD);
    textField_EV_SpD.setColumns(10);

    ///////////////// Calculate /////////////////
    JButton btnCalculate = new JButton("Calculate");
    btnCalculate.setBounds(659, 432, 217, 73);
    btnCalculate.setFont(new Font("Serif", Font.PLAIN, 50));
    statCalcFrame.getContentPane().add(btnCalculate);

    ///////////////// Shiny /////////////////
    JRadioButton rdbtnShiny = new JRadioButton("Shiny");
    rdbtnShiny.setFont(new Font("Serif", Font.PLAIN, 50));
    rdbtnShiny.setBounds(50, 216, 201, 60);
    statCalcFrame.getContentPane().add(rdbtnShiny);
    btnCalculate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          getWindowData(natureComboBox, rdbtnShiny);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    statCalcFrame.toFront();
    statCalcFrame.setVisible(true);
  }

  public void getWindowData(JComboBox<?> natureComboBox,
      AbstractButton rdbtnShiny) throws IOException {
    Pokemon poke = new Pokemon();
    poke.setPokemonID(textField_Poke_ID.getSelectedIndex()+1);
    poke.FilePokemonData(poke.getPokemonID());
    if (rdbtnShiny.isSelected()) {
      poke.setIsShiny(true);
    } else {
      poke.setIsShiny(false);
    }
    poke.setLevel(Integer.parseInt(levelField.getText()));
    poke.calculateNature((String) natureComboBox.getSelectedItem());
    poke.setEV(0, Integer.parseInt(textField_EV_HP.getText()));
    poke.setEV(1, Integer.parseInt(textField_EV_Att.getText()));
    poke.setEV(2, Integer.parseInt(textField_EV_Def.getText()));
    poke.setEV(3, Integer.parseInt(textField_EV_SpAtt.getText()));
    poke.setEV(4, Integer.parseInt(textField_EV_SpDef.getText()));
    poke.setEV(5, Integer.parseInt(textField_EV_SpD.getText()));
    poke.setIV(0, Integer.parseInt(textField_IV_HP.getText()));
    poke.setIV(1, Integer.parseInt(textField_IV_Att.getText()));
    poke.setIV(2, Integer.parseInt(textField_IV_Def.getText()));
    poke.setIV(3, Integer.parseInt(textField_IV_SpAtt.getText()));
    poke.setIV(4, Integer.parseInt(textField_IV_SpDef.getText()));
    poke.setIV(5, Integer.parseInt(textField_IV_SpD.getText()));
    poke.solveCurrentStats();
    PokemonWindow1 pokeWin = new PokemonWindow1(poke);
  }

  public String[] createPokemonNameArray() throws Exception {
    String[] temporaryArray = new String[802];
    FileReader file = new FileReader(
        "C:/Users/chris/OneDrive/Documents/Java 2017/Resources/pokemon.csv");
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
