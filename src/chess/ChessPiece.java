package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	//classe, ainda abstrata, que herda a classe Peça do pacote boardgame que determina cor e posição no tabuleiro 
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	// metodo para retirnar a posição de uma peça pegando a posição e retornando 
	//faz parte da logica do check
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected 	boolean isThereOponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
		
	}

}
