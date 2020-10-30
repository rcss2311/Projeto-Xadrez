package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	//classe que implementa as regras de jogo, ou de negocio, aqui � que a dinamica do jogo vai ganhar forma
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	List<Piece> piecesOnBoard = new ArrayList<>();
	List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetUp();
	
	}
	
	public int getTurn() {
		return turn;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	//cira as pe�as dentro do jogo 
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColuns()];
		for(int i = 0; i<board.getRows();i++) {
			for(int j = 0; j <board.getColuns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);
			}
		}
		return mat;
	}
	
	//imprime as possi��es possiveis, a partir de uma posi��o de origem. Faz issoconvertendo uma ChessPosition para uma position, valida
	// a source position e retorna a pe�a no tabuleiro com seus movimentos possiveis
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	//fazendo movimenta��es no jogo, fazendo testes, validando posi��o inicial, posi��o final, e ao final usando o metodo makeAmove
	//usando source e target, depois de validado.
	public ChessPiece performeChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetposition(source,target);
		Piece capturedPiece = makeMove(source,target);
		
		if(testCheck(currentPlayer)) {
			undoneMove(source, target, capturedPiece);
			throw new ChessException("You cant put your self in check!");
		}
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}else { 
			
		nextTurn();
		
		}
		return (ChessPiece)capturedPiece;
	}
	
	//determina o que acontece quando a pe�a de movimento tanto na origem , quando ela precisa ser transportada para outra posi��o
	//ate o destino, onde pode acontecer a captura de uma pe�a
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturePiece = board.removePiece(target);
		board.placePiece(p,target );
		
		if(capturePiece != null) {
			piecesOnBoard.remove(capturePiece);
			capturedPieces.add(capturePiece);
		}
		
		return capturePiece;
		
	}
	//desfaz o movimento se a posi��o for ilegal 
	private void undoneMove(Position souce, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, souce);
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnBoard.add(capturedPiece);
		}
		}
	
	// metodo para validar, primeiro, se existe uma pe�a nessa posi��o, segundo verifica se existe um movimento possivel para a pe�a
	private void validateSourcePosition(Position position) {
		if(!board.thereIsaPiece(position)) {
			throw new ChessException("There no piece 	on souce position");
		}
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece isn`t yours");
		}
		if(!board.piece(position).isThereAnyPossibleMoves()) {
			throw new ChessException("there is no possible moves for the chosen piece!");
		}
	}
	//Valida a posi��o destino, principalmente se � possivel o movimento para determinada posi��o, serve para detectar algum obstaculo ou
	// se a pe�a pode realizar o movimento
	private void validateTargetposition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can`t move to target possition!");
		}
		
	}
	
	//troca de turno
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//testa a cor do oponente com uma express�o condicional retornando a cor do oponente
	//faz parte da logica do check
	private Color opponent(Color color) {
		return (color == Color.WHITE ) ? color.BLACK : color.WHITE;
	}
	
	//Identifica percorre a lista de pe�as mo jogo com uma express�o lambda para pegar a cor da pe�a
	//depois com o for each, identifica se a pe�a p na lista � o rei
	// caso verdadeiro, retorna a pe�a p( nesse caso foi feito um down casting para pegar uma pe�a que era generica na lista, e transfomar
	//em uma especifica do tipo pe�a de xadrez) retornando a pe�a e sua cor. Caso n�o encontre o rei, gera uma excess�o 
	private ChessPiece king (Color color) {
		List<Piece> list = piecesOnBoard.stream().filter(x-> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p: list) {
			if(p instanceof King ) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There ins no"+ color +" king on the board");
	}
	
	//Logica para implementar o check
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> oponentePieces = piecesOnBoard.stream().filter(x-> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for(Piece p : oponentePieces  ) {
			boolean [][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColum()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
			if(!testCheck(color)) {
				return false;
			}
			List<Piece> list = piecesOnBoard.stream().filter(x-> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
			for(Piece p: list) {
				boolean[][] mat = p.possibleMoves();
				for(int i = 0;i<board.getRows();i++) {
					for(int j = 0; j <board.getColuns();j++ ) {
						if(mat[i][j]) {
							Position source = ((ChessPiece)p).getChessPosition().toPosition();
							Position target = new Position(i, j);
							Piece capturedPiece = makeMove(source, target);
							boolean testeCheck = testCheck(color);
							undoneMove(source, target, capturedPiece);
							if(!testeCheck) {
								return false;
							}
						}
					}
				}
			}
		return true;
	}
	
	//adiciona uma nova pe�a no tabuleiro in game
	private void placeNewPiece(char colunm, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(colunm, row).toPosition());
		piecesOnBoard.add(piece);
	}
	
	
	
	//instancia o tipo de pe�a, a cor, posi��o no tabuleiro. setUp inicial do jogo, mapeamento das pe�as no tabuleiro
	private void initialSetUp() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
	}

}
