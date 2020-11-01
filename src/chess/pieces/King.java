package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
		// TODO Auto-generated constructor stub
	}
	
	private boolean testHookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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
		//#EspecialMove Castling
				
				if(getMoveCount() == 0 && !chessMatch.getCheck()) {
					//Especial move king side rook
					Position posT1 = new Position(position.getRow(), position.getColum() + 3);
					if(testHookCastling(posT1)) {
						Position p1 = new Position(position.getRow(), position.getColum() + 1);
						Position p2 = new Position(position.getRow(), position.getColum() + 2);
						if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
							mat[position.getRow()][position.getColum()+2] = true;
						}
					}
					//Especial move queen side rook
					Position posT2 = new Position(position.getRow(), position.getColum() -4);
					if(testHookCastling(posT2)) {
						Position p1 = new Position(position.getRow(), position.getColum() - 1);
						Position p2 = new Position(position.getRow(), position.getColum() - 2);
						Position p3 = new Position(position.getRow(), position.getColum() - 3);
						if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
							mat[position.getRow()][position.getColum() - 2] = true;
				}
			}
		}
				return mat;
		}
	}
