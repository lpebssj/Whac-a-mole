
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * Whac a mole game
 *
 * @author Beverly Sai
 * @date 2020-06-08
 */
public class Whac_a_mole extends JFrame implements ActionListener, MouseListener {
    //variables
    Random random = new Random();
    JPanel content;
    public JLabel mole = new JLabel(), level, timeLeft, molesEsc, score;
    public String lvl;
    Font f1 = FontUtil.setFont("visitor1", Font.BOLD, 15);
    Timer delayTimer,timer;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Point hotspot = new Point(0, 0);
    Cursor cu;
    List<ImageIcon> moleList = new ArrayList<>(), moledList = new ArrayList<>();
    int[] xList = {68, 161, 254, 50, 161, 270, 34, 161, 282};     //x coordinates (the left-most) for 9 moles
    int[] yList = {62, 62, 62, 127, 127, 127, 192, 192, 192};     //y coordinates (approx the top of their head) for 9 moles
    public int showNum = 0, hitNum = 0, missNum = 0, sc = 0;
    static int x, y, ran, delay;
    double time = 30;
    List<Integer> randomList = new ArrayList<>();
    boolean flag;

    public Whac_a_mole(int delay) {
        super("Whac_a_mole");
        Whac_a_mole.delay = delay;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(402, 428);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);
        content = (JPanel) this.getContentPane();
        content.setOpaque(false);   //make transparent pixels invisible

        content.add(mole);

        //set background
        //reference: https://blog.csdn.net/weixin_42247720/article/details/80866409
        ImageIcon bgImage = new ImageIcon(getClass().getResource("bgbar.png"));
        JLabel bgLabel = new JLabel(bgImage);
        bgLabel.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
        this.getLayeredPane().add(bgLabel, Integer.valueOf(Integer.MIN_VALUE));

        //change cursor
        cu = tk.createCustomCursor(tk.getImage(getClass().getResource("hammer.png")), hotspot, "hammer");
        this.setCursor(cu);

        //images for 9 moles
        ImageIcon mole1 = new ImageIcon(getClass().getResource("1.png"));
        ImageIcon mole2 = new ImageIcon(getClass().getResource("2.png"));
        ImageIcon mole3 = new ImageIcon(getClass().getResource("3.png"));
        ImageIcon mole4 = new ImageIcon(getClass().getResource("4.png"));
        ImageIcon mole5 = new ImageIcon(getClass().getResource("5.png"));
        ImageIcon mole6 = new ImageIcon(getClass().getResource("6.png"));
        ImageIcon mole7 = new ImageIcon(getClass().getResource("7.png"));
        ImageIcon mole8 = new ImageIcon(getClass().getResource("8.png"));
        ImageIcon mole9 = new ImageIcon(getClass().getResource("9.png"));
        Collections.addAll(moleList, mole1, mole2, mole3, mole4, mole5, mole6, mole7, mole8, mole9);

        //images for 9 moles (beaten)
        ImageIcon moled1 = new ImageIcon(getClass().getResource("1d.png"));
        ImageIcon moled2 = new ImageIcon(getClass().getResource("2d.png"));
        ImageIcon moled3 = new ImageIcon(getClass().getResource("3d.png"));
        ImageIcon moled4 = new ImageIcon(getClass().getResource("4d.png"));
        ImageIcon moled5 = new ImageIcon(getClass().getResource("5d.png"));
        ImageIcon moled6 = new ImageIcon(getClass().getResource("6d.png"));
        ImageIcon moled7 = new ImageIcon(getClass().getResource("7d.png"));
        ImageIcon moled8 = new ImageIcon(getClass().getResource("8d.png"));
        ImageIcon moled9 = new ImageIcon(getClass().getResource("9d.png"));
        Collections.addAll(moledList, moled1, moled2, moled3, moled4, moled5, moled6, moled7, moled8, moled9);

        showInfo(delay);

        this.addMouseListener(this);
        this.setVisible(true);
        this.setResizable(false);

        int moleShowTimes = (int) (time*1000/delay);
        while(moleShowTimes>0){
            randomList.add(random.nextInt(9));
            moleShowTimes--;
        }

        //start timing
        delayTimer = new Timer(delay, this);
        delayTimer.start();
        timer = new Timer(1000,this);
        timer.start();
    }

    /**
     * Shows information (level, time left, mole escaped, score) in the game interface
     */
    private void showInfo(int delay) {
        level = new JLabel();
        level.setFont(f1);
        level.setForeground(new Color(179, 202, 178));
        level.setBounds(50, 12, 329, 47);
        if (delay == 1200) {
            level.setText("Level: EASY");
            lvl = "EASY";
        } else if (delay == 550) {
            level.setText("level: HARD");
            lvl = "HARD";
        }

        timeLeft = new JLabel("Time Left: " + (int) time);
        timeLeft.setFont(f1);
        timeLeft.setForeground(new Color(179, 202, 178));
        timeLeft.setBounds(50, 250, 329, 109);

        missNum = showNum - hitNum;
        molesEsc = new JLabel("Moles Escaped: " + missNum);
        molesEsc.setFont(f1);
        molesEsc.setForeground(new Color(179, 202, 178));
        molesEsc.setBounds(50, 270, 329, 109);

        score = new JLabel("Score: " + sc);
        score.setFont(f1);
        score.setForeground(new Color(179, 202, 178));
        score.setBounds(50, 290, 329, 109);

        this.add(timeLeft);
        this.add(level);
        this.add(molesEsc);
        this.add(score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==delayTimer){
            missNum = showNum - hitNum;
            molesEsc.setText("Moles Escaped: " + missNum);

            //make one random mole appear
            ran = randomList.get(showNum);
            mole.setIcon(moleList.get(ran));
            flag = true;
            x = xList[ran];     //store coordinate info of currently appearing mole in temp x and y variables
            y = yList[ran];
            mole.setBounds(0, 0, 400, 400);
            mole.setVisible(true);
            showNum++;

        } else if (e.getSource()==timer){
            //calculate time
            int timeInt = (int) --time;
            timeLeft.setText("Time Left: " + timeInt);
            //when time's up
            if (time <= 0) {
                this.removeMouseListener(this);
                delayTimer.stop();
                timer.stop();
                new Whac_a_mole_End(lvl, sc);     //won't close the game window because that's so rude
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //change cursor to simulate a hit
        cu = tk.createCustomCursor(tk.getImage(getClass().getResource("hammerp.png")), hotspot, "hammerPressed");
        this.setCursor(cu);

        //determine whether clicked within the area of currently appearing mole
        Point location = e.getPoint();
        if (location.x > x && location.x < x + 81 && location.y > y && location.y < y + 65 && flag == true) {
            flag = false;
            mole.setIcon(moledList.get(ran));
            hitNum++;
            sc = sc + 100;
            score.setText("Score: " + sc);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //change the cursor back to the original
        cu = tk.createCustomCursor(tk.getImage(getClass().getResource("hammer.png")), hotspot, "hammer");
        this.setCursor(cu);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
