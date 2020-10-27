package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColuns()];
		
		Position p = new Position(0,0);
		
		//acima
		p.setValues(position.getRow() - 1, position.getColum());
		while(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
			mat[p.getRow()][p.getColum()] = true; 
			p.setRow(p.getRow() - 1);
			if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColum()] = true;
				
			}
		}
		//Esquerda
				p.setValues(position.getRow(), position.getColum() - 1);
				while(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
					mat[p.getRow()][p.getColum()] = true; 
					p.setColum(p.getColum() - 1);;
					if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
						mat[p.getRow()][p.getColum()] = true;
						
					}
				}
		//direita
				p.setValues(position.getRow(), position.getColum() + 1);
				while(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
					mat[p.getRow()][p.getColum()] = true; 
					p.setColum(p.getColum() + 1);
					if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
						mat[p.getRow()][p.getColum()] = true;
						
					}
				}
		//baixo
				p.setValues(position.getRow() + 1, position.getColum());
				while(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
					mat[p.getRow()][p.getColum()] = true; 
					p.setRow(p.getRow() + 1);
					if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
						mat[p.getRow()][p.getColum()] = true;
						
					}
				}
		return mat;
	}

}
