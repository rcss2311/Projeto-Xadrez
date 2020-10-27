package boardgame;

public class Board {
	
	private Integer rows;
	private Integer coluns;
	private Piece  pieces[][];
	
	public Board() {
		
	} 
	public Board(Integer rows, Integer coluns) {
		this.rows = rows;
		this.coluns = coluns;
		pieces = new Piece[rows][coluns];
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getColuns() {
		return coluns;
	}
	public void setColuns(Integer coluns) {
		this.coluns = coluns;
	}
	
	

}
