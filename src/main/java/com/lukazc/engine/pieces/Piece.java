package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.game.Board.Coordinates;
import com.lukazc.engine.player.Team;

import java.util.Collection;

public abstract class Piece {
    private final PieceType pieceType;
    private final Team pieceTeam;
    private Coordinates piecePositionTracker;
    private boolean isFirstMove;

    Collection<Coordinates> legalMoves;

    Piece(PieceType pieceType, Team pieceTeam, Coordinates piecePositionTracker) {
        this.pieceType = pieceType;
        this.pieceTeam = pieceTeam;
        this.piecePositionTracker = piecePositionTracker;
        this.isFirstMove = true;
    }

    // PieceType made final to implement in printBoard()

    public final PieceType getPieceType() { return pieceType; }

    public Team getPieceTeam() { return pieceTeam; }

    Coordinates getPiecePositionTracker() {
        return piecePositionTracker;
    }
    public void setPiecePositionTracker(Coordinates newPosition) { this.piecePositionTracker = newPosition; }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    // Register when the Piece has moved for the first time.
    public void registerMove() { if (this.isFirstMove) this.isFirstMove = false; }

    //    Returns a list of coordinates of all tiles this piece can reach.
    public abstract Collection<Coordinates> findLegalMoves(Board board);

    /**
     * Helper method to check if locations passed in are mapped on our generated board.
     * @param newX
     * @param newY
     * @return boolean true if move is in board bounds
     */
    boolean inBoardBounds(int newX, int newY){
        return newX < 8 && newY < 8 && newX > -1 && newY > -1;
    }

    public enum PieceType {

        PAWN("P") {},
        KNIGHT("N") {},
        BISHOP("B") {},
        ROOK("R") {},
        QUEEN("Q") {},
        KING("K") {};

        private final String pieceName;

        @Override
        public String toString() {
            return this.pieceName;
        }

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }


    }

}
