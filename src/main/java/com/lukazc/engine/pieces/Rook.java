package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.game.Move;
import com.lukazc.engine.player.Team;

import java.util.Collection;

public class Rook extends Piece {
    public Rook(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition) {
        super(pieceType, pieceTeam, piecePosition);
    }

    @Override
    public Collection<Move> findLegalMoves() {
        Board.Coordinates position = this.getPiecePositionTracker();


        return null;
    }
}
