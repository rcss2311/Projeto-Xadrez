package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	//classe applicativa que implemnte a interface do usuatrio

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	//limpa a tela apos um movimento
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		}
	//faz a leitura das posições das peças no jogo
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			 String s = sc.nextLine();
			 char colunm = s.charAt(0);
			 int row = Integer.parseInt(s.substring(1));
			 return new ChessPosition(colunm, row);
		}
		catch(RuntimeException e) {
			throw new InputMismatchException("Error instantiate chees position. Valid values are from a1 to h8");
		}
	}
	//inicia a partida indicando placar de peças capturadas(metodo printCapturedPieces), o turno, e quem inicia a partida
	//através do metodo chessMatch.getTurn e chessMatch.getCurrencyPlayer, respectivamente
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces()); // printa a primeira versão do tabuleiro
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn: "+ chessMatch.getTurn());
		if(!chessMatch.getCheckMate()) {
			System.out.println("Wait player: "+chessMatch.getCurrentPlayer());
			if(chessMatch.getCheck()) {
				System.out.println("You are in CHECk!");
			}
		}else {
			System.out.println("CHEKMATE!");
			System.out.println("Winner: "+chessMatch.getCurrentPlayer());
		}
	}
	
//imprime a disposição do tabuleiro
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	//printa na inteface do usuario o tabuleiro com linhas numericas e colunas alfabeticas, agora com os movimentos possiveis
	public static void printBoard(ChessPiece[][] pieces, boolean [][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j],possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
//imprime as peças e suas peculiaridades no jogo. alem do caminho que a peça pode fazer no tabuleiro
	private static void printPiece(ChessPiece piece, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
			
		}
    	if (piece == null) {
            System.out.print("-"+ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	//interface do placar de captura de peças brancas e pretas(nesse caso as pretas são amarelas, pois se não estariam da mesma cor do bg) 
	private static void printCapturedPieces(List<ChessPiece> capturedPiece) {
		List <ChessPiece> white = capturedPiece.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List <ChessPiece> black = capturedPiece.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Captured pieces: ");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.println("Captured pieces: ");
		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.println(ANSI_RESET);
		
	}
}
