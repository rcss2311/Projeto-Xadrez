package boardgame;

public class Board {
	//classe tabuleiro possui linhas, colunas e pe�as ou locais onde estaram dispostas as pe�as do jogo e por onde se movimentar�o
	//as linhas e colunas representadas aqui s�o "pe�as" que representam posi��es no tabuleiro, ou seja, � como se cada quadrado
	//fosse uma pe�a que recebe a pe�a de xadrez em cima 
	private Integer rows;
	private Integer coluns;
	private Piece  pieces[][];
	
	public Board() {
		
	} 
	public Board(Integer rows, Integer coluns) {
		//implementa��o defensiva, checando se a condi��o de existencia do tabuleiro � possivel
		if(rows <= 0 || coluns <=0) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 colunm");
		}
		this.rows = rows;
		this.coluns = coluns;
		//a matriz, apesar de estar listado dentro do construtor, ela n�o recebe parametro externo. Instancia um nova matriz usando
		//as infoma��es de linhas e colunas.
		pieces = new Piece[rows][coluns];
	}
	public Integer getRows() {
		return rows;
	}
	public Integer getColuns() {
		return coluns;
	}
	//checa se a posi��o existe no tabuleiro, caso exista, retorna a linha e coluna. 
	public Piece piece(int row, int colum) {
		if(!positionExist(row, colum)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][colum];
	}
	//checa a posi��o existe no tabuleiro e retorna a posi��o
	public Piece piece(Position position) {
		if(!positioExist(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColum()];
	}
	//checa se a pe�a que foi movida, que passou os parametros para o metodo, pode se mover para a posi��o informada
	//caso n�o exista nenhum impedimento, a pe�a que veio de parametro assume a posi��o da pe�a no tabuleiro 
	public void placePiece(Piece piece, Position position) {
		if(thereIsaPiece(position)) {
			throw new BoardException("There is alrady a piece on position "+ position);
		}
		pieces[position.getRow()][position.getColum()] = piece;
		piece.position = position;
	}
	//aqui � o que acontece, quando uma pe�a de uma cor, encontra a pe�a oposta, removendo do tabuleiro
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
	// retorna valor booleano para existecia da posi��o no tabuleiro
	public boolean positionExist(int row, int colun) {
		return row >= 0 && row < rows && colun >= 0 && colun < coluns;
	}
	//sobrecarga do metodo anterior que verifica valor booleano retornado no metodo anterior, caso ele atenda a condi�ao de verdadeiro,
	//linhas e colunas, mas dessa vez pegando a posi��o passada durante a partida
	public boolean positioExist(Position position) {
		return positionExist(position.getRow(), position.getColum());
	}
	//verifica se existe uma pe�a naquela posi��o, vai influenciar na movimenta��o da pe�a caso seja amiga ou inimiga
	public boolean thereIsaPiece(Position position) {
		if(!positioExist(position)) {
			throw new BoardException("Position not on the board!");
		}
		return piece(position) != null;
	}

}
