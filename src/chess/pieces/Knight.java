package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "N";
	}
	
	//verifica movimentos possiveis especificos dessa pe�a, neste caso o rei pode ir 1 casa em todas as dire�oes
	//cria um objeto do tipo chessPiece e atribui um cating pegando a pe�a no tabuleiro, na posi��o especificada e retorna
	//valor booleano verificando se p � igual a nulo, ou se � diferente da cor do adversario
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColuns()];
		
		//criar uma variavel auxiliar para verificar todas as 8 posi��es de movimento do rei
		
		Position p = new Position(0,0);
		
		
		p.setValues(position.getRow() - 1, position.getColum() - 2);
		if(getBoard().positioExist(p) && canMove(p)){
			mat[p.getRow()][p.getColum()] = true;
		}
		
		
				p.setValues(position.getRow() - 2, position.getColum() - 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
				}
		
				p.setValues(position.getRow()-2, position.getColum() + 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		
				p.setValues(position.getRow()-1, position.getColum() + 2);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		
				p.setValues(position.getRow() +1, position.getColum() +2 );
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		
				p.setValues(position.getRow() +2, position.getColum() + 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		
				p.setValues(position.getRow() + 2, position.getColum() - 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		
				p.setValues(position.getRow() + 1, position.getColum() - 2);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		return mat;
	}
	

}
