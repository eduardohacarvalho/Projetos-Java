package com.projeto_java.Sistema_Xadrez;


import com.projeto_java.Sistema_Xadrez.chess.ChessMatch;

public class SistemaXadrezApplication {

	public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
	}

}
