package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class Pawn extends ChessPiece {
    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        // TODO Auto-generated constructor stub
    }

    /*
     * This is an abstract function that will be implemented in the
     * concrete subclasses corresponding to each chess piece. It returns
     * a one character String that corresponds to the type of the piece. In
     * the Unicode6 character encoding scheme, there are characters that represet
     * each chess piece.
     */
    public String toString()
    {
        if( this.color == Color.BLACK)
        {
            return "\u265F";
        }
        else if( this.color == Color.WHITE )
        {
            return "\u2659";
        }

        return "";
    }

    /*
     * To be implemented in the concrete subclasses corresponding to each
     * chess piece. This method returns all the legal moves that a piece
     * can make based on the rules described above. Each string in the ArrayList
     * should be the position of a possible destination for the piece (in the same
     * format described above). If there are multiple legal moves, the order of
     * moves in the ArrayList does not matter. If there are no legal moves, return
     * return an empty ArrayList, i.e., the size should be zero.
     */
    public ArrayList<String> legalMoves()
    {
        ArrayList<String> validMoves = new ArrayList<String>();

        ChessPiece p1 = null;
        String p1_str = board.reverseParse(row+1, column);

        try {
            p1 = board.getPiece(p1_str);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        // if the spot 1 forward to move is null, add it to legal moves
        if( p1 == null)
        {
            validMoves.add(p1_str);
        }

        if( column > 0 )
        {
            ChessPiece diagLeft = null;
            String diagLeft_str = board.reverseParse(row+1, column-1);

            try {
                diagLeft = board.getPiece(diagLeft_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if a chess piece is here and it is an opponent's piece
            if( diagLeft != null && diagLeft.getColor() != this.color )
            {
                validMoves.add(diagLeft_str);
            }
        }

        if( column < 7 )
        {
            ChessPiece diagRight = null;
            String diagRight_str = board.reverseParse(row+1, column+1);

            try {
                diagRight = board.getPiece(diagRight_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if a chess piece is here and it is an opponent's piece
            if( diagRight != null && diagRight.getColor() != this.color )
            {
                validMoves.add(diagRight_str);
            }
        }

        // a pawn is in the initial position if it is in row 2
        if( row == 2 )
        {
            // pawn in initial can move 1 or 2 squares vertically forward to an empty
            // square but cannot leap over anything
            ChessPiece p2 = null;
            String p2_str = board.reverseParse(row+2, column);

            try {
                p2 = board.getPiece(p2_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if the spot 1 forward and the spot 2 forward is null, add it to legal move
            if( p1 == null && p2 == null )
            {
                validMoves.add(p2_str);
            }
        }

        return validMoves;
    }
}
