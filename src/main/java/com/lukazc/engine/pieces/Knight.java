package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.game.Move;
import com.lukazc.engine.player.Team;

import java.util.Collection;

public class Knight extends Piece {
    public Knight(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition, boolean isFirstMove) {
        super(pieceType, pieceTeam, piecePosition, isFirstMove);
    }

    @Override
    public Collection<Move> findLegalMoves(Board board) {
        return null;
    }
}
