package com.lukazc;

import com.lukazc.engine.game.*;
import com.lukazc.engine.pieces.*;
import com.lukazc.engine.player.*;
import com.lukazc.gui.Chessboard;

import java.util.Map;

public class Main {


    public static void main(String[] args) {

        /*
         * PPP
         * Initialize and set up the board.
         *
         * LOOP:
         *
         * Check end-game conditions.
         * Calculate all legal moves for Black, including potential future access to the White King.
         * Take away legal moves from White based on Black's legal moves and potential future access to the king.
         * Let White player move, within the calculated legal moves.
         * White's move updates the board.
         *
         * Check end-game conditions.
         * Calculate all legal moves for White, including potential future access to the Black King.
         * Take away legal moves from Black based on Whites's legal moves and potential future access to the king.
         * Let Black player move, within the calculated legal moves.
         * Black's move updates the board.
         */

        /*
        * For now, we're just setting up the board and printing out the state to console.
        */
        new Chessboard();
        Board board = new Board();
        final Map boardState = board.boardState;

        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        board.currentPlayer = whitePlayer;

        board.setupPieces();
        printBoard(boardState);


    }

    private static void printBoard(Map boardState){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board.Coordinates coordinates = new Board.Coordinates(i,j);
                Piece piece = (Piece) boardState.get(coordinates);
                if (piece == null) {
                    System.out.print("-");
                }else {
                    String pieceLetter = piece.getPieceType().toString();
                    System.out.print(pieceLetter);
                }
            }
            System.out.println();
        }
    }
}
