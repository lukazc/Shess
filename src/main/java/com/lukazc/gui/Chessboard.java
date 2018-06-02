package com.lukazc.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chessboard extends JFrame {

    // Load piece images

        // Load black pieces

        public static ImageIcon blackRook = new ImageIcon("./././././assets/BR.gif");
        public static ImageIcon blackPawn = new ImageIcon("./././././assets/BP.gif");
        public static ImageIcon blackKing = new ImageIcon("./././././assets/BK.gif");
        public static ImageIcon blackQueen = new ImageIcon("./././././assets/BQ.gif");
        public static ImageIcon blackBishop = new ImageIcon("./././././assets/BB.gif");
        public static ImageIcon blackKnight = new ImageIcon("./././././assets/BN.gif");

        // Load white pieces

        public static ImageIcon whiteRook = new ImageIcon("./././././assets/WR.gif");
        public static ImageIcon whitePawn = new ImageIcon("./././././assets/WP.gif");
        public static ImageIcon whiteKing = new ImageIcon("./././././assets/WK.gif");
        public static ImageIcon whiteQueen = new ImageIcon("./././././assets/WQ.gif");
        public static ImageIcon whiteBishop = new ImageIcon("./././././assets/WB.gif");
        public static ImageIcon whiteKnight = new ImageIcon("./././././assets/WN.gif");

    //private int row = 0;
    //private int col = 0;

    // Make Container

    private static Container contents;

    // Squares

    public static JButton[][] squares = new JButton[8][8];

    // Color for squares

    private Color colorGray = Color.GRAY;

    /**
     * Constructor
     */
    public Chessboard() {

        // Initialize Layout

        contents = getContentPane();
        contents.setLayout(new GridLayout(8, 8));

        ButtonHandler buttonHandler = new ButtonHandler();

        // Create Chessboard (array[i][j])

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                // Create squares

                squares[i][j] = new JButton();

                // Odd squares paint black

                if ((i + j) % 2 != 0) {

                    squares[i][j].setBackground(colorGray);
                }

                // Add components to container

                contents.add(squares[i][j]);
                squares[i][j].addActionListener(buttonHandler);
            }
        }

        //squares[row][col].setIcon(rook);

        // Size and display

        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null); // Center window
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Window deleted from memory
    }

    private void processClick(int x, int y){

        //squares[row][col].setIcon(null);
        //squares[x][y].setIcon(rook);
        //row = x;
        //col = y;
    }


    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent click){
            Object source = click.getSource();

            for (int i = 0; i <8 ; i++) {
                for (int j = 0; j < 8; j++) {
                    if (source == squares[i][j]){
                        processClick(i,j);
                        return;
                    }
                }
            }
        }
    }
}