package com.omegaChess.pieces;

import com.omegaChess.board.ChessBoard;
import com.omegaChess.exceptions.IllegalPositionException;

import java.util.ArrayList;

public class Bishop extends ChessPiece {
    public Bishop(ChessBoard board, Color color) {
        super(board, color);
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
            return "\u265D";
        }
        else if( this.color == Color.WHITE )
        {
            return "\u2657";
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

        // rook can move any number of squares forward, backward, horiz or vert if
        // it does not encounter other pieces

        // handle forward up diag pieces
        int tmp_row = row+1;
        int tmp_col = column+1;
        ChessPiece tmp_piece = null;
        String tmp_str = board.reverseParse(tmp_row, tmp_col);

        while( tmp_row < 8 && tmp_col < 8 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                tmp_row = 9;
            }

            tmp_row += 1;
            tmp_col += 1;
            tmp_str = board.reverseParse(tmp_row, tmp_col);
        }

        // handle forwards down diag pieces
        tmp_row = row-1;
        tmp_col = column +1;
        tmp_piece = null;
        tmp_str = board.reverseParse(tmp_row, tmp_col);

        while( tmp_row > -1 && tmp_col < 8 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                tmp_row = -1;
            }

            tmp_row -= 1;
            tmp_col += 1;
            tmp_str = board.reverseParse(tmp_row, tmp_col);
        }

        // handle backwards diag up pieces
        tmp_col = column-1;
        tmp_row = row+1;
        tmp_piece = null;
        tmp_str = board.reverseParse(tmp_row, tmp_col);

        while( tmp_col > -1 && tmp_row < 8 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                tmp_col = 9;
            }

            tmp_col -= 1;
            tmp_row += 1;
            tmp_str = board.reverseParse(tmp_row, tmp_col);

        }

        // handle backward diag down pieces
        tmp_col = column-1;
        tmp_row = row-1;
        tmp_piece = null;
        tmp_str = board.reverseParse(tmp_row, tmp_col);

        while( tmp_col > -1 && tmp_row > -1 && tmp_piece == null )
        {
            try {
                tmp_piece = board.getPiece(tmp_str);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }

            // if empty - legal move
            if( tmp_piece == null )
            {
                validMoves.add(tmp_str);
            }
            // if opponent piece - legal move but can't move in this direction anymore
            else if( tmp_piece.getColor() != this.color )
            {
                validMoves.add(tmp_str);
                tmp_col = -1;
            }

            tmp_col -= 1;
            tmp_row -= 1;
            tmp_str = board.reverseParse(tmp_row, tmp_col);

        }

        return validMoves;
    }
}