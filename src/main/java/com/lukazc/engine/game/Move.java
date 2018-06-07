package com.lukazc.engine.game;

import com.lukazc.engine.pieces.Piece;
import com.lukazc.engine.player.Black;
import com.lukazc.engine.player.Player;
import com.lukazc.engine.player.Team;
import com.lukazc.engine.player.White;
import com.lukazc.gui.Chessboard;

import java.util.Collection;
import java.util.HashSet;


public class Move {
    //    private Player currentPlayer;
    private Piece piece;
    private Board.Coordinates origin;
    private Board.Coordinates destination;
    private final Board board;

    private Player currentPlayer;
    private White whitePlayer;
    private Black blackPlayer;

    public Move(Board board) {
        this.board = board;
        whitePlayer = new White();
        blackPlayer = new Black();

        currentPlayer = whitePlayer;

        // Calculate legal moves for every piece.
        calculateLegalMovesForAllPieces(board, currentPlayer);
        filterLegalMovesForCurrentPlayer(board, currentPlayer);
    }

    public void selectTile(Board.Coordinates coordinates) {
        // Select a Piece.
        if (piece == null) {
            // Only if it matches current player's color, and it can move.
            selectPiece(coordinates);
        }

        // Once a piece has been selected:
        if (piece != null) {
            if (origin == null) {
                // Set its coordinates as "origin".
                setOrigin(coordinates);
            } else {
                // If origin is already set: Second click selects the "destination".
                if (setDestination(coordinates)) {
                    // If a legal move destination is selected, finally make the move.
                    board.updateBoardState(this);
                    /* TODO: Pawn Promotion
                     When a pawn reaches the final rank, it's substituted for a Queen. */
                    piece.trackFirstMove();
                    piece.setPiecePositionTracker(coordinates);
                    startNextTurn();
                }
            }
        }
    }

    // Reset all Move information, and switch player.
    private void startNextTurn() {

        resetMove();

        // Switch player.
        if (currentPlayer == whitePlayer) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }

        // Calculate legal moves for each piece.
        calculateLegalMovesForAllPieces(board, currentPlayer);
        filterLegalMovesForCurrentPlayer(board, currentPlayer);
    }

    // Reset all selections, and remove legal moves indicators.
    private void resetMove() {
        piece = null;
        origin = null;
        destination = null;
        Chessboard.hideLegalMoves();

        whitePlayer.resetTurnInfo();
        blackPlayer.resetTurnInfo();
    }

    // If the Piece is one of the player's, and it can move, select it.
    private void selectPiece(Board.Coordinates coordinates) {
        Piece selection = board.getBoardState().get(coordinates);

        // If tile is empty, or the chosen piece can't move, do nothing.
        if (selection == null || selection.getLegalMoves().isEmpty()) return;

        // Select the piece if it's owned by current player.
        // Show tiles it can move to.
        if (currentPlayer == whitePlayer && selection.getPieceTeam() == Team.WHITE
                || currentPlayer == blackPlayer && selection.getPieceTeam() == Team.BLACK) {
            piece = selection;
            Chessboard.showLegalMoves(piece.getLegalMoves());
        }


    }

    private void setOrigin(Board.Coordinates coordinates) {
        origin = coordinates;
    }

    /**
     * Update board if the chosen move destination is legal, and return "true".
     * Otherwise, if there's not any legal moves, or the destination is illegal,
     * reset to the beginning of the move and return "false".
     */
    private boolean setDestination(Board.Coordinates destination) {
        if (piece.getLegalMoves().contains(destination)) {
            this.destination = destination;
            return true;
        } else {
            resetMove();
            return false;
        }
    }

    private void calculateLegalMovesForAllPieces(Board board, Player currentPlayer) {
        for (Piece piece : board.getBoardState().values()) {
            if (piece != null) {
                piece.clearLegalMoves();
                piece.calculateLegalMoves(board, currentPlayer);
            }
        }
    }

    private void filterLegalMovesForCurrentPlayer(Board board, Player currentPlayer) {
        // For each piece
        for (Piece piece : board.getBoardState().values()) {
            Collection<Board.Coordinates> illegalMoves = new HashSet<>();
            // Of current player's
            if (piece != null && piece.getPieceTeam() == currentPlayer.getTeam()) {
                // If the current player is in check, only allow him moves which will get him out.
                // If current player is in check
                if (currentPlayer.isInCheck()) {
                    // If the player is in check by two pieces simultaneously, only the king can move to escape.
                    if (currentPlayer.isInDoubleCheck()) {
                        // Take away all the moves from his soldiers.
                        // TODO: Test the double-check filter once the scanner is global.
                        if (piece.getPieceType() != Piece.PieceType.KING) piece.clearLegalMoves();
                    }
                    for (Board.Coordinates legalMove : piece.getLegalMoves()) {
                        if (piece.getPieceType() == Piece.PieceType.KING) {
                            // For King, remove legal moves within checkline.
                            if (currentPlayer.getCheckLine().contains(legalMove)) illegalMoves.add(legalMove);
                        } else {
                            // For soldiers, take away all legal moves except for the checkline and assassin position.
                            if (!currentPlayer.getCheckLine().contains(legalMove)
                                    && !legalMove.equals(currentPlayer.getAssassinPosition())) {
                                System.out.println(currentPlayer.getAssassinPosition().getX() + " " + currentPlayer.getAssassinPosition().getY());
                                illegalMoves.add(legalMove);
                            }
                        }
                    }
                    if (!illegalMoves.isEmpty()) piece.removeFromLegalMoves(illegalMoves);
                }
            }
        }

        // TODO: If a piece is guarding the king, filter its moves -> (guardLine && potential assassin)

        // TODO: mark guarded friendly pieces (on friendly potentialAttacks tiles)
        // TODO: filter out King legal moves -> (enemy potentialAttacks && guarded pieces)
    }

    public Piece getPiece() {
        return piece;
    }

    Board.Coordinates getOrigin() {
        return origin;
    }

    Board.Coordinates getDestination() {
        return destination;
    }
}
