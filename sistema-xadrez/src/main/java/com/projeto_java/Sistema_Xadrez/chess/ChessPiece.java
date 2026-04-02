package com.projeto_java.Sistema_Xadrez.chess;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.boardgame.Piece;

public class ChessPiece extends Piece {

    private ChessColor chessColor;

    public ChessPiece(Board board, ChessColor chessColor) {
        super(board);
        this.chessColor = chessColor;
    }

    public ChessColor getColor() {
        return chessColor;
    }
}
