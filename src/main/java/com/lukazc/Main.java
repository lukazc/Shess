package com.lukazc;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.game.GameState;
import com.lukazc.engine.pieces.Piece;
import com.lukazc.engine.player.Player;
import com.lukazc.engine.player.Team;
import com.lukazc.gui.Chessboard;
import com.lukazc.gui.MainMenu;

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
        if (GameState.State == GameState.STATE.GAME) {
            new Chessboard();
            Board board = new Board();
            final Map boardState = board.boardState;

            Player whitePlayer = new Player();
            Player blackPlayer = new Player();
            board.currentPlayer = whitePlayer;

            board.setupPieces();
            printBoard(boardState);
        } else if (GameState.State == GameState.STATE.MENU){
            new MainMenu();
        }
    }

    private static void printBoard(Map boardState) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board.Coordinates coordinates = new Board.Coordinates(i, j);
                Piece piece = (Piece) boardState.get(coordinates);

                // Draw empty tiles on board

                if (piece == null) {
                    Chessboard.squares[i][j].setIcon(null);

                // Draw black pieces on board

                } else if (piece.getPieceType().isKing() && piece.getPieceTeam().equals(Team.BLACK))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.blackKing);
                } else if (piece.getPieceType().isQueen() && piece.getPieceTeam().equals(Team.BLACK))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.blackQueen);
                } else if (piece.getPieceType().isRook() && piece.getPieceTeam().equals(Team.BLACK))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.blackRook);
                } else if (piece.getPieceType().isKnight() && piece.getPieceTeam().equals(Team.BLACK))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.blackKnight);
                } else if (piece.getPieceType().isBishop() && piece.getPieceTeam().equals(Team.BLACK))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.blackBishop);
                } else if (piece.getPieceType().isPawn() && piece.getPieceTeam().equals(Team.BLACK))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.blackPawn);

                // Draw white pieces on board

                }else if (piece.getPieceType().isKing() && piece.getPieceTeam().equals(Team.WHITE))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteKing);
                } else if (piece.getPieceType().isQueen() && piece.getPieceTeam().equals(Team.WHITE))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteQueen);
                } else if (piece.getPieceType().isRook() && piece.getPieceTeam().equals(Team.WHITE))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteRook);
                } else if (piece.getPieceType().isKnight() && piece.getPieceTeam().equals(Team.WHITE))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteKnight);
                } else if (piece.getPieceType().isBishop() && piece.getPieceTeam().equals(Team.WHITE))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.whiteBishop);
                } else if (piece.getPieceType().isPawn() && piece.getPieceTeam().equals(Team.WHITE))
                {
                    Chessboard.squares[i][j].setIcon(Chessboard.whitePawn);
                }
            }
        }
    }
}