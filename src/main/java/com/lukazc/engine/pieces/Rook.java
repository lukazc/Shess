package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.player.Team;

import java.util.Collection;
import java.util.Map;

public class Rook extends Piece {

    public Rook(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition) {
        super(pieceType, pieceTeam, piecePosition);
    }

    /**
     * Check board horizontally and vertically from Rook's position.
     * Collect coordinates of empty tiles to a Set.
     * Stop when a Piece is found. If it's an enemy piece, store its coordinates too.
     */
    @Override
    public Collection<Board.Coordinates> findLegalMoves(Board board) {

        clearLegalMoves();

        Board.Coordinates startPosition = this.getPiecePositionTracker();
        int x = startPosition.getX();
        int y = startPosition.getY();

        int xMod = 1;
        int yMod = 1;

        Map boardState = board.getBoardState();

        // North vertical
        while (inBoardBounds(x - xMod, y)) {
            Board.Coordinates coordinates = new Board.Coordinates(x - xMod, y);
            Piece piece = (Piece) boardState.get(coordinates);

            if (piece == null) {
                addLegalMove(coordinates);
            }
            if (piece != null) {
                if (piece.getPieceTeam() != this.getPieceTeam()
                        && piece.getPieceType() != PieceType.KING) {
                    addLegalMove(coordinates);
                }
                break;
            }
            xMod++;
            yMod++;
        }

        xMod = 1;
        yMod = 1;

        // South vertical
        while (inBoardBounds(x + xMod, y)) {
            Board.Coordinates coordinates = new Board.Coordinates(x + xMod, y);
            Piece piece = (Piece) boardState.get(coordinates);

            if (piece == null) {
                addLegalMove(coordinates);
            }
            if (piece != null) {
                if (piece.getPieceTeam() != this.getPieceTeam()
                        && piece.getPieceType() != PieceType.KING) {
                    addLegalMove(coordinates);
                }
                break;
            }
            xMod++;
            yMod++;
        }

        xMod = 1;
        yMod = 1;

        // East horizontal
        while (inBoardBounds(x, y + yMod)) {
            Board.Coordinates coordinates = new Board.Coordinates(x, y + yMod);
            Piece piece = (Piece) boardState.get(coordinates);

            if (piece == null) {
                addLegalMove(coordinates);
            }
            if (piece != null) {
                if (piece.getPieceTeam() != this.getPieceTeam()
                        && piece.getPieceType() != PieceType.KING) {
                    addLegalMove(coordinates);
                }
                break;
            }
            xMod++;
            yMod++;
        }

        xMod = 1;
        yMod = 1;

        // West horizontal
        while (inBoardBounds(x, y - yMod)) {
            Board.Coordinates coordinates = new Board.Coordinates(x, y - yMod);
            Piece piece = (Piece) boardState.get(coordinates);

            if (piece == null) {
                addLegalMove(coordinates);
            }
            if (piece != null) {
                if (piece.getPieceTeam() != this.getPieceTeam()
                        && piece.getPieceType() != PieceType.KING) {
                    addLegalMove(coordinates);
                }
                break;
            }
            xMod++;
            yMod++;
        }

        return getLegalMoves();
    }


}