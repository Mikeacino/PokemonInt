import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates the first window that links to the Pokedex and the Stat Calculator.
 * 
 * @author Michael Carracino
 */

public class MainWindow {
  /**
   * Creates the main window along with buttons and tools.
   */
  public void initializeWindow() {
    JFrame mainFrame = new JFrame();
    mainFrame.setSize(400, 600);
    mainFrame.setResizable(false);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setBorder(null);
    mainFrame.getContentPane().add(panel, BorderLayout.NORTH);
    JLabel lblPokemonProject = new JLabel("Pokelator");
    lblPokemonProject.setFont(new Font("Serif", Font.PLAIN, 50));
    panel.add(lblPokemonProject);

    JPanel panelOne = new JPanel();
    panelOne.setBorder(null);
    mainFrame.getContentPane().add(panelOne, BorderLayout.CENTER);
    panelOne.setLayout(null);

    JButton statsCalcBtn = new JButton("Stats");
    statsCalcBtn.setFont(new Font("Serif", Font.PLAIN, 30));
    statsCalcBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          StatCalcWindow win = new StatCalcWindow();
          win.buildWindow();
        } catch (Exception e) {
          System.out.println(e);
          ErrorWindow error = new ErrorWindow();
          error.buildWindow(e);
        }
      }
    });
    statsCalcBtn.setBounds(100, 190, 200, 60);
    panelOne.add(statsCalcBtn);

    JButton pokedexBtn = new JButton("Pokedex");
    pokedexBtn.setFont(new Font("Serif", Font.PLAIN, 30));
    pokedexBtn.setBounds(100, 70, 200, 60);
    panelOne.add(pokedexBtn);

    JButton infoButton = new JButton("Information");
    infoButton.setEnabled(false);
    infoButton.setFont(new Font("Serif", Font.PLAIN, 30));
    infoButton.setBounds(100, 310, 200, 60);
    panelOne.add(infoButton);
    pokedexBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          PokedexWindow infoWindow = new PokedexWindow();
          infoWindow.randomWindow();
        } catch (Exception e) {
          ErrorWindow error = new ErrorWindow();
          error.buildWindow(e);
        }
      }
    });

    mainFrame.setResizable(false);
    mainFrame.setVisible(true);

  }
}
