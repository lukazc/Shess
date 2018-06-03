package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.player.Team;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

    private final Set<Board.Coordinates> legalMoves = new HashSet<>();

    public Queen(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition) {
        super(pieceType, pieceTeam, piecePosition);
    }

    @Override
    public Collection<Board.Coordinates> findLegalMoves(Board board) {

        legalMoves.clear();

        return null;
    }
}
