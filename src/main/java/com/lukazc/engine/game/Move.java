package com.lukazc.engine.game;

import com.lukazc.engine.pieces.Piece;
import com.lukazc.engine.player.Team;
import com.lukazc.gui.Chessboard;


public class Move {
    //    private Player currentPlayer;
    private Piece piece;
    private Board.Coordinates origin;
    private Board.Coordinates destination;
    private final Board board;

    private boolean whiteTurn;

    public Move(Board board) {
        this.board = board;
        whiteTurn = true;
        // Calculate legal moves for every piece.
        for (Piece piece : board.getBoardState().values()) {
            piece.calculateLegalMoves(board);
        }
    }

    public void selectTile(Board.Coordinates coordinates) {
        // Select a Piece.
        if (piece == null) {
            // If it matches current player's color.
            selectPiece(coordinates);
        }

        // Once a piece has been selected:
        if (piece != null) {
            if (origin == null) {
                // Set its coordinates as "origin".
                setOrigin(coordinates);
            } else {
                // Second click selects the "destination".
                if (setDestination(coordinates)) {
                    board.updateBoardState(this);
                    /* TODO: Pawn Promotion
                     When a pawn reaches the final rank, it's substituted for a Queen. */
                    piece.trackFirstMove();
                    piece.setPiecePositionTracker(coordinates);
                    startNextTurn();
                }
            }
        }
    }

    // Reset all Move information, and switch player.
    private void startNextTurn() {
        // Calculate legal moves for each piece first.
        for (Piece piece : board.getBoardState().values()) {
            if (piece != null) piece.calculateLegalMoves(board);
        }

        restartMove();

        // Switch player.
        whiteTurn = !whiteTurn;
    }

    // Reset all selections, and remove legal moves indicators.
    private void restartMove() {
        piece = null;
        origin = null;
        destination = null;
        Chessboard.hideLegalMoves();
    }

    // If the Piece is one of the player's, and it can move, select it.
    private void selectPiece(Board.Coordinates coordinates) {
        Piece selection = board.getBoardState().get(coordinates);

        // If tile is empty, or the chosen piece can't move, do nothing.
        if (selection == null || selection.legalMoves.isEmpty()) return;

        // Select the piece if it's owned by current player.
        // Show tiles it can move to.
        if (whiteTurn && selection.getPieceTeam() == Team.WHITE
                || !whiteTurn && selection.getPieceTeam() == Team.BLACK) {
            piece = selection;
            Chessboard.showLegalMoves(piece.legalMoves);
        }


    }

    private void setOrigin(Board.Coordinates coordinates) {
        if (piece != null) origin = coordinates;
    }

    /**
     * Update board if the chosen move destination is legal, and return "true".
     * Otherwise, if there's not any legal moves, or the destination is illegal,
     * reset to the beginning of the move and return "false".
     */
    private boolean setDestination(Board.Coordinates destination) {
        if (piece.legalMoves.contains(destination)) {
            this.destination = destination;
            return true;
        } else {
            restartMove();
            return false;
        }
    }


    public Piece getPiece() {
        return piece;
    }

    Board.Coordinates getOrigin() {
        return origin;
    }

    Board.Coordinates getDestination() {
        return destination;
    }
}
