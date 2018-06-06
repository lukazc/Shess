package com.lukazc.engine.pieces;

import com.lukazc.engine.game.Board;
import com.lukazc.engine.player.Team;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class Bishop extends Piece {
    public Bishop(PieceType pieceType, Team pieceTeam, Board.Coordinates piecePosition) {
        super(pieceType, pieceTeam, piecePosition);
    }


    /**
     * Check board diagonally in 4 directions. Collect coordinates of empty tiles to a Set.
     * Stop when a Piece is found. If it's an enemy piece, store its coordinates too.
     */
    @Override
    public Collection<Board.Coordinates> calculateLegalMoves(Board board) {

        LegalMovesScannerDiagonal scanner = new LegalMovesScannerDiagonal();

        // Starting coordinates
        Board.Coordinates startPosition = this.getPiecePositionTracker();
        int x = startPosition.getX();
        int y = startPosition.getY();

        Map boardState = board.getBoardState();

        int northOffset, southOffset, westOffset, eastOffset;

        while (scanner.sum() > 0) {

            northOffset = scanner.getNorthOffset(x);
            southOffset = scanner.getSouthOffset(x);
            westOffset = scanner.getWestOffset(y);
            eastOffset = scanner.getEastOffset(y);

            // NW diagonal
            if (scanner.phaseNW > 0) {
                if (inBoardBounds(northOffset, westOffset)) {
                    Board.Coordinates coordinates = new Board.Coordinates(northOffset, westOffset);
                    Piece piece = (Piece) boardState.get(coordinates);

                    switch (scanner.phaseNW) {
                        case 1:
                            scanner.scanPhaseOne(piece, coordinates, "NW");
                            break;
                        case 2:
                            scanner.scanPhaseTwo(piece, coordinates, "NW");
                            break;
                    }

                } else {
                    scanner.updatePhase("NW", 0);
                }
            }

            // NE diagonal
            if (scanner.phaseNE > 0) {
                if (inBoardBounds(northOffset, eastOffset)) {
                    Board.Coordinates coordinates = new Board.Coordinates(northOffset, eastOffset);
                    Piece piece = (Piece) boardState.get(coordinates);

                    switch (scanner.phaseNE) {
                        case 1:
                            scanner.scanPhaseOne(piece, coordinates, "NE");
                            break;
                        case 2:
                            scanner.scanPhaseTwo(piece, coordinates, "NE");
                            break;
                    }

                } else {
                    scanner.updatePhase("NE", 0);
                }
            }

            // SW diagonal
            if (scanner.phaseSW > 0) {
                if (inBoardBounds(southOffset, westOffset)) {
                    Board.Coordinates coordinates = new Board.Coordinates(southOffset, westOffset);
                    Piece piece = (Piece) boardState.get(coordinates);

                    switch (scanner.phaseSW) {
                        case 1:
                            scanner.scanPhaseOne(piece, coordinates, "SW");
                            break;
                        case 2:
                            scanner.scanPhaseTwo(piece, coordinates, "SW");
                            break;
                    }

                } else {
                    scanner.updatePhase("SW", 0);
                }
            }

            // SE diagonal
            if (scanner.phaseSE > 0) {
                if (inBoardBounds(southOffset, eastOffset)) {
                    Board.Coordinates coordinates = new Board.Coordinates(southOffset, eastOffset);
                    Piece piece = (Piece) boardState.get(coordinates);

                    switch (scanner.phaseSE) {
                        case 1:
                            scanner.scanPhaseOne(piece, coordinates, "SE");
                            break;
                        case 2:
                            scanner.scanPhaseTwo(piece, coordinates, "SE");
                            break;
                    }

                } else {
                    scanner.updatePhase("SE", 0);
                }
            }

            scanner.increaseOffset();
        }


        return getLegalMoves();

    }


    private class LegalMovesScannerDiagonal {
        int phaseNW = 1, phaseNE = 1, phaseSW = 1, phaseSE = 1;

        // Coordinates offset
        int xMod = 1;
        int yMod = 1;

        void increaseOffset() {
            xMod++;
            yMod++;
        }

        int getSouthOffset(int x) {
            return x + xMod;
        }

        int getNorthOffset(int x) {
            return x - xMod;
        }

        int getEastOffset(int y) {
            return y + yMod;
        }

        int getWestOffset(int y) {
            return y - yMod;
        }

        private Piece potentialKingsGuardNW, potentialKingsGuardNE, potentialKingsGuardSW, potentialKingsGuardSE;
        private final Collection<Board.Coordinates> potentialCheckLineNW = new HashSet<>();
        private final Collection<Board.Coordinates> potentialCheckLineNE = new HashSet<>();
        private final Collection<Board.Coordinates> potentialCheckLineSW = new HashSet<>();
        private final Collection<Board.Coordinates> potentialCheckLineSE = new HashSet<>();

        private void setPotentialKingsGuard(Piece piece, String direction) {
            switch (direction) {
                case "NW":
                    potentialKingsGuardNW = piece;
                    break;
                case "NE":
                    potentialKingsGuardNE = piece;
                    break;
                case "SW":
                    potentialKingsGuardSW = piece;
                    break;
                case "SE":
                    potentialKingsGuardSE = piece;
                    break;
            }
        }

        private Piece getPotentialKingsGuard(String direction) {
            switch (direction) {
                case "NW":
                    return potentialKingsGuardNW;
                case "NE":
                    return potentialKingsGuardNE;
                case "SW":
                    return potentialKingsGuardSW;
                case "SE":
                    return potentialKingsGuardSE;
            }
            return null;
        }

        Collection<Board.Coordinates> getPotentialCheckLine(String direction) {
            switch (direction) {
                case "NW":
                    return potentialCheckLineNW;
                case "NE":
                    return potentialCheckLineNE;
                case "SW":
                    return potentialCheckLineSW;
                case "SE":
                    return potentialCheckLineSE;
            }
            return null;
        }

        private void updatePhase(String direction, int phase) {
            switch (direction) {
                case "NW":
                    phaseNW = phase;
                    break;
                case "NE":
                    phaseNE = phase;
                    break;
                case "SW":
                    phaseSW = phase;
                    break;
                case "SE":
                    phaseSE = phase;
                    break;
            }
        }

        int sum() {
            return phaseNW + phaseNE + phaseSE + phaseSW;
        }

        /* Phase One:
         * ...
         */
        void scanPhaseOne(Piece foundPiece, Board.Coordinates coordinates, String direction) {

            // If empty tile, add to legalMoves.
            if (foundPiece == null) {
                addLegalMove(coordinates);
                // TODO: add to potentialAttackCoordinates
                // Add to potentialCheckLine for this scan direction.
                getPotentialCheckLine(direction).add(coordinates);
            } else {
                // If it's the enemy
                if (foundPiece.getPieceTeam() != thisPiece().getPieceTeam()) {
                    if (foundPiece.getPieceType() == PieceType.KING) {
                        // If it's the enemy king.
                        // TODO: put him in check, give a checkLine, give self as assassin and stop
                    } else {
                        // If it's the enemy soldier.
                        // Add his position to your legal moves.
                        addLegalMove(coordinates);

                        // Mark that soldier as potentialKingsGuard for current direction of scan.
                        setPotentialKingsGuard(foundPiece, direction);
                        // Switch to Phase 2, to see if their King lies beyond.
                        updatePhase(direction, 2);
                    }
                } else {
                    // If it's friendly piece
                    // TODO: Add to potentialAttackCoordinates

                    // Stop scanning this direction
                    updatePhase(direction, 0);
                }
            }
        }

        /* Phase Two:
         * Peek behind the enemy piece to see if it's guarding the enemy King.
         */
        void scanPhaseTwo(Piece foundPiece, Board.Coordinates coordinates, String direction) {

            if (foundPiece == null) {
                // If tile is empty
                // Add to potentialCheckLine for this scan direction.
                getPotentialCheckLine(direction).add(coordinates);
                // Peek further.
                return;
            } else {
                if (foundPiece.getPieceType() == PieceType.KING && foundPiece.getPieceTeam() != thisPiece().getPieceTeam()) {
                    // If it's the enemy king.
                    Piece kingsGuard = getPotentialKingsGuard(direction);
                    // TODO: give kingsGuard its guardedCheckLine and this potentialAssassin's own coordinates, for filtering
                    // Stop.
                    updatePhase(direction, 0);
                } else {
                    // If it's anything but the enemy king, stop.
                    updatePhase(direction, 0);
                }
            }
        }


    }

    private Piece thisPiece() {
        return this;
    }

}
