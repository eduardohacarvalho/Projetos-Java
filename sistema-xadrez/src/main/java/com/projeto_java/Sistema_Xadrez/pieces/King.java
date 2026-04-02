package com.projeto_java.Sistema_Xadrez.pieces;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.chess.ChessPiece;

import java.awt.*;

// King = Rei
public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }
    
    @Override
    public String toString() {
        return "R";
    }
}
