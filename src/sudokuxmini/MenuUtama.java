package sudokuxmini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuUtama {
    
    public static JFrame menu;
    public static MenuUtama Menu;
    public static SudokuXMini Sudo;
    
    public MenuUtama() {
        // Membuat Frame Menu utama
        menu = new JFrame();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setTitle("Sudoku-X Mini");
        menu.setResizable(false);
        JPanel menupanel = new CustomPanel();
        menupanel.setLayout(null);
        
        // Membuat logo
        ImageIcon image = new ImageIcon("sudo.png");
        JLabel imagelabel = new JLabel(image);   
        imagelabel.setBounds(0, 6, 460, 100);
        ImageIcon image2 = new ImageIcon("mini.png");
        JLabel imagelabel2 = new JLabel(image2);   
        imagelabel2.setBounds(0, 100, 460, 100);
        
        // Tombol Play
        //ImageIcon play = new ImageIcon("play.png");
        JButton Play = new JButton("Play");
        Play.setBounds(185, 270, 100, 30);
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                int x = menu.getX();
                int y = menu.getY();
                Sudo.frame.setLocation(x, y);
                Sudo.frame.setVisible(true);
            }
        });
        
        // Tombol About Us
        //ImageIcon AboutUs = new ImageIcon("aboutus.png");
        JButton aboutus = new JButton("About Us");
        aboutus.setBounds(185, 360, 100, 30);
        
        menupanel.add(Play);
        menupanel.add(aboutus);
        menupanel.add(imagelabel);
        menupanel.add(imagelabel2);
        menu.add(menupanel);
        menu.pack();
        menu.setSize(470, 600);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
    
    public static void main(String[] args) {
        Sudo = new SudokuXMini();
        Menu = new MenuUtama();
    }
}
