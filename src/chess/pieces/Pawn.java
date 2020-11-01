package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color,ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColuns()];
		
		Position p = new Position(0,0);
		
		if(getColor() == Color.WHITE ) {
			p.setValues(position.getRow() - 1, position.getColum() );
			if(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
				mat[p.getRow()][p.getColum()] = true;
			}
			p.setValues(position.getRow() - 2, position.getColum() );
			Position p2 = new Position(position.getRow() - 1, position.getColum());
			if(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p) && getBoard().positioExist(p2) && !getBoard().thereIsaPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColum()] = true;
			}
			if(getColor() == Color.WHITE ) {
				p.setValues(position.getRow() - 1, position.getColum() - 1 );
				if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
					mat[p.getRow()][p.getColum()] = true;
				}
		}
			if(getColor() == Color.WHITE ) {
				p.setValues(position.getRow() - 1, position.getColum() + 1 );
				if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
					mat[p.getRow()][p.getColum()] = true;
				}
			}
			//Special move enPassant
			if(position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColum()-1);
				if(getBoard().positioExist(left) && isThereOponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow()-1][left.getColum()] = true;
				}
				Position rigth = new Position(position.getRow(), position.getColum()+1);
				if(getBoard().positioExist(rigth) && isThereOponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVulnerable()) {
					mat[rigth.getRow()-1][rigth.getColum()] = true;
				}

			}
		
	} else {
		p.setValues(position.getRow() + 1, position.getColum() );
		if(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p)) {
			mat[p.getRow()][p.getColum()] = true;
		}
		p.setValues(position.getRow() + 2, position.getColum() );
		Position p2 = new Position(position.getRow() + 1, position.getColum());
		if(getBoard().positioExist(p) && !getBoard().thereIsaPiece(p) && getBoard().positioExist(p2) && !getBoard().thereIsaPiece(p2) && getMoveCount() == 0) {
			mat[p.getRow()][p.getColum()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColum() - 1);
		if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColum()] = true;
			}
		p.setValues(position.getRow() + 1, position.getColum() + 1 );
		if(getBoard().positioExist(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColum()] = true;
			}
		}
		
		//Special move enPassant black
		if(position.getRow() == 4) {
			Position left = new Position(position.getRow(), position.getColum()-1);
			if(getBoard().positioExist(left) && isThereOponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
				mat[left.getRow()+1][left.getColum()] = true;
			}
			Position rigth = new Position(position.getRow(), position.getColum()+1);
			if(getBoard().positioExist(rigth) && isThereOponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVulnerable()) {
				mat[rigth.getRow()+1][rigth.getColum()] = true;
			}

		}

		
	
		return mat;
	}
}

