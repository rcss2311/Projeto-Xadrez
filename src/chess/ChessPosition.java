package chess;

import boardgame.Position;

public class ChessPosition {
	//classe que determina a posi��o das pessoas, porem utilisando a logica de caractere para as colunas e numeros paras as linhas
	//aqui � onde � mapeado a informa��o que os jogadores passam durante o jogo
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
	//converte a posi��o para o formato do tabuleiro de chadres, ao inves de usar cordenadas numericas, usa numerica e alfabetica
	protected Position toPosition() {
		return new Position(8-row, colunm - 'a');
	}
	//recebe uma posi��o durante o jogo, e mapeia  a posi��o de onde a pe�a sai
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColum()), 8 - position.getRow());
	}
	@Override
	public String toString() {
		return "" + colunm + row;
	}
	
	

}
