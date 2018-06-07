package com.lukazc.engine.player;

import com.lukazc.engine.game.Board;

import java.util.Collection;
import java.util.HashSet;

public class Player {
    // Tiles to which the player's King can't go.
    private Collection<Board.Coordinates> potentialChecks;

    // Tracks whether the player's King is in check.
    private boolean inCheck, inDoubleCheck;
    // The positions between the King and Assassin.
    private Collection<Board.Coordinates> checkLine;
    // Assassin's position.
    private Board.Coordinates assassinPosition;
    // Set of legal moves for all the player's pieces, for tracking checkmate and stalemate.
    private Collection<Board.Coordinates> allLegalMoves;

    Player() {
        potentialChecks = new HashSet<>();

        inCheck = false;
        inDoubleCheck = false;
        checkLine = new HashSet<>();
        assassinPosition = null;

        allLegalMoves = new HashSet<>();
    }

    public Collection<Board.Coordinates> getPotentialChecks() {
        return potentialChecks;
    }

    public void addPotentialCheck(Board.Coordinates potentialCheckCoordinates) {
        this.potentialChecks.add(potentialCheckCoordinates);
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    public boolean isInDoubleCheck() {
        return inDoubleCheck;
    }

    public void setInDoubleCheck(boolean inDoubleCheck) {
        this.inDoubleCheck = inDoubleCheck;
    }

    public Collection<Board.Coordinates> getCheckLine() {
        return checkLine;
    }

    public void setCheckLine(Collection<Board.Coordinates> checkLine) {
        this.checkLine = checkLine;
    }

    public Board.Coordinates getAssassinPosition() {
        return assassinPosition;
    }

    public void setAssassinPosition(Board.Coordinates assassinPosition) {
        this.assassinPosition = assassinPosition;
    }

    public void addLegalMove(Board.Coordinates coordinates){
        allLegalMoves.add(coordinates);
    }
    public Collection<Board.Coordinates> getAllLegalMoves(){
        return allLegalMoves;
    }

    public void resetTurnInfo(){
        potentialChecks.clear();

        inCheck = false;
        inDoubleCheck = false;
        checkLine.clear();
        assassinPosition = null;

        allLegalMoves.clear();
    }
}
