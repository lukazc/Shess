package com.lukazc;

import com.lukazc.engine.game.*;
import com.lukazc.engine.pieces.*;
import com.lukazc.engine.player.*;

import java.util.Map;

public class Main {


    public static void main(String[] args) {

        Board board = new Board();
        final Map boardState = board.boardState;

        Player whitePlayer = new Player();
        Player blackPlayer = new Player();
        board.currentPlayer = whitePlayer;

        board.initializeBoard();
        printBoard(boardState);

    }

    private static void printBoard(Map boardState){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board.Coordinates tile = new Board.Coordinates(i,j);
                Piece piece = (Piece) boardState.get(tile);
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
