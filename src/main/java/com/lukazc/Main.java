package com.lukazc;

import com.lukazc.engine.game.*;
import com.lukazc.engine.pieces.*;
import com.lukazc.engine.player.*;
import com.lukazc.gui.Chessboard;

import java.util.Map;

public class Main {


    public static void main(String[] args) {

        Board board = new Board();
        new Chessboard();


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
