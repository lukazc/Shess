package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
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
    public Collection<Board.Coordinates> findLegalMoves(Board board) {

        clearLegalMoves();

        Board.Coordinates startPosition = this.getPiecePositionTracker();
        int x = startPosition.getX();
        int y = startPosition.getY();

        Map boardState = board.getBoardState();

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
        return getLegalMoves();
    }

    @Override
    public Collection<Board.Coordinates> findIllegalMoves(Board board) {

        int enemyX = 0;
        int enemyY = 0;
        clearIllegalMoves();

        Map boardState = board.getBoardState();

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                enemyX = 0;
                enemyY = 0;
                Board.Coordinates enemyCoordinates = new Board.Coordinates(i, j);
                Piece piece = (Piece) boardState.get(enemyCoordinates);
                if (piece.getPieceType() == PieceType.ROOK && piece.getPieceTeam() != this.getPieceTeam()) {
                    while (inBoardBounds(enemyX - i, j)) {
                        enemyCoordinates = new Board.Coordinates(enemyX - i, j);
                        addIllegalMove(enemyCoordinates);
                    }
                }
            }

        }return getIllegalMoves();
    }

}
