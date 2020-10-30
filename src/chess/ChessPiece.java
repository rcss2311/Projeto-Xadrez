package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	//classe, ainda abstrata, que herda a classe Pe�a do pacote boardgame que determina cor e posi��o no tabuleiro 
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	// metodo para retirnar a posi��o de uma pe�a pegando a posi��o e retornando 
	//faz parte da logica do check
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected 	boolean isThereOponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
		
	}

}
