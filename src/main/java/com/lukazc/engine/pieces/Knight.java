package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.player.Team;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Knight extends Piece {

    private final Set<Board.Coordinates> legalMoves = new HashSet<>();

    public Knight(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition) {
        super(pieceType, pieceTeam, piecePosition);
    }

    /**
     * Check Knight's possible jumps.
     * Collect coordinates of empty tiles and those occupied by enemy pieces.
     */
    @Override
    public Collection<Board.Coordinates> findLegalMoves(Board board) {

        legalMoves.clear();

        Board.Coordinates startPosition = this.getPiecePositionTracker();
        int x = startPosition.getX();
        int y = startPosition.getY();

        int doubleMod = 2;
        int singleMod = 1;

        Map boardState = board.getBoardState();

        checkTile(x-doubleMod, y-singleMod, boardState);
        checkTile(x-doubleMod, y+singleMod, boardState);
        checkTile(x-singleMod, y-doubleMod, boardState);
        checkTile(x-singleMod, y+doubleMod, boardState);
        checkTile(x+singleMod, y-doubleMod, boardState);
        checkTile(x+singleMod, y+doubleMod, boardState);
        checkTile(x+doubleMod, y-singleMod, boardState);
        checkTile(x+doubleMod, y+singleMod, boardState);
        
        return legalMoves;
    }
    private void checkTile (int x, int y, Map boardState) {
        if (inBoardBounds(x, y)) {
            Board.Coordinates coordinates = new Board.Coordinates(x, y);
            Piece piece = (Piece) boardState.get(coordinates);

            // If empty tile, add to legalMoves.
            if (piece == null) {
                legalMoves.add(coordinates);
            }
            // If there's a piece on the tile,
            // and it's the enemy, but not the King,
            // add to legalMoves.
            if (piece != null) {
                if (piece.getPieceTeam() != this.getPieceTeam()
                        && piece.getPieceType() != PieceType.KING) {
                    legalMoves.add(coordinates);
                }
            }
        }
    }

}
