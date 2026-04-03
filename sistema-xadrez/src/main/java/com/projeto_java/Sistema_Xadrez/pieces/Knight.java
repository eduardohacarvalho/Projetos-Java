package com.projeto_java.Sistema_Xadrez.pieces;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.boardgame.Position;
import com.projeto_java.Sistema_Xadrez.chess.ChessColor;
import com.projeto_java.Sistema_Xadrez.chess.ChessPiece;

// Knight = Cavalo
public class Knight extends ChessPiece {

    public Knight(Board board, ChessColor color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "C";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);


        p.setValues(position.getRow() - 1, position.getColumn() - 2);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() - 2, position.getColumn() - 1);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() - 2, position.getColumn() + 1);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() - 1, position.getColumn() + 2);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() + 1, position.getColumn() + 2);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() + 2, position.getColumn() + 1);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() + 2, position.getColumn() - 1);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValues(position.getRow() + 1, position.getColumn() - 2);
        if (getBoard().positionExists(p) && ( !getBoard().thereIsAPiece(p) || isThereOpponentPiece(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}