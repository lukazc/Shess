package com.lukazc.gui;

import com.lukazc.engine.game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chessboard extends JFrame {

    private int row = 0;
    private int col = 0;

    private ImageIcon rook = new ImageIcon("./././././assets/BR.gif");

    private Container contents;

    // Squares
    private JButton[][] squares = new JButton[8][8];

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
        squares[row][col].setIcon(rook);

        // Size and display
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null); // Center window
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Window deleted from memory
    }

    private void processClick(int x, int y){

        squares[row][col].setIcon(null);
        squares[x][y].setIcon(rook);
        row = x;
        col = y;
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

    // Implement Board class to setup game
    //  public SetupBoard(){
    // Board.Coordinates.this.getX() = getWidth() / 8;
    //  Board.Coordinates.this.getY() = getHeight() / 8;
    //  }

}