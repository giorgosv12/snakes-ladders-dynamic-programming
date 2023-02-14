
/*Βέλλιος Γεώργιος-Σεραφείμ ΑΕΜ:9471 velliosg@ece.auth.gr    Πέτρος Θεολόγου ΑΕΜ:9464 petrtheo@ece.auth.gr  */

public class Player {
	int playerId;
	String name;
	int score;
	Board board;
	
	public Player() {
		playerId=0;
		name="";
		score=0;
		board = new Board();
	}
	public Player(int playerId, String name, int score, Board board) {
		this.playerId = playerId;
		this.name = name;
		this.score = score;
		this.board = board;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public Board getBoard() {
		return board;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	int[] move(int id, int die) {
		int[] matrix = new int[5];
		int mediumId, snakesNum=0, laddersNum=0, redApplesNum=0, blackApplesNum=0;
		
		boolean found=false;
		mediumId=id+die;
		do {
			found=false;
			
			for(int i=0;i<board.getSnakes().length;i++) {
				if(mediumId==board.getSnakes()[i].getHeadId()) {
					mediumId=board.getSnakes()[i].getTailId();
					System.out.println("Player got bit by a snake, Ouch!");
					snakesNum++;
					found=true;
				}
					
			}
				for(int j=0;j<board.getLadders().length;j++) {
					if(found==true)
						break;
						
					if(mediumId==board.getLadders()[j].getDownStepId() && !board.getLadders()[j].getBroken()) {
						mediumId=board.getLadders()[j].getUpStepId();
						System.out.println("Player climbed a ladder!");
						laddersNum++;
                                                         
						board.getLadders()[j].setBroken(true);
						found=true;
					}
				}
					for(int k=0;k<board.getApples().length;k++) {
						if(mediumId==board.getApples()[k].getAppleTileId() && board.getApples()[k].getPoints() != 0) {
							score += board.getApples()[k].getPoints();
							System.out.println("Player ate an apple! Their points were altered by:" + board.getApples()[k].getPoints());
							board.getApples()[k].setPoints(0);
							
							if(board.getApples()[k].getColor()=="red")
								redApplesNum++;
							else
								blackApplesNum++;
							found = true;
							break;
						}
					}
				
			
			//System.out.println("MediumId"+mediumId);

			
		}
		while(found==true);
		
		matrix[0]=mediumId;
		matrix[1]=snakesNum;
		matrix[2]=laddersNum;
		matrix[3]=redApplesNum;
		matrix[4]=blackApplesNum;
		return matrix;
	}
	
}