package com.omegaChess;

import java.util.ArrayList;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.pieces.Champion;
import com.omegaChess.pieces.ChessPiece;
import com.omegaChess.pieces.King;
import com.omegaChess.pieces.LegalMoves;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit Champion Class Test")
class TestChampion {

    @Test
    public void testGetColor(){
        ChessBoard board = new ChessBoard();
        Champion champ = new Champion(board, ChessPiece.Color.WHITE);
        assertEquals(ChessPiece.Color.WHITE, champ.getColor(), "Expected color to be white");
    }

    @Test
    public void testGetPosition(){
        ChessBoard board = new ChessBoard();
        Champion champ = new Champion(board, ChessPiece.Color.WHITE);
        board.placePiece(champ, "a4");
        assertEquals("a4", champ.getPosition(), "Piece expected to return \"a4\"");
    }

    @Test
    public void testLegalMoves(){
        ChessBoard board = new ChessBoard();
        Champion champ = new Champion(board, ChessPiece.Color.BLACK);
        King king = new King(board, ChessPiece.Color.BLACK);
        board.placePiece(champ, "c7");
        board.placePiece(king, "j2");
        ArrayList<String> expectedMoves = new ArrayList<>();
        expectedMoves.add("c8"); expectedMoves.add("c9"); expectedMoves.add("c6"); expectedMoves.add("c5");
        expectedMoves.add("d7"); expectedMoves.add("e7"); expectedMoves.add("b7"); expectedMoves.add("a7");
        expectedMoves.add("a9"); expectedMoves.add("e9"); expectedMoves.add("e5"); expectedMoves.add("a5");
        LegalMoves moves = champ.legalMoves(true, false);
        ArrayList<String> actualMoves = moves.getListOfMoves();
        assertEquals(expectedMoves, actualMoves, "Expected moves not provided");
    }

    @Test
    public void testMovesToBlockCheckingPiece() {
        ChessBoard board = new ChessBoard();
        Champion champ = new Champion(board, ChessPiece.Color.WHITE);
        board.placePiece(champ, "f5");
        ArrayList<String> validMoves = new ArrayList<>();

        validMoves.add("f5");
        validMoves.add("f4");
        assertEquals(validMoves, champ.movesToBlockCheckingPiece("f3").getListOfMoves());

        validMoves.clear();
        validMoves.add("f5");
        validMoves.add("g5");
        assertEquals(validMoves, champ.movesToBlockCheckingPiece("h5").getListOfMoves());

        validMoves.clear();
        validMoves.add("f5");
        assertEquals(validMoves, champ.movesToBlockCheckingPiece("h3").getListOfMoves());

        validMoves.clear();
        validMoves.add("f5");
        assertEquals(validMoves, champ.movesToBlockCheckingPiece("h7").getListOfMoves());
    }
}