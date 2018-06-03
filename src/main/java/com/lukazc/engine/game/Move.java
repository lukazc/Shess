package com.lukazc.engine.game;

import com.lukazc.engine.pieces.Piece;
import com.lukazc.engine.player.Team;


public class Move {
    //    private Player currentPlayer;
    private Piece piece;
    private Board.Coordinates origin;
    private Board.Coordinates destination;
    private final Board board;

    private boolean whiteMoves = true;

    public Move(Board board) {
//        this.currentPlayer = player;
        this.board = board;
    }

    public void select(Board.Coordinates coordinates) {
        // Select a Piece.
        if (piece == null) {
            // If it matches player's color.
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
                    piece.registerMove();
                    piece.setPiecePositionTracker(coordinates);
                    startNextTurn();
                }
            }
        }
    }

    // Reset all Move information, and switch player.
    private void startNextTurn() {
        quitMove();
        whiteMoves = !whiteMoves;
    }

    private void quitMove() {
        piece = null;
        origin = null;
        destination = null;
    }

    // If the selected Piece is one of the player's pieces, prepare it to move.
    private void selectPiece(Board.Coordinates coordinates) {
        Piece selection = board.getBoardState().get(coordinates);
        if (selection == null) return;
        if (whiteMoves && selection.getPieceTeam() == Team.WHITE) piece = selection;
        if (!whiteMoves && selection.getPieceTeam() == Team.BLACK) piece = selection;
    }

    private void setOrigin(Board.Coordinates coordinates) {
        if (piece != null) origin = coordinates;
    }

    private boolean setDestination(Board.Coordinates destination) {
        // TODO: check if destination lies inside legalMoves.
        if (piece.findLegalMoves(board) == null) {
            quitMove();
            return false;
        }
        if (piece.findLegalMoves(board).contains(destination)) {
            this.destination = destination;
            return true;
        } else {
            quitMove();
            return false;
        }
    }


    public Piece getPiece() {
        return piece;
    }

    public Board.Coordinates getOrigin() {
        return origin;
    }

    public Board.Coordinates getDestination() {
        return destination;
    }
}
