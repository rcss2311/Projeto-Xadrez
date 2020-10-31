package boardgame;

public abstract class Piece {
	//classe abstrata que pertence a tabuleiro e ser� usada posteriomente para mapear as pe�as no tabuleiro
	//duas rela��es, com tabuleiro e com a posi��o, usando duas variaveis do tipo das classes
	protected Position position;
	private Board board;
	
	public Piece() {}

	public Piece(Board board) {
		
		position = null;
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}
	
	//metodo abstrato para mapear os movementos possiveis de cada pe�a
	public abstract boolean[][] possibleMoves();
	
	//metodo concreto, que so � possivel nessa classe, pois retorna o metodo abstrato possible moves
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColum()];
	}
	
	//logica para mapear os mobvementos possiveis de uma pe�a, porem de forma generica, apenas diz se existe movimento possivel
	public boolean isThereAnyPossibleMoves() {
		boolean[][] mat =  possibleMoves();
			for(int i = 0; i<mat.length;i++) {
				for(int j = 0; j < mat.length; j++) {
					if(mat[i][j]) {
						return true;
					}
				}
			}
			return false;
	}
}
