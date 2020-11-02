package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	//classe, ainda abstrata porem ja indica no jogo que � uma pe�a de xadrez
	//que herda a classe Pe�a do pacote boardgame que determina cor e posi��o no tabuleiro 
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
	
	// metodo para pegar a posi��o de uma pe�a pegando a posi��o e retornando no formato de tabuleiro
	//a inform��o da posi��o vem da classe Piece, do pacote BoardGame, cuja classe recebe a heran�a 
	//faz parte da logica do check
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//testa a cor da pe�a na posi��o para ver se � ou n�o a pe�a do oponente e retorna um valor booleano
	protected 	boolean isThereOponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
		
	}

}
