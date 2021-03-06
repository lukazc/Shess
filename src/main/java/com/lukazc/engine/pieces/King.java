package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.player.Player;
import com.lukazc.engine.player.Team;

import java.util.Collection;
import java.util.Map;

public class King extends Piece {

    public King(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition) {
        super(pieceType, pieceTeam, piecePosition);
    }

    /**
     * Check board tiles directly around the King.
     * Collect coordinates of empty tiles and those occupied by enemy pieces.
     */
    @Override
    public Collection<Board.Coordinates> calculateLegalMoves(Board board, Player currentPlayer) {

        Board.Coordinates startPosition = this.getPiecePositionTracker();
        int x = startPosition.getX();
        int y = startPosition.getY();

        Map boardState = board.getBoardState();

        // Standard move
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (inBoardBounds(x + i, y + j)) {
                    Board.Coordinates coordinates = new Board.Coordinates(x + i, y + j);
                    Piece piece = (Piece) boardState.get(coordinates);

                    // If empty tile, add to legalMoves.
                    if (piece == null) {
                        addLegalMove(coordinates);
                    }
                    // If there's a piece on the tile,
                    // and it's the enemy, but not the King,
                    // add to legalMoves.
                    if (piece != null) {
                        if (piece.getPieceTeam() != this.getPieceTeam()
                                && piece.getPieceType() != PieceType.KING) {
                            addLegalMove(coordinates);
                        }
                    }
                }
            }
        }

        /* TODO: add castling
         - rook and king have not moved
         - squares between rook and king are empty
         - king does not castle from, through, or to check
         */


        return getLegalMoves();
    }
}
