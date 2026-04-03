package com.projeto_java.Sistema_Xadrez.chess;

import com.projeto_java.Sistema_Xadrez.boardgame.Board;
import com.projeto_java.Sistema_Xadrez.boardgame.Piece;
import com.projeto_java.Sistema_Xadrez.boardgame.Position;
import com.projeto_java.Sistema_Xadrez.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ChessMatch {
    private int turn;
    private ChessColor currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = ChessColor.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public ChessColor getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("Você não pode colocar seu rei em cheque.");
        }
        ChessPiece movedPiece = (ChessPiece)board.piece(target);

        check = (testCheck(opponent(currentPlayer))) ? true : false;
       if (testCheckMate(opponent(currentPlayer))){
           checkMate = true;
       } else {
           nextTurn();
       }
       // Special Move en passant
        if(movedPiece instanceof  Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)){
            enPassantVulnerable = movedPiece;
        } else {
            enPassantVulnerable = null;
        }
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        // SpecialMove Castling kingside rook - king side
        if(p instanceof King && target.getColumn() == source.getColumn() + 2 ){
           Position sourceT = new Position(source.getRow(), source.getColumn()+3);
           Position targetT = new Position(source.getRow(), source.getColumn()+1);
           ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
           board.placePiece(rook, targetT);
           rook.increaseMoveCount();
        }
        // SpecialMove Castling kingside rook - queen side
        if(p instanceof King && target.getColumn() == source.getColumn() - 2 ){
            Position sourceT = new Position(source.getRow(), source.getColumn()-4);
            Position targetT = new Position(source.getRow(), source.getColumn()-1);
            ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
            board.placePiece(rook, targetT);
            rook.increaseMoveCount();
        }
        // Special Move enPassant
        if (source.getColumn() != target.getColumn() && capturedPiece == null){
            Position pawnPosition;
            if (p.getColor() == ChessColor.WHITE){
                pawnPosition = new Position(target.getRow()+1, target.getColumn());
            } else {
                pawnPosition = new Position(target.getRow()-1, target.getColumn());
            }
            capturedPiece = board.removePiece(pawnPosition);
            capturedPieces.add(capturedPiece);
            piecesOnTheBoard.remove(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
        // SpecialMove Castling kingside rook - king side
        if(p instanceof King && target.getColumn() == source.getColumn() + 2 ){
            Position sourceT = new Position(source.getRow(), source.getColumn()+3);
            Position targetT = new Position(source.getRow(), source.getColumn()+1);
            ChessPiece rook = (ChessPiece)board.removePiece(targetT);
            board.placePiece(rook, sourceT);
            rook.decreaseMoveCount();
        }
        // SpecialMove Castling kingside rook - queen side
        if(p instanceof King && target.getColumn() == source.getColumn() - 2 ){
            Position sourceT = new Position(source.getRow(), source.getColumn()-4);
            Position targetT = new Position(source.getRow(), source.getColumn()-1);
            ChessPiece rook = (ChessPiece)board.removePiece(targetT);
            board.placePiece(rook, sourceT);
            rook.decreaseMoveCount();
        }
        // Special Move enPassant
        if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable){
            ChessPiece pawn = (ChessPiece)board.removePiece(target);
            Position pawnPosition;
            if (p.getColor() == ChessColor.WHITE){
                pawnPosition = new Position(3, target.getColumn());
            } else {
                pawnPosition = new Position(4, target.getColumn());
            }
            board.placePiece(pawn, pawnPosition);
            
        }

    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("Não existe peça na posição de origem.");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("A peça escolhida não é sua.");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Não existe movimento possível para peça");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("A peça escolhida não pode ser movida para o destino escolhido");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == ChessColor.WHITE) ? ChessColor.BLACK : ChessColor.WHITE;
    }

    private ChessColor opponent(ChessColor color) {
        return (color == ChessColor.WHITE) ? ChessColor.BLACK : ChessColor.WHITE;
    }

    private ChessPiece king(ChessColor color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("O rei da cor " + color + " não está no tabuleiro.");
    }

    private boolean testCheck(ChessColor color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(ChessColor color) {
        if (!testCheck(color)){
            return false;
        }
        List<Piece> pieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : pieces){
            boolean[][] mat = p.possibleMoves();
            for (int i=0; i< board.getRows(); i++){
                for (int j=0; j< board.getRows(); j++){
                    if (mat[i][j]){
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, ChessColor.WHITE));
        placeNewPiece('b', 1, new Knight(board, ChessColor.WHITE));
        placeNewPiece('c', 1, new Bishop(board, ChessColor.WHITE));
        placeNewPiece('d', 1, new Queen(board, ChessColor.WHITE));
        placeNewPiece('e', 1, new King(board, ChessColor.WHITE, this));
        placeNewPiece('f', 1, new Bishop(board, ChessColor.WHITE));
        placeNewPiece('g', 1, new Knight(board, ChessColor.WHITE));
        placeNewPiece('h', 1, new Rook(board, ChessColor.WHITE));
        placeNewPiece('a', 2, new Pawn(board, ChessColor.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, ChessColor.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, ChessColor.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, ChessColor.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, ChessColor.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, ChessColor.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, ChessColor.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, ChessColor.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, ChessColor.BLACK));
        placeNewPiece('b', 8, new Knight(board, ChessColor.BLACK));
        placeNewPiece('c', 8, new Bishop(board, ChessColor.BLACK));
        placeNewPiece('d', 8, new Queen(board, ChessColor.BLACK));
        placeNewPiece('e', 8, new King(board, ChessColor.BLACK, this));
        placeNewPiece('f', 8, new Bishop(board, ChessColor.BLACK));
        placeNewPiece('g', 8, new Knight(board, ChessColor.BLACK));
        placeNewPiece('h', 8, new Rook(board, ChessColor.BLACK));
        placeNewPiece('a', 7, new Pawn(board, ChessColor.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, ChessColor.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, ChessColor.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, ChessColor.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, ChessColor.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, ChessColor.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, ChessColor.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, ChessColor.BLACK, this));    }
}