package boardgame;

public class Board {
	//classe tabuleiro possui linhas, colunas e peças ou locais onde estaram dispostas as peças do jogo e por onde se movimentarão
	//as linhas e colunas representadas aqui são "peças" que representam posições no tabuleiro, ou seja, é como se cada quadrado
	//fosse uma peça que recebe a peça de xadrez em cima 
	private Integer rows;
	private Integer coluns;
	private Piece  pieces[][];
	
	public Board() {
		
	} 
	public Board(Integer rows, Integer coluns) {
		//implementação defensiva, checando se a condição de existencia do tabuleiro é possivel
		if(rows <= 0 || coluns <=0) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 colunm");
		}
		this.rows = rows;
		this.coluns = coluns;
		//a matriz, apesar de estar listado dentro do construtor, ela não recebe parametro externo. Instancia um nova matriz usando
		//as infomações de linhas e colunas.
		pieces = new Piece[rows][coluns];
	}
	public Integer getRows() {
		return rows;
	}
	public Integer getColuns() {
		return coluns;
	}
	//checa se a posição existe no tabuleiro, caso exista, retorna a linha e coluna. 
	public Piece piece(int row, int colum) {
		if(!positionExist(row, colum)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][colum];
	}
	//checa a posição existe no tabuleiro e retorna a posição
	public Piece piece(Position position) {
		if(!positioExist(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColum()];
	}
	//checa se a peça que foi movida, que passou os parametros para o metodo, pode se mover para a posição informada
	//caso não exista nenhum impedimento, a peça que veio de parametro assume a posição da peça no tabuleiro 
	public void placePiece(Piece piece, Position position) {
		if(thereIsaPiece(position)) {
			throw new BoardException("There is alrady a piece on position "+ position);
		}
		pieces[position.getRow()][position.getColum()] = piece;
		piece.position = position;
	}
	//aqui é o que acontece, quando uma peça de uma cor, encontra a peça oposta, removendo do tabuleiro
	public Piece removePiece(Position position) {
		if(!positioExist(position)) {
			throw new BoardException("Position not in the board!");
		}
		if(piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColum()] = null;
		return aux;
	}
	// retorna valor booleano para existecia da posição no tabuleiro
	public boolean positionExist(int row, int colun) {
		return row >= 0 && row < rows && colun >= 0 && colun < coluns;
	}
	//sobrecarga do metodo anterior que verifica valor booleano retornado no metodo anterior, caso ele atenda a condiçao de verdadeiro,
	//linhas e colunas, mas dessa vez pegando a posição passada durante a partida
	public boolean positioExist(Position position) {
		return positionExist(position.getRow(), position.getColum());
	}
	//verifica se existe uma peça naquela posição, vai influenciar na movimentação da peça caso seja amiga ou inimiga
	public boolean thereIsaPiece(Position position) {
		if(!positioExist(position)) {
			throw new BoardException("Position not on the board!");
		}
		return piece(position) != null;
	}

}
