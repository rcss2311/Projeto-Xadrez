package boardgame;

public class Position {
	//clase para mapeamento de posição das peças no tabuleiro
	private Integer row;
	private Integer colum;
	
	public Position() {
		
	}

	public Position(Integer row, Integer colum) {
		
		this.row = row;
		this.colum = colum;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColum() {
		return colum;
	}

	public void setColum(Integer colum) {
		this.colum = colum;
	}
	
	public void setValues(int row, int colunm) {
		this.row = row;
		this.colum = colunm;
	}
	
	@Override
	public String toString() {
		return row+", "+colum;
	}
	
	
}
