package com.lukazc.engine.game;

import com.lukazc.engine.pieces.Piece;
import com.lukazc.engine.player.Team;


public class Move {
    //    private Player currentPlayer;
    private Piece piece;
    private Board.Coordinates origin;
    private Board.Coordinates destination;
    private final Board board;

    private boolean whiteTurn = true;

    public Move(Board board) {
//        this.currentPlayer = player;
        this.board = board;
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
        restartMove();
        whiteTurn = !whiteTurn;
    }

    private void restartMove() {
        piece = null;
        origin = null;
        destination = null;
    }

    // If the selected Piece is one of the player's pieces, prepare it to move.
    // TODO: check if there's legal moves before even selecting a piece
    private void selectPiece(Board.Coordinates coordinates) {
        Piece selection = board.getBoardState().get(coordinates);
        if (selection == null) return;
        if (whiteTurn && selection.getPieceTeam() == Team.WHITE) piece = selection;
        if (!whiteTurn && selection.getPieceTeam() == Team.BLACK) piece = selection;
    }

    private void setOrigin(Board.Coordinates coordinates) {
        if (piece != null) origin = coordinates;
    }

    /**
     * Update board if the chosen move destination is legal, and return "true".
     * Otherwise, if there's not any legal moves, or the destination is illegal,
     * reset to the beginning of the move and return "false".
     * TODO: remove "legal == null" check once it's implemented in selectPiece()
     */
    private boolean setDestination(Board.Coordinates destination) {
        if (piece.calculateLegalMoves(board) == null) {
            restartMove();
            return false;
        }
        if (piece.calculateLegalMoves(board).contains(destination)) {
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
