package com.lukazc.engine.game;

import com.lukazc.engine.pieces.Piece;
import com.lukazc.engine.player.Team;


public class Move {
    //    private Player currentPlayer;
    private Piece piece;
    private Board.Coordinates origin;
    private Board.Coordinates destination;
    private Board board;

    private boolean whiteMoves = true;

    public Move(Board board) {
//        this.currentPlayer = player;
        this.board = board;
    }

    public void select(Board.Coordinates coordinates) {

        if (piece == null) {
            setPiece(coordinates);
        }

        if (piece != null) {
            if (origin == null) {
                setOrigin(coordinates);
            } else {
                setDestination(coordinates);
                board.updateBoardState(this);
                startNextTurn();
            }
        }
    }

    private void startNextTurn() {
        piece = null;
        origin = null;
        setDestination(null);

        whiteMoves = !whiteMoves;
    }

    private void setPiece(Board.Coordinates coordinates) {
        Piece selection = board.getBoardState().get(coordinates);
        if (selection == null) return;
        if (whiteMoves && selection.getPieceTeam() == Team.WHITE) piece = selection;
        if (!whiteMoves && selection.getPieceTeam() == Team.BLACK) piece = selection;
    }
    private void setOrigin(Board.Coordinates coordinates){
        if (piece != null) origin = coordinates;
    }
    private void setDestination(Board.Coordinates destination) {
        this.destination = destination;
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
