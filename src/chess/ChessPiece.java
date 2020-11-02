package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	//classe, ainda abstrata porem ja indica no jogo que é uma peça de xadrez
	//que herda a classe Peça do pacote boardgame que determina cor e posição no tabuleiro 
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;	
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	// metodo para pegar a posição de uma peça pegando a posição e retornando no formato de tabuleiro
	//a informção da posição vem da classe Piece, do pacote BoardGame, cuja classe recebe a herança 
	//faz parte da logica do check
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//testa a cor da peça na posição para ver se é ou não a peça do oponente e retorna um valor booleano
	protected 	boolean isThereOponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
		
	}

}
