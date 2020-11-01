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
	//classe de implementação da logica do jogo como um todo, tabuleiro e suas regras, partida e suas regras, peças, tudo se rune aqui
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
				System.out.print("Source: "); //escolhe a peça de origem
				ChessPosition source = UI.readChessPosition(sc); //lê a escolha
				
				boolean [][] possibleMoves = chessMatch.possibleMoves(source); //instancia matriz de movimentos possiveis da peça
				UI.clearScreen(); //limpa a tela
				UI.printBoard(chessMatch.getPieces(), possibleMoves); // printa o tabuleiro novamente com a peça marcada e a linha de movimentos
				
				System.out.println();
				System.out.print("Target: "); //escolhe o destino da peça
				ChessPosition target = UI.readChessPosition(sc); // lê a escolha 
				
				ChessPiece capturedPiece = chessMatch.performeChessMove(source, target);// realiza o movimento com as informações da fonte e do destino 
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}//verifica a peça capturada e adiciona na lista de peças capturadas
				
				if(chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while(!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.print("Invalid value. Enter piece for promotion (B/N/R/Q): ");
					    type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
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
