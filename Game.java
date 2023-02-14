
/*Βέλλιος Γεώργιος-Σεραφείμ ΑΕΜ:9471 velliosg@ece.auth.gr    Πέτρος Θεολόγου ΑΕΜ:9464 petrtheo@ece.auth.gr  */

public class Game {

	public int round;
	
	public Game(int round) {
		this.round = round;
	}
	
	public Game() {
		round=0;
	}


	public int getRound() {
		return round;
	}


	public void setRound(int round) {
		this.round = round;
	}


	public static void main(String[] args) {

		Game game = new Game();
		Board board = new Board(20,10,3,3,6);
		board.createBoard();
	//	board.createElementBoard();
		Player p1, p2;
		int[] matrix = new int[5];
		int id1=1, id2=1;
		p1 = new Player(1, "player 1", 0, board);
		p2 = new Player(1, "player 2", 0, board);
		int round=0;
		int size=board.getM()*board.getN();
		do {
			int die1 = (int) (Math.random()*6+1);
		//	System.out.println("Player's 1 position before move:" + id1);
		//	System.out.println("Die:" + die1);
		//	System.out.println("Player's 1 position with die:" + (id1+die1));
			matrix=p1.move(id1, die1);
			id1=matrix[0];
			p1.setPlayerId(id1);
		//	System.out.println("Player's 1 position after move:" + id1);
		//	System.out.println();
		//	System.out.println();
			
			int die2 = (int) (Math.random()*6+1);
		//	System.out.println("Player's 2 position before move:" + id2);
		//	System.out.println("Die:" + die2);
		//	System.out.println("Player's 2 position with die:" + (id2+die2));
			matrix=p2.move(id2,die2);
			id2=matrix[0];
			p2.setPlayerId(id2);
		//	System.out.println("Player's 2 position after move:" + id2);
		//	System.out.println();
		//	System.out.println();

		
			game.setRound(++round);
			
		}
		while(p1.getPlayerId()<size && p2.getPlayerId()<size);
		System.out.println("Total rounds played: "+game.getRound());
		System.out.println("Player's 1 score: "+p1.getScore());
		System.out.println("Player's 2 score: "+p2.getScore());
		
		if(p1.getPlayerId()>=size)
			System.out.println("Player 1 won!");
		else if(p2.getPlayerId()>=board.getTiles().length)
			System.out.println("Player 2 won!");
	}
		

}


