package com.lukazc.engine.game;

import com.lukazc.Main;
import com.lukazc.engine.pieces.*;
import com.lukazc.engine.player.Team;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class Board {

    /**
     * Coordinates class represents locations of board tiles, each having unique X and Y.
     * It is used as the key to the boardState Map.
     */
    public static class Coordinates {
        //        X and Y coordinates unique to each tile, and their getter functions.
        final int x;
        final int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        /**
         * Constructor for Coordinates class.
         * @param x X coordinate.
         * @param y Y coordinate.
         */
        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /* equals() and hashCode() are overridden
                to compare the coordinates X and Y
                when two Coordinates objects are compared,
                such as when they're used as the Map key. */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /**
     * boardState
     * Map of the current positions on the board.
     * @key Coordinates object, containing X and Y.
     * @value Piece object, or null.
     */
    private final Map<Coordinates, Piece> boardState = new HashMap<>();

    // Sets of players' active pieces.
//    private final Collection<Piece> whiteLivePieces;
//    private final Collection<Piece> blackLivePieces;


    /**
     * Find all legal moves of some collection of pieces.
     * @param pieces All Black or White pieces.
     * @return Set of Coordinates of legal moves for White or Black pieces.
     */
//    private Set<Coordinates> findLegalMoves(final Collection<Piece> pieces) {
//        return pieces.stream().flatMap(piece -> piece.calculateLegalMoves(this).stream())
//                .collect(Collectors.toSet());
//    }

    /**
     * Remove Piece from origin, and set the Piece to new destination coordinates.
     */
    void updateBoardState(Move move) {
        Coordinates origin = move.getOrigin();
        Coordinates destination = move.getDestination();
        Piece piece = move.getPiece();

        // Remove the Piece from its starting point.
        boardState.put(origin, null);
        // Register the Piece to new coordinates.
        boardState.put(destination, piece);

        Main.drawAllPieces(boardState);
    }

    public Map<Coordinates, Piece> getBoardState() {
        return boardState;
    }

    /**
     * Setup all Black and White pieces in their initial positions.
     */
    public void setupPieces(){
        setupBishops();
        setupKings();
        setupKnights();
        setupPawns();
        setupQueens();
        setupRooks();
    }

    /**
     * Setup 8 black and 8 white pawns in their initial positions.
     */
    private void setupPawns(){
        for(int i = 0; i < 8; i++){
            Coordinates blackPawnStartingPoint = new Coordinates(1, i);
            Coordinates whitePawnStartingPoint = new Coordinates(6, i);
            Pawn blackPawn = new Pawn(Piece.PieceType.PAWN, Team.BLACK, blackPawnStartingPoint);
            Pawn whitePawn = new Pawn(Piece.PieceType.PAWN, Team.WHITE, whitePawnStartingPoint);
            boardState.put(blackPawnStartingPoint, blackPawn);
            boardState.put(whitePawnStartingPoint, whitePawn);
        }
    }

    /**
     * Setup 2 black rooks and 2 white rooks in their initial positions.
     */
    private void setupRooks(){
        Coordinates blackRookOneStartingPoint = new Coordinates(0, 0);
        Coordinates blackRookTwoStartingPoint = new Coordinates(0, 7);
        Rook blackRookOne = new Rook(Piece.PieceType.ROOK, Team.BLACK, blackRookOneStartingPoint);
        Rook blackRookTwo = new Rook(Piece.PieceType.ROOK, Team.BLACK, blackRookTwoStartingPoint);
        boardState.put(blackRookOneStartingPoint, blackRookOne);
        boardState.put(blackRookTwoStartingPoint, blackRookTwo);

        Coordinates whiteRookOneStartingPoint = new Coordinates(7, 0);
        Coordinates whiteRookTwoStartingPoint = new Coordinates(7, 7);
        Rook whiteRookOne = new Rook(Piece.PieceType.ROOK, Team.WHITE, whiteRookOneStartingPoint);
        Rook whiteRookTwo = new Rook(Piece.PieceType.ROOK, Team.WHITE, whiteRookTwoStartingPoint);
        boardState.put(whiteRookOneStartingPoint, whiteRookOne);
        boardState.put(whiteRookTwoStartingPoint, whiteRookTwo);
    }

    /**
     * Setup 2 black Bishops and 2 white Bishops in their initial positions.
     */
    private void setupBishops(){
        Coordinates blackBishopOneStartingPoint = new Coordinates(0, 2);
        Coordinates blackBishopTwoStartingPoint = new Coordinates(0, 5);
        Bishop blackBishopOne = new Bishop(Piece.PieceType.BISHOP, Team.BLACK, blackBishopOneStartingPoint);
        Bishop blackBishopTwo = new Bishop(Piece.PieceType.BISHOP, Team.BLACK, blackBishopTwoStartingPoint);
        boardState.put(blackBishopOneStartingPoint, blackBishopOne);
        boardState.put(blackBishopTwoStartingPoint, blackBishopTwo);

        Coordinates whiteBishopOneStartingPoint = new Coordinates(7, 2);
        Coordinates whiteBishopTwoStartingPoint = new Coordinates(7, 5);
        Bishop whiteBishopOne = new Bishop(Piece.PieceType.BISHOP, Team.WHITE, whiteBishopOneStartingPoint);
        Bishop whiteBishopTwo = new Bishop(Piece.PieceType.BISHOP, Team.WHITE, whiteBishopTwoStartingPoint);
        boardState.put(whiteBishopOneStartingPoint, whiteBishopOne);
        boardState.put(whiteBishopTwoStartingPoint, whiteBishopTwo);
    }

    /**
     * Setup 2 black Knights and 2 white Knights in their initial positions.
     */
    private void setupKnights(){
        Coordinates blackKnightOneStartingPoint = new Coordinates(0, 1);
        Coordinates blackKnightTwoStartingPoint = new Coordinates(0, 6);
        Knight blackKnightOne = new Knight(Piece.PieceType.KNIGHT, Team.BLACK, blackKnightOneStartingPoint);
        Knight blackKnightTwo = new Knight(Piece.PieceType.KNIGHT, Team.BLACK, blackKnightTwoStartingPoint);
        boardState.put(blackKnightOneStartingPoint, blackKnightOne);
        boardState.put(blackKnightTwoStartingPoint, blackKnightTwo);

        Coordinates whiteKnightOneStartingPoint = new Coordinates(7, 1);
        Coordinates whiteKnightTwoStartingPoint = new Coordinates(7, 6);
        Knight whiteKnightOne = new Knight(Piece.PieceType.KNIGHT, Team.WHITE, whiteKnightOneStartingPoint);
        Knight whiteKnightTwo = new Knight(Piece.PieceType.KNIGHT, Team.WHITE, whiteKnightTwoStartingPoint);
        boardState.put(whiteKnightOneStartingPoint, whiteKnightOne);
        boardState.put(whiteKnightTwoStartingPoint, whiteKnightTwo);
    }

    /**
     * Setup 2 queens white and black in their initial positions.
     */
    private void setupQueens(){
        Coordinates blackQueenStartingPoint = new Coordinates(0, 3);
        Queen blackQueen = new Queen(Piece.PieceType.QUEEN, Team.BLACK, blackQueenStartingPoint);
        boardState.put(blackQueenStartingPoint, blackQueen);

        Coordinates whiteQueenStartingPoint = new Coordinates(7, 3);
        Queen whiteQueen = new Queen(Piece.PieceType.QUEEN, Team.WHITE, whiteQueenStartingPoint);
        boardState.put(whiteQueenStartingPoint, whiteQueen);
    }

    /**
     * Setup 2 queens white and black in their initial positions.
     */
    private void setupKings(){
        Coordinates blackKingStartingPoint = new Coordinates(0,4);
        King blackKing = new King(Piece.PieceType.KING, Team.BLACK, blackKingStartingPoint);
        boardState.put(blackKingStartingPoint, blackKing);

        Coordinates whiteKingStartingPoint = new Coordinates(7,4);
        King whiteKing = new King(Piece.PieceType.KING, Team.WHITE, whiteKingStartingPoint);
        boardState.put(whiteKingStartingPoint, whiteKing);
    }
}
