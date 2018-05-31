package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.game.Board.Coordinates;
import com.lukazc.engine.game.Move;
import com.lukazc.engine.player.Team;

import java.util.Collection;

public abstract class Piece {
    private final PieceType pieceType;
    private final Team pieceTeam;
    private final Coordinates piecePosition;
    private final boolean isFirstMove;

    Piece(PieceType pieceType, Team pieceTeam, Coordinates piecePosition, boolean isFirstMove) {
        this.pieceType = pieceType;
        this.pieceTeam = pieceTeam;
        this.piecePosition = piecePosition;
        this.isFirstMove = isFirstMove;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Team getPieceTeam() {
        return pieceTeam;
    }

    public Coordinates getPiecePosition() {
        return piecePosition;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    //    Returns a list of coordinates of all tiles this piece can reach.
    public abstract Collection<Move> findLegalMoves(final Board board);

    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isPawn() {
                return true;
            }

            @Override
            public boolean isBishop() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }

            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isPawn() {
                return false;
            }

            @Override
            public boolean isBishop() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }

            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isPawn() {
                return false;
            }

            @Override
            public boolean isBishop() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }

            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isPawn() {
                return false;
            }

            @Override
            public boolean isBishop() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }

            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isPawn() {
                return false;
            }

            @Override
            public boolean isBishop() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }

            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isPawn() {
                return false;
            }

            @Override
            public boolean isBishop() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }

            @Override
            public boolean isKing() {
                return true;
            }
        };

        private final String pieceName;

        @Override
        public String toString() {
            return this.pieceName;
        }

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        public abstract boolean isPawn();
        public abstract boolean isBishop();
        public abstract boolean isRook();
        public abstract boolean isKing();

    }

}
