package com.projeto_java.Sistema_Xadrez.boardgame;

public class Board {
    private Integer rows;
    private Integer columns;
    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        if (rows < 1 || columns < 1){
            throw new BoardException("ERRO: Não é possível criar o tabuleiro com menos que 1 linha e 1 coluna.");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public Piece piece (int row, int column){
        if (!positionExists(row, column)){
            throw new BoardException("ERRO: Posição não está no tabuleiro");
        }
        return pieces[row][column];
    }
    public Piece piece (Position position){
        if (!positionExists(position)){
            throw new BoardException("ERRO: Posição não está no tabuleiro");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public Piece removePiece (Position position){
        if (!positionExists(position)){
            throw new BoardException("ERRO: Posição não está no tabuleiro");
        }
        if (piece(position) == null){
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    public void placePiece(Piece piece, Position position){
        if (thereIsAPiece(position)){
            throw new BoardException("ERRO: Já tem uma peça na posição " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    private boolean positionExists(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    private boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if (!positionExists(position)){
            throw new BoardException("ERRO: Posição não está no tabuleiro");
        }
        return piece(position) != null;
    }
}
