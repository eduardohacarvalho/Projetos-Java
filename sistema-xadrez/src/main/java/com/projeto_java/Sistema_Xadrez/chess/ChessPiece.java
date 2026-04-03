package com.projeto_java.Sistema_Xadrez.chess;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.boardgame.Piece;
import com.projeto_java.Sistema_Xadrez.boardgame.Position;

public abstract class ChessPiece extends Piece {

    private ChessColor chessColor;

    public ChessPiece(Board board, ChessColor chessColor) {
        super(board);
        this.chessColor = chessColor;
    }

    public ChessColor getColor() {
        return chessColor;
    }

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    protected  boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p !=null && p.getColor() != chessColor;
    }

}
