package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.player.Team;

import java.util.Collection;
import java.util.Map;

public class Pawn extends Piece {

    public Pawn(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition) {
        super(pieceType, pieceTeam, piecePosition);
    }

    @Override
    public Collection<Board.Coordinates> calculateLegalMoves(Board board) {

        Board.Coordinates startPosition = this.getPiecePositionTracker();
        int x = startPosition.getX();
        int y = startPosition.getY();

        Map boardState = board.getBoardState();

        clearLegalMoves();

        // Set direction of movement, according to team.
        int push;
        int doublePush;
        if (getPieceTeam() == Team.BLACK) {
            push = 1;
            doublePush = 2;
        } else {
            push = -1;
            doublePush =-2;
        }

        // Regular push move
        for (int i = -1; i <=1 ; i++) {
            if (inBoardBounds(x+push, y+i)) {
                Board.Coordinates coordinates = new Board.Coordinates(x+push, y+i);
                Piece piece = (Piece) boardState.get(coordinates);

                // If empty tile is in front of Pawn, add to legalMoves.
                if (piece == null && i == 0) {
                    addLegalMove(coordinates);
                }
                // If there's a piece on the diagonally positioned tile,
                // and it's the enemy, but not the King,
                // add to legalMoves.
                if (piece != null && i != 0) {
                    if (piece.getPieceTeam() != this.getPieceTeam()
                            && piece.getPieceType() != PieceType.KING) {
                        addLegalMove(coordinates);
                    }
                }
            }
        }

        // Double push move.
        // Only if this is its first move and path is clear.
        if (this.isFirstMove()) {
            Board.Coordinates coordinatesFirstTile = new Board.Coordinates(x+push, y);
            Board.Coordinates coordinatesSecondTile = new Board.Coordinates(x+doublePush, y);
            Piece pieceFirstTile = (Piece) boardState.get(coordinatesFirstTile);
            Piece pieceSecondTile = (Piece) boardState.get(coordinatesSecondTile);

            if (pieceFirstTile == null && pieceSecondTile == null) addLegalMove(coordinatesSecondTile);
        }

        // TODO: En passant
        // When a pawn advances two squares and ends the turn adjacent to a pawn of the opponent's,
        // it may be captured by that pawn, as if it had moved only one square forward.
        // This capture is only legal on the opponent's immediately following move.

        return getLegalMoves();
    }
}
