package com.lukazc;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.game.Move;
import com.lukazc.engine.pieces.Piece;
import com.lukazc.gui.Chessboard;
import com.lukazc.gui.MainMenu;
import com.lukazc.gui.Splash;

import java.util.Map;

public class Main {

    private static Board board;
    public static Chessboard chessboard;
    public static MainMenu mainMenu;
    public static Splash splash;

    public static void main(String[] args) {

        /*
         * Calculate all legal moves for Black, including potential future access to the White King.
         * Take away legal moves from White based on Black's legal moves and potential future access to the king.
         * Check end-game conditions.
         * Let White player move, within the calculated legal moves.
         * White's move updates the board.
         *
         * Calculate all legal moves for White, including potential future access to the Black King.
         * Take away legal moves from Black based on Whites's legal moves and potential future access to the king.
         * Check end-game conditions.
         * Let Black player move, within the calculated legal moves.
         * Black's move updates the board.
         */

        // Load main menu

        mainMenu = new MainMenu();

        splash = new Splash();


        // Initialize board

        board = new Board();

        // Initialize Move class, set it to GUI

        Move move = new Move(board);

        // Load chessboard

        chessboard = new Chessboard(move);

        // Reference state of the board

        Map boardState = board.getBoardState();

        // Setup pieces and show board

        board.setupPieces();
        drawAllPieces(boardState);
    }


    public static void drawAllPieces(Map boardState) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board.Coordinates coordinates = new Board.Coordinates(i, j);
                Piece piece = (Piece) boardState.get(coordinates);

                chessboard.drawPiece(coordinates, piece);
            }
        }
    }
}