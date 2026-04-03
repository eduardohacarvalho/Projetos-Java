package com.projeto_java.Sistema_Xadrez.pieces;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.chess.ChessPiece;
import com.projeto_java.Sistema_Xadrez.chess.ChessColor; // Import correto da sua cor

// King = Rei
public class King extends ChessPiece {
    public King(Board board, ChessColor color) { // Alterado para ChessColor
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}