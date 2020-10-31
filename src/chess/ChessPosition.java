package chess;

import boardgame.Position;

public class ChessPosition {
	//classe que determina a posição das pessoas, porem utilisando a logica de caractere para as colunas e numeros paras as linhas
	//aqui é onde é mapeado a informação que os jogadores passam durante o jogo
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
	//converte a posição para o formato do tabuleiro de chadres, ao inves de usar cordenadas numericas, usa numerica e alfabetica
	protected Position toPosition() {
		return new Position(8-row, colunm - 'a');
	}
	//recebe uma posição durante o jogo, e mapeia  a posição de onde a peça sai
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColum()), 8 - position.getRow());
	}
	@Override
	public String toString() {
		return "" + colunm + row;
	}
	
	

}
