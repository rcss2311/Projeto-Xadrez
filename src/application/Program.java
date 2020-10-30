package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
	//classe de implementa��o da logica do jogo como um todo, tabuleiro e suas regras, partida e suas regras, pe�as, tudo se rune aqui
	//onde a magica acontece
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List <ChessPiece> captured = new ArrayList<>(); 
		
		while(!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen(); // limpa tela
				UI.printMatch(chessMatch,captured); //inicia a partida
				System.out.println();
				System.out.print("Source: "); //escolhe a pe�a de origem
				ChessPosition source = UI.readChessPosition(sc); //l� a escolha
				
				boolean [][] possibleMoves = chessMatch.possibleMoves(source); //instancia matriz de movimentos possiveis da pe�a
				UI.clearScreen(); //limpa a tela
				UI.printBoard(chessMatch.getPieces(), possibleMoves); // printa o tabuleiro novamente com a pe�a marcada e a linha de movimentos
				
				System.out.println();
				System.out.print("Target: "); //escolhe o destino da pe�a
				ChessPosition target = UI.readChessPosition(sc); // l� a escolha 
				
				ChessPiece capturedPiece = chessMatch.performeChessMove(source, target);// realiza o movimento com as informa��es da fonte e do destino 
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}//verifica a pe�a capturada e adiciona na lista de pe�as capturadas
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}

}
