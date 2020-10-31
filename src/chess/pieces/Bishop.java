package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() { 
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColuns()];
		
		Position p = new Position(0,0);
		
		//Noroeste
		p.setValues(position.getRow() - 1, position.getColum() - 1);
		while(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
			mat[p.getRow()][p.getColum()] = true; 
			p.setValues(p.getRow() - 1, p.getColum() - 1);; 
		}
			if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColum()] = true;
		}
		//Nordeste
				p.setValues(position.getRow() - 1, position.getColum() + 1);
				while(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
					mat[p.getRow()][p.getColum()] = true; 
					p.setValues(p.getRow() - 1, p.getColum() +1);
				}
					if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
						mat[p.getRow()][p.getColum()] = true;
					}
				
		//Sudestes
				p.setValues(position.getRow() + 1, position.getColum() + 1);
				while(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
					mat[p.getRow()][p.getColum()] = true; 
					p.setValues(p.getRow() + 1, p.getColum() + 1);;
				}
					if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
						mat[p.getRow()][p.getColum()] = true;	
					}
		//Sudoeste
				p.setValues(position.getRow() + 1, position.getColum() - 1);
				while(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
					mat[p.getRow()][p.getColum()] = true; 
					p.setValues(p.getRow() + 1, p.getColum() - 1);;
				}
					if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
						mat[p.getRow()][p.getColum()] = true;	
					}
		return mat;
	}

}
