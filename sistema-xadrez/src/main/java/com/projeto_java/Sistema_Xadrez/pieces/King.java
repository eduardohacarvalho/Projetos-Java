package com.projeto_java.Sistema_Xadrez.pieces;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.boardgame.Position;
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
        Position p = new Position(0, 0);

        //para cima
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //para baixo
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //para esquerda
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // para direita
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}