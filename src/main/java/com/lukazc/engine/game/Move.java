package com.lukazc.engine.game;

import com.lukazc.engine.pieces.Piece;

public class Move {
    private final Board.Coordinates origin;
    private final Board.Coordinates destination;
    private final Piece piece;

    public Move(Board.Coordinates origin, Board.Coordinates destination, Piece piece) {
        this.origin = origin;
        this.destination = destination;
        this.piece = piece;
    }
}
