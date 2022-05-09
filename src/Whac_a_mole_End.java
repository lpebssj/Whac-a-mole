import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Whac_a_mole_End extends JFrame implements ActionListener {
    //variables
    JPanel panel;
    JLabel timeUp, level, score;
    JButton retry, menu;
    private final Font f1 = FontUtil.setFont("visitor1", Font.BOLD, 40);
    private final Font f2 = FontUtil.setFont("visitor1", Font.PLAIN, 20);
    private final Font f3 = FontUtil.setFont("visitor1", Font.PLAIN, 10);

    public Whac_a_mole_End(String lvl, int sc) {
        setLayout(null);
        setBounds(0, 0, 300, 320);

        panel = new JPanel(new BorderLayout());
        panel.setBounds(0, 20, 290, 120);
        timeUp = new JLabel("Time's Up!", JLabel.CENTER);
        timeUp.setFont(f1);
        //显示level
        level = new JLabel("Level: " + lvl, JLabel.CENTER);
        level.setFont(f2);
        //显示score
        score = new JLabel("Score: " + sc, JLabel.CENTER);
        score.setFont(f2);
        panel.add(timeUp, BorderLayout.NORTH);
        panel.add(level, BorderLayout.CENTER);
        panel.add(score, BorderLayout.SOUTH);

        //retry button
        retry = new JButton();
        retry.addActionListener(this);
        retry.setFont(f3);
        retry.setIcon(new ImageIcon(getClass().getResource("retry.png")));
        retry.setUI(new BasicButtonUI());
        retry.setMargin(new Insets(0,0,0,0));
        retry.setBounds(30, 200, 80, 50);

        //menu button
        menu = new JButton();
        menu.addActionListener(this);
        menu.setFont(f3);
        menu.setIcon(new ImageIcon(getClass().getResource("menu.png")));
        menu.setUI(new BasicButtonUI());//设置UI
        menu.setMargin(new Insets(0,0,0,0));//设置边距
        menu.setBounds(180, 200, 80, 50);

        this.add(panel);
        this.add(retry);
        this.add(menu);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(retry)) {
            Whac_a_mole_Start.whacAMole.dispose();
            Whac_a_mole_Start.whacAMole = new Whac_a_mole(Whac_a_mole.delay);
        } else if (e.getSource().equals(menu)) {
            Whac_a_mole_Start.whacAMole.dispose();
            new Whac_a_mole_Start();
        }
        this.dispose();
    }

}
