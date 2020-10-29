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
		
		nextTurn();
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
	
	private Color opponent(Color color) {
		return (color == Color.WHITE ) ? color.BLACK : color.WHITE;
	}
	
	private ChessPiece king (Color color) {
		List<Piece> list = piecesOnBoard.stream().filter(x-> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p: list) {
			if(p instanceof King ) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There ins no"+ color +" king on the board");
	}
	
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
	
	//adiciona uma nova pe�a no tabuleiro in game
	private void placeNewPiece(char colunm, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(colunm, row).toPosition());
		piecesOnBoard.add(piece);
	}
	
	
	
	//instancia o tipo de pe�a, a cor, posi��o no tabuleiro. setUp inicial do jogo, mapeamento das pe�as no tabuleiro
	private void initialSetUp() {
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

}
