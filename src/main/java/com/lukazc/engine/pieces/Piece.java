package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.game.Board.Coordinates;
import com.lukazc.engine.player.Player;
import com.lukazc.engine.player.Team;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
    private final PieceType pieceType;
    private final Team pieceTeam;
    private Coordinates piecePositionTracker;
    private boolean isFirstMove;

    private final Set<Coordinates> legalMoves = new HashSet<>();


    private boolean isKingsGuard;


    private Collection<Coordinates> guardedCheckLine = new HashSet<>();
    private Coordinates potentialAssassin;


    Piece(PieceType pieceType, Team pieceTeam, Coordinates startingPosition) {
        this.pieceType = pieceType;
        this.pieceTeam = pieceTeam;
        this.piecePositionTracker = startingPosition;
        this.isFirstMove = true;
    }

    public Coordinates getPotentialAssassin() {
        return potentialAssassin;
    }

    void setPotentialAssassin(Coordinates potentialAssassin) {
        this.potentialAssassin = potentialAssassin;
    }

    public boolean isKingsGuard(){
        return this.isKingsGuard;
    }

    void setKingsGuard() {
        isKingsGuard = true;
    }

    public Collection<Coordinates> getGuardedCheckLine() {
        return guardedCheckLine;
    }

    void setGuardedCheckLine(Collection<Coordinates> guardedCheckLine) {
        this.guardedCheckLine = guardedCheckLine;
    }

    public void resetKingsGuard() {
        isKingsGuard = false;
        this.guardedCheckLine.clear();
        potentialAssassin = null;
    }


    public final PieceType getPieceType() { return pieceType; }

    public Team getPieceTeam() { return pieceTeam; }

    Coordinates getPiecePositionTracker() {
        return piecePositionTracker;
    }
    public void setPiecePositionTracker(Coordinates newPosition) { this.piecePositionTracker = newPosition; }

    boolean isFirstMove() {
        return isFirstMove;
    }

    // Register when the Piece has moved for the first time.
    public void trackFirstMove() { if (this.isFirstMove) this.isFirstMove = false; }

    /** Returns a list of coordinates of all tiles this piece can reach.*/
    public abstract Collection<Coordinates> calculateLegalMoves(Board board, Player player);

    public void clearLegalMoves() {
        legalMoves.clear();
    }
    void addLegalMove(Coordinates coordinates){
        legalMoves.add(coordinates);
    }

    public Collection<Coordinates> getLegalMoves() {
        return legalMoves;
    }

    public void removeFromLegalMoves (Collection<Coordinates> illegalMoves) {
        for (Coordinates coordinates : illegalMoves) {
            legalMoves.remove(coordinates);
        }
    }

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
