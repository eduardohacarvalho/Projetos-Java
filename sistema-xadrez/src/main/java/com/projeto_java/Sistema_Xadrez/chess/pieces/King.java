package com.projeto_java.Sistema_Xadrez.chess.pieces;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.boardgame.Position;
import com.projeto_java.Sistema_Xadrez.chess.ChessMatch;
import com.projeto_java.Sistema_Xadrez.chess.ChessPiece;
import com.projeto_java.Sistema_Xadrez.chess.ChessColor; // Import correto da sua cor

// King = Rei
public class King extends ChessPiece {
    private ChessMatch chessMatch;

    public King(Board board, ChessColor color, ChessMatch chessMatch) { // Alterado para ChessColor
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }
    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        //para cima
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //para baixo
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //para esquerda
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // para direita
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // para cima e direita
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // para baixo e direita
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // para cima e esquerda
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // para baixo e esquerda
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // SpecialMove Castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()){
            //kingside rook
            Position posT1 = new Position(position.getRow(), position.getColumn()+3);
            if(testRookCastling(posT1)){
                Position p1 = new Position(position.getRow(), position.getColumn()+2);
                Position p2 = new Position(position.getRow(), position.getColumn()+1);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getColumn()+2] = true;
                }
            }
            Position posT2 = new Position(position.getRow(), position.getColumn()-4);
            if(testRookCastling(posT1)){
                Position p1 = new Position(position.getRow(), position.getColumn()-3);
                Position p2 = new Position(position.getRow(), position.getColumn()-2);
                Position p3 = new Position(position.getRow(), position.getColumn()-3);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    mat[position.getRow()][position.getColumn()-2] = true;
                }
            }

        }
        return mat;
    }
}