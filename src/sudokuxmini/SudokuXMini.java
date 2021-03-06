package sudokuxmini;

import CLIPSJNI.Environment;
import CLIPSJNI.FactAddressValue;
import CLIPSJNI.MultifieldValue;
import CLIPSJNI.PrimitiveValue;
import java.awt.BorderLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class SudokuXMini {
    
    private JTextField text;
    public static JFrame frame;
    private static TableData TD;
    
    // Constructor
    public SudokuXMini(){
        
        /////////////////////////////////////
        // Membuat JFrame baru yang kosong //
        /////////////////////////////////////
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        //////////////////////////
        // Membuat Tabel Sudoku //
        //////////////////////////
        TD = new TableData();
        final Border Border1 = new ZoneBorder(null, null, Color.RED, null); // Bawah merah
        final Border Border2 = new ZoneBorder(null, Color.RED, Color.RED, null); // Bawah kanan merah
        final Border Border3 = new ZoneBorder(null, Color.RED, null, null); // Kanan merah
        final Border Border4 = new ZoneBorder(null, null, Color.RED, Color.RED); // BAwah kiri merah
        final Border Border5 = new ZoneBorder(null, null, null, null); // Warna dasar
        
        JTable table = new JTable(TD) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel label = (JLabel) super.prepareRenderer(renderer, row, column);
                if (row == column) {
                    label.setBackground(Color.YELLOW);
                } else if (row + column == 5) {
                    label.setBackground(Color.PINK);
                } else {
                    label.setBackground(Color.WHITE);
                }
                if (((row == 0) && (column == 2)) || ((row == 2) && (column == 2)) || ((row == 4) && (column == 2)) || ((row == 5) && (column == 2)))
                {
                    label.setBorder(Border3);
                }
                else if(((row == 1) && (column == 0)) || ((row == 1) && (column == 1)) || ((row == 1) && (column == 3)) || ((row == 1) && (column == 4)) || ((row == 1) && (column == 5)) ||
                        ((row == 3) && (column == 0)) || ((row == 3) && (column == 1)) || ((row == 3) && (column == 3)) || ((row == 3) && (column == 4)) || ((row == 3) && (column == 5)))
                {
                    label.setBorder(Border1);
                }
                else if(((row == 1) && (column == 2)) || ((row == 3) && (column == 2)))
                {
                    label.setBorder(Border2);
                }
                else
                {
                    label.setBorder(Border5);
                }
                return label;
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
        table.setTableHeader(null);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        //////////////////////////////////////////////////
        // Menentukan ukuran kotak dan font di dalamnya //
        //////////////////////////////////////////////////
        for(int i=0;i<=5;i++)
        {
            table.getColumnModel().getColumn(i).setPreferredWidth(75);
            table.setRowHeight(75);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Monotype Corsiva", Font.BOLD, 70));
        for(int i=0;i<=5;i++)
        {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        ////////////////////////////////////////////////////////////
        // Membuat tombol Browse & Solve & TextField untuk browse //
        ////////////////////////////////////////////////////////////
        JPanel panel = new CustomPanel();
        panel.setLayout(null);
        JButton browse = new JButton("Browse");
        text = new JTextField();
        text.setEditable(false);
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                int rVal = fileChooser.showOpenDialog(null);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                text.setText(fileChooser.getSelectedFile().toString());

                //////////////////////
                // Membaca isi file //
                //////////////////////
                BufferedReader reader;
                String line;
                Vector<String> angka = new Vector<String>();
                try{
                        reader = new BufferedReader(new FileReader(text.getText()));
                        while ((line = reader.readLine()) != null) 
                        {
                            angka.add(line);
                        }
                }
                catch(Exception E){}
                
                /////////////////////////////////////////
                // Menuliskan isi file ke tabel sudoku //
                /////////////////////////////////////////
                TD.RefreshTable(angka);
                }
            }
        });
        JButton solve = new JButton("Solve");
        solve.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ///////////////////////////////////////////////////
                // Menggabungkan Java & CLIPS untuk solve Sudoku //
                ///////////////////////////////////////////////////
                Environment clips = new Environment();
                
                String inputfile = text.getText();
                if(!inputfile.equalsIgnoreCase(""))
                {
                    TXTConverter convert = new TXTConverter(inputfile);

                    String evalStr = "(clear)";
                    clips.eval(evalStr);
                    evalStr = "(unwatch all)";
                    clips.eval(evalStr);
                    evalStr = "(load \"CLIPS//sudoku.clp\")";
                    clips.eval(evalStr);
                    evalStr = "(load \"CLIPS//solve.clp\")";
                    clips.eval(evalStr);
                    evalStr = "(load \"CLIPS//output-frills.clp\")";
                    clips.eval(evalStr);
                    evalStr = "(load \"CLIPS//puzzles//out.clp\")";
                    clips.eval(evalStr);
                    evalStr = "(reset)";
                    clips.eval(evalStr);
                    evalStr = "(run)";
                    clips.eval(evalStr);
                    evalStr = "?*x*";
                    PrimitiveValue rv = clips.eval(evalStr);
                    String hasil = rv.toString();

                    //////////////////////////////////////
                    // Mencari solusi dari String hasil //
                    //////////////////////////////////////
                    int x = hasil.lastIndexOf("is:    ") + 7;
                    Vector<String> solusi = new Vector<String>();

                    int inc = 0;
                    while(inc<6)
                    {
                        String tes = "";
                        int inc2 = 0;
                        while(inc2<6)
                        {
                            if(hasil.charAt(x)!=' ')
                            {
                                tes = tes + hasil.charAt(x) + "";
                                inc2++;
                            }
                            x++;
                        }
                        solusi.add(tes);
                        inc++;
                    }
                    TD.RefreshTable(solusi);
                }
            }
        });
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Vector<String> kosong = new Vector<String>();
                for(int i=0;i<6;i++)
                {
                    kosong.add("******");
                }
                TD.RefreshTable(kosong);
                text.setText("");
            }
        });
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                int x = frame.getX();
                int y = frame.getY();
                MenuUtama.menu.setLocation(x, y);
                MenuUtama.menu.setVisible(true);
                Vector<String> kosong = new Vector<String>();
                for(int i=0;i<6;i++)
                {
                    kosong.add("******");
                }
                TD.RefreshTable(kosong);
                text.setText("");
            }
        });
        
        ///////////////////////////////////////////
        // Menentukan ukuran komponen - komponen //
        ///////////////////////////////////////////
        text.setBackground(Color.WHITE);
        table.setBounds(6, 6, 450, 450);
        browse.setBounds(6, 475, 100, 30);
        solve.setBounds(6, 515, 100, 30);
        clear.setBounds(180, 515, 100, 30);
        back.setBounds(355, 515, 100, 30);
        text.setBounds(112, 475, 343, 30);
        
        //////////////////////////////////////////////
        // Memasukkan semua komponen ke dalam Frame //
        //////////////////////////////////////////////
        panel.add(table);
        panel.add(browse);
        panel.add(solve);
        panel.add(text);
        panel.add(clear);
        panel.add(back);
        frame.add(panel);
        
        frame.pack();
        frame.setTitle("Sudoku-X Mini");
        frame.setSize(470, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }
}
