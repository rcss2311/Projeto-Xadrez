package chess;

import boardgame.Position;

public class ChessPosition {
	//Classe que converte as cordenadas de jogo em cordenadas de matriz 
	private char colunm;
	private int row;
	public ChessPosition(char colunm, int row) {
		if( colunm < 'a' || colunm >'h' || row < 0 || row > 8) {
			throw new ChessException("Error instantiate chees position. Valid values are from a1 to h8 ");
		}
		this.colunm = colunm;
		this.row = row;
	}
	public char getColunm() {
		return colunm;
	}
	public int getRow() {
		return row;
	}
	//converte a posi��o no formato de tabuleiro, para o formato de posi��o normal
	protected Position toPosition() {
		return new Position(8-row, colunm - 'a');//convers�o automatica de char em int
	}
	//convetre a posi��o no formato normal de matriz, para o formato de tabuleiro
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColum()), 8 - position.getRow());//casting da posi��o de matriz, int, para char
	}																					   //retorna uma posi��o no tabuleiro
	@Override
	public String toString() {
		return "" + colunm + row;
	}
	
	

}
