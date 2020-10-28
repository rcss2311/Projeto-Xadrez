package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	//verifica movimentos possiveis especificos dessa peça, neste caso o rei pode ir 1 casa em todas as direçoes
	//cria um objeto do tipo chessPiece e atribui um cating pegando a peça no tabuleiro, na posição especificada e retorna
	//valor booleano verificando se p é igual a nulo, ou se é diferente da cor do adversario
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColuns()];
		
		//criar uma variavel auxiliar para verificar todas as 8 posições de movimento do rei
		
		Position p = new Position(0,0);
		
		//acima
		p.setValues(position.getRow() - 1, position.getColum());
		if(getBoard().positioExist(p) && canMove(p)){
			mat[p.getRow()][p.getColum()] = true;
		}
		
		//abaixo
				p.setValues(position.getRow() + 1, position.getColum());
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
				}
		//esquerda
				p.setValues(position.getRow(), position.getColum() - 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		//direita
				p.setValues(position.getRow(), position.getColum() + 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		//noroeste
				p.setValues(position.getRow() - 1, position.getColum() - 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		//nordeste
				p.setValues(position.getRow() - 1, position.getColum() + 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		//suldoeste
				p.setValues(position.getRow() + 1, position.getColum() - 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		//suldoeste
				p.setValues(position.getRow() + 1, position.getColum() + 1);
				if(getBoard().positioExist(p) && canMove(p)){
					mat[p.getRow()][p.getColum()] = true;
		}
		return mat;
	}
	

}
