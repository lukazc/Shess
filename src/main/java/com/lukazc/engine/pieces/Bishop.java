package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.player.Team;

import java.util.*;

public class Bishop extends Piece {

    Set<Board.Coordinates> legalMoves = new HashSet<>();

    public Bishop(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition) {
        super(pieceType, pieceTeam, piecePosition);
    }

    /**
     * Check board diagonally in 4 directions. Collect coordinates of empty tiles to a Set.
     * Stop when a Piece is found. If it's an enemy piece, store its coordinates too.
     */
    @Override
    public Collection<Board.Coordinates> findLegalMoves(Board board) {

        legalMoves.clear();

        Board.Coordinates startPosition = this.getPiecePositionTracker();
        int x = startPosition.getX();
        int y = startPosition.getY();

        int xMod = 1;
        int yMod = 1;

        Map boardState = board.getBoardState();

        // South East diagonal
        while (inBoardBounds(x + xMod, y + yMod)) {
            Board.Coordinates coordinates = new Board.Coordinates(x + xMod, y + yMod);
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
                break;
            }
            xMod++;
            yMod++;
        }

        xMod = 1;
        yMod = 1;
        // South West diagonal
        while (inBoardBounds(x + xMod, y - yMod)) {
            Board.Coordinates coordinates = new Board.Coordinates(x + xMod, y - yMod);
            Piece piece = (Piece) boardState.get(coordinates);

            if (piece == null) {
                legalMoves.add(coordinates);
            }
            if (piece != null) {
                if (piece.getPieceTeam() != this.getPieceTeam()
                        && piece.getPieceType() != PieceType.KING) {
                    legalMoves.add(coordinates);
                }
                break;
            }
            xMod++;
            yMod++;
        }

        xMod = 1;
        yMod = 1;
        // North West diagonal
        while (inBoardBounds(x - xMod, y - yMod)) {
            Board.Coordinates coordinates = new Board.Coordinates(x - xMod, y - yMod);
            Piece piece = (Piece) boardState.get(coordinates);

            if (piece == null) {
                legalMoves.add(coordinates);
            }
            if (piece != null) {
                if (piece.getPieceTeam() != this.getPieceTeam()
                        && piece.getPieceType() != PieceType.KING) {
                    legalMoves.add(coordinates);
                }
                break;
            }
            xMod++;
            yMod++;
        }

        xMod = 1;
        yMod = 1;
        // North East diagonal
        while (inBoardBounds(x - xMod, y + yMod)) {
            Board.Coordinates coordinates = new Board.Coordinates(x - xMod, y + yMod);
            Piece piece = (Piece) boardState.get(coordinates);

            if (piece == null) {
                legalMoves.add(coordinates);
            }
            if (piece != null) {
                if (piece.getPieceTeam() != this.getPieceTeam()
                        && piece.getPieceType() != PieceType.KING) {
                    legalMoves.add(coordinates);
                }
                break;
            }
            xMod++;
            yMod++;
        }

        return legalMoves;
    }



}
