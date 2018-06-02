package com.lukazc.gui;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.game.Move;
import com.lukazc.engine.pieces.Piece;
import com.lukazc.engine.player.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chessboard extends JFrame {

    // Load piece images

        // Load black pieces

        private static ImageIcon blackRook = new ImageIcon("./././././assets/BR.gif");
        private static ImageIcon blackPawn = new ImageIcon("./././././assets/BP.gif");
        private static ImageIcon blackKing = new ImageIcon("./././././assets/BK.gif");
        private static ImageIcon blackQueen = new ImageIcon("./././././assets/BQ.gif");
        private static ImageIcon blackBishop = new ImageIcon("./././././assets/BB.gif");
        private static ImageIcon blackKnight = new ImageIcon("./././././assets/BN.gif");

        // Load white pieces

        private static ImageIcon whiteRook = new ImageIcon("./././././assets/WR.gif");
        private static ImageIcon whitePawn = new ImageIcon("./././././assets/WP.gif");
        private static ImageIcon whiteKing = new ImageIcon("./././././assets/WK.gif");
        private static ImageIcon whiteQueen = new ImageIcon("./././././assets/WQ.gif");
        private static ImageIcon whiteBishop = new ImageIcon("./././././assets/WB.gif");
        private static ImageIcon whiteKnight = new ImageIcon("./././././assets/WN.gif");

    // Make Container

    private static Container contents;

    // Squares

    private static JButton[][] squares = new JButton[8][8];

    // Color for squares

    private Color colorGray = Color.GRAY;
    private Move move;

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
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Window deleted from memory

    }

    public void newMove(Move move) {
        this.move = move;
    }

    private void processClick(int x, int y){

        move.select(new Board.Coordinates(x,y));
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

    public void drawPiece(Board.Coordinates coordinates, Piece piece){
        final int i = coordinates.getX();
        final int j = coordinates.getY();

        // For given tile on the board:
        if (piece == null) {
            // If there's no piece, remove icon.
            Chessboard.squares[i][j].setIcon(null);
        } else if (piece.getPieceTeam().equals(Team.BLACK)) {
            // If the piece is Black, draw black icon of its type.
            switch (piece.getPieceType()) {
                case BISHOP:
                    Chessboard.squares[i][j].setIcon(Chessboard.blackBishop);
                    break;
                case KNIGHT:
                    Chessboard.squares[i][j].setIcon(Chessboard.blackKnight);
                    break;
                case PAWN:
                    Chessboard.squares[i][j].setIcon(Chessboard.blackPawn);
                    break;
                case ROOK:
                    Chessboard.squares[i][j].setIcon(Chessboard.blackRook);
                    break;
                case KING:
                    Chessboard.squares[i][j].setIcon(Chessboard.blackKing);
                    break;
                case QUEEN:
                    Chessboard.squares[i][j].setIcon(Chessboard.blackQueen);
            }
        } else if (piece.getPieceTeam().equals(Team.WHITE)) {
            // If the piece is White, draw white icon of its type.
            switch (piece.getPieceType()) {
                case BISHOP:
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteBishop);
                    break;
                case KNIGHT:
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteKnight);
                    break;
                case PAWN:
                    Chessboard.squares[i][j].setIcon(Chessboard.whitePawn);
                    break;
                case ROOK:
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteRook);
                    break;
                case KING:
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteKing);
                    break;
                case QUEEN:
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteQueen);
            }
        }
    }
}