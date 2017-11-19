import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {
  public MainWindow() {
    JFrame mainFrame = new JFrame("Main Window");
    mainFrame.setSize(500, 300);
    mainFrame.setResizable(false);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    mainFrame.getContentPane().add(panel, BorderLayout.NORTH);

    JLabel lblPokemonProject = new JLabel("Pokemon Project");
    lblPokemonProject.setFont(new Font("Serif", Font.PLAIN, 50));
    panel.add(lblPokemonProject);

    JPanel panel_1 = new JPanel();
    mainFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
    panel_1.setLayout(null);

    JButton btnStatsCalc = new JButton("Stats");
    btnStatsCalc.setFont(new Font("Serif", Font.PLAIN, 30));
    btnStatsCalc.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          @SuppressWarnings("unused")
          StatCalcWindow basic = new StatCalcWindow();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    btnStatsCalc.setBounds(175, 21, 150, 47);
    panel_1.add(btnStatsCalc);

    JButton btnInfo = new JButton("Pokedex");
    btnInfo.setFont(new Font("Serif", Font.PLAIN, 30));
    btnInfo.setBounds(175, 89, 150, 47);
    panel_1.add(btnInfo);
    btnInfo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          @SuppressWarnings("unused")
          PokedexWindow infoWindow = new PokedexWindow();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    mainFrame.setResizable(false);
    mainFrame.setVisible(true);
  }
}
