package com.projeto_java.Sistema_Xadrez;


import com.projeto_java.Sistema_Xadrez.chess.ChessMatch;
import com.projeto_java.Sistema_Xadrez.chess.ChessPiece;
import com.projeto_java.Sistema_Xadrez.chess.ChessPosition;

import java.util.Scanner;

public class SistemaXadrezApplication {

	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());

        while (true) {
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(sc);

            System.out.println();
            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(sc);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
        }
	}

}
