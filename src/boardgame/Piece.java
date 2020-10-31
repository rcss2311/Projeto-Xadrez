package boardgame;

public abstract class Piece {
	//classe abstrata que pertence a tabuleiro e será usada posteriomente para mapear as peças no tabuleiro
	//duas relações, com tabuleiro e com a posição, usando duas variaveis do tipo das classes
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
	
	//metodo abstrato para mapear os movementos possiveis de cada peça
	public abstract boolean[][] possibleMoves();
	
	//metodo concreto, que so é possivel nessa classe, pois retorna o metodo abstrato possible moves
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColum()];
	}
	
	//logica para mapear os mobvementos possiveis de uma peça, porem de forma generica, apenas diz se existe movimento possivel
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
