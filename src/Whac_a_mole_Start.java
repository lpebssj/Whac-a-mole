import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Whac_a_mole_Start extends JFrame implements ActionListener {
    //variables
    JLabel label1;
    JLabel label2;
    JPanel panel1;
    JPanel panel2;
    JButton easy;
    JButton hard;

    static Whac_a_mole whacAMole;

    public Whac_a_mole_Start() {
        setLayout(new GridLayout(4, 1));
        setBounds(100, 100, 400, 400);
        label1 = new JLabel("Whac-a-mole", JLabel.CENTER);
        label1.setFont(FontUtil.setFont("visitor1", Font.BOLD, 50));

        label2 = new JLabel("Select difficulty:", JLabel.CENTER);
        label2.setFont(FontUtil.setFont("visitor1", Font.PLAIN,30));

        //easy button
        panel1 = new JPanel();
        Dimension buttonSize = new Dimension(110, 50);
        easy = new JButton();
        easy.setSize(buttonSize);
        easy.setIcon(new ImageIcon(getClass().getResource("easy.png")));//get image, reference: https://stackoverflow.com/questions/19414453/how-to-get-resources-directory-path-programmatically
        easy.setUI(new BasicButtonUI());//set UI, reference: https://www.codota.com/code/java/methods/javax.swing.JButton/setUI
        easy.setMargin(new Insets(0,0,0,0));
        easy.addActionListener(this);
        panel1.add(easy);

        //hard button
        panel2 = new JPanel();
        hard = new JButton();
        hard.setSize(buttonSize);
        hard.setIcon(new ImageIcon(getClass().getResource("hard.png")));
        hard.setUI(new BasicButtonUI());
        hard.setMargin(new Insets(0,0,0,0));
        hard.addActionListener(this);
        panel2.add(hard);

        this.add(label1);
        this.add(label2);
        this.add(panel1);
        this.add(panel2);
        setLocationRelativeTo(null);// center the window
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);//set not resizable
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(easy)) {
            whacAMole = new Whac_a_mole(1200);
        } else if (e.getSource().equals(hard)){
            whacAMole = new Whac_a_mole(550);
        }
        this.dispose();
    }

    public static void main(String[] args) {
        new Whac_a_mole_Start();
    }
}
