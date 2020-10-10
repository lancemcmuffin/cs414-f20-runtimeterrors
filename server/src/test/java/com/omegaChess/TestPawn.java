package com.omegaChess;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.Knight;
import com.omegaChess.pieces.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit Pawn Class Test")
class TestPawn {

    @Test
    void test_createInstance() {
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);

        assertNotNull(pawn);
    }

    @Test
    void test_getColor() {
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);

        assertEquals(pawn.getColor(), ChessPiece.Color.BLACK);
    }

    @Test
    void test_position() {
        // test_position test both getPosition and setPosition
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);

        try {
            pawn.setPosition("e3");
        } catch (IllegalPositionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("e3", pawn.getPosition());
    }

    @Test
    void test_toString() {
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.BLACK);

        assertEquals("\u265F", pawn.toString());

        Pawn pawn_w = new Pawn(board, ChessPiece.Color.WHITE);

        assertEquals("\u2659", pawn_w.toString());
    }

    @Test
    void test_legalMoves() {
        ChessBoard board = new ChessBoard();

        Pawn pawn = new Pawn(board, ChessPiece.Color.WHITE);
        Knight knight = new Knight(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "c2");

        // test 1 from initial position - no blocking pieces
        ArrayList<String> validMoves = new ArrayList<String>();
        validMoves.add("c3");
        validMoves.add("c4");

        ArrayList<String> pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 2 from initial position - yes blocking pieces
        board.placePiece(knight, "c4");

        validMoves.clear();
        validMoves.add("c3");

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 3 from initial position - yes both blocking pieces
        board.placePiece(knight, "c3");

        validMoves.clear();

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 4 from initial position - piece diagonal left available for capture
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);
        knight = new Knight(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "c2");
        board.placePiece(knight, "b3");

        validMoves.clear();
        validMoves.add("b3");
        validMoves.add("c3");
        validMoves.add("c4");

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);


        // test 5 from initial position - piece diagonal right available for capture
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.BLACK);
        knight = new Knight(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c9");
        board.placePiece(knight, "d8");

        validMoves.clear();
        validMoves.add("c8");
        validMoves.add("c7");
        validMoves.add("d8");

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 6 from non initial position - no blocking pieces
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "c7");

        validMoves.clear();
        validMoves.add("c6");

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 7 from non initial position - yes blocking pieces
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);
        knight = new Knight(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "c5");

        validMoves.clear();

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 8 from non initial position - no blocking and yes diag left
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);
        knight = new Knight(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "b5");

        validMoves.clear();
        validMoves.add("c5");
        validMoves.add("b5");

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 9 from non initial position - yes blocking and yes diag right
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);
        knight = new Knight(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "c5");
        board.placePiece(knight, "d5");

        validMoves.clear();
        validMoves.add("d5");

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 10 - non initial position - yes blocking and same color piece diag left
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);
        knight = new Knight(board, ChessPiece.Color.WHITE);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "c5");
        board.placePiece(knight, "b5");

        validMoves.clear();

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

        // test 11 - non initial position, yes blocking and both diags available
        board = new ChessBoard();

        pawn = new Pawn(board, ChessPiece.Color.WHITE);
        knight = new Knight(board, ChessPiece.Color.BLACK);

        board.placePiece(pawn, "c4");
        board.placePiece(knight, "b5");
        board.placePiece(knight, "d5");
        board.placePiece(knight, "c5");

        validMoves.clear();
        validMoves.add("d5");
        validMoves.add("b5");

        pawnValid = pawn.legalMoves();
        Collections.sort(validMoves);
        Collections.sort(pawnValid);

        assertEquals(validMoves, pawnValid);

    }

}
