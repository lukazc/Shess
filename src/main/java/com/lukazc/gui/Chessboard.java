package com.lukazc.gui;

import com.lukazc.engine.game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chessboard extends JFrame{

    private Container contents;

    // Squares
    private JButton[] [] squares = new JButton[8][8];

    // Color for squares
    private Color colorGray = Color.GRAY;

    public Chessboard(){

        // Initialize Layout

        contents = getContentPane();
        contents.setLayout(new GridLayout(8,8));

        // Create Chessboard (array[i][j])
        for (int i = 0; i < 8; i++){

            for (int j = 0; j < 8; j++){

                // Create squares
                squares[i][j] = new JButton();
                // Odd squares paint black
                if((i + j) % 2 != 0){

                    squares[i][j].setBackground(colorGray);
                }

                // Add components to container
                contents.add(squares[i][j]);
            }
        }

        // Size and display
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null); // Center window
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Window deleted from memory
    }

    // Implement Board class to setup game
  //  public SetupBoard(){
       // Board.Coordinates.this.getX() = getWidth() / 8;
      //  Board.Coordinates.this.getY() = getHeight() / 8;
  //  }
}