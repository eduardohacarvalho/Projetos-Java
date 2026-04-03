package com.projeto_java.Sistema_Xadrez.pieces;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.boardgame.Position;
import com.projeto_java.Sistema_Xadrez.chess.ChessColor;
import com.projeto_java.Sistema_Xadrez.chess.ChessPiece;

// Pawn = Peão
public class Pawn extends ChessPiece {
    public Pawn(Board board, ChessColor color) { // Alterado para ChessColor
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        //para cima
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) ){
            mat[p.getRow()][p.getColumn()] = true;
        }


        // para cima e direita
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // para cima e esquerda
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}