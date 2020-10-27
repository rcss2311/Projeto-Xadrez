package boardgame;

public class Board {
	
	private Integer rows;
	private Integer coluns;
	private Piece  pieces[][];
	
	public Board() {
		
	} 
	public Board(Integer rows, Integer coluns) {
		if(rows <= 0 || coluns <=0) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 colunm");
		}
		this.rows = rows;
		this.coluns = coluns;
		pieces = new Piece[rows][coluns];
	}
	public Integer getRows() {
		return rows;
	}
	public Integer getColuns() {
		return coluns;
	}
	
	public Piece piece(int row, int colum) {
		if(!positionExist(row, colum)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][colum];
	}
	
	public Piece piece(Position position) {
		if(!positioExist(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColum()];
	}
	public void placePiece(Piece piece, Position position) {
		if(thereIsaPiece(position)) {
			throw new BoardException("There is alrady a piece on position "+ position);
		}
		pieces[position.getRow()][position.getColum()] = piece;
		piece.position = position;
	}
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
	
	public boolean positionExist(int row, int colun) {
		return row >= 0 && row < rows && colun >= 0 && colun < coluns;
	}
	public boolean positioExist(Position position) {
		return positionExist(position.getRow(), position.getColum());
	}
	public boolean thereIsaPiece(Position position) {
		if(!positioExist(position)) {
			throw new BoardException("Position not on the board!");
		}
		return piece(position) != null;
	}

}
