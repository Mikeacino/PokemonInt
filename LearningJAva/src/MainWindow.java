import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {
  public MainWindow() {
    JFrame mainFrame = new JFrame();
    mainFrame.setSize(500, 500);
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
          StatCalcWindow basic = new StatCalcWindow();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    btnStatsCalc.setBounds(66, 21, 151, 47);
    panel_1.add(btnStatsCalc);

    JButton btnIvCalc = new JButton("IV");
    btnIvCalc.setFont(new Font("Serif", Font.PLAIN, 30));
    btnIvCalc.setBounds(262, 21, 146, 47);
    panel_1.add(btnIvCalc);

    JButton btnInfo = new JButton("Info");
    btnInfo.setFont(new Font("Serif", Font.PLAIN, 30));
    btnInfo.setBounds(160, 89, 167, 47);
    panel_1.add(btnInfo);

    mainFrame.setResizable(true);
    mainFrame.setVisible(true);
  }
}
