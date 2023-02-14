import java.util.ArrayList;

public class HeuristicPlayer extends Player{
	public ArrayList<int[]> path;
	int[] statistika;
	
	public HeuristicPlayer(int id, String n, int s, Board b ) {           //constructor με ορισματα. Η ArrayList δεν ζητήται καθώς θα δημιουγηθεί μεσω του παρακάτω κώδικα.
		super(id, n, s, b);												// καλείται η εντολή super() που καλεί τον constructor της κλάσης Player  την οποία κληρονομεί.
		path= new ArrayList <int[]>();
		statistika=new int[4];                               //το συνολο των στοιχείων που επισκεύθηκε συνολικα ο παίκτης. αρχικά τιθενται ισα με μηδέν
		for(int i=0;i<4;i++) {
			statistika[i]=0;
		}
	}

	public HeuristicPlayer() {											//constructor χωρίς ορίσματα
		super();
		path = new ArrayList<int[]>();
		statistika=new int[4];                              //το συνολο των στοιχείων που επισκεύθηκε συνολικα ο παίκτης. αρχικά τιθενται ισα με μηδέν
		for(int i=0;i<4;i++) {
			statistika[i]=0;
		}
	}
	
	public ArrayList<int[]> getPath(){            								//getter της μεταβλητής path.
		return path;
	}

	public double evaluate(int currentPos, int dice) {
		int newPosition = currentPos + dice;
		int bPoints = score;
		boolean flag;
		
		
		
		do {
			flag = false;
			// check for snake' s head
			for (int j = 0; j < board.getSnakes().length ; j++) {
				if (board.getSnakes()[j].getHeadId() == newPosition) {
					flag = true;
					newPosition = board.getSnakes()[j].getTailId();
				//	result[1]++;
				//	System.out.println(name + " was bitten by a snake");
					break;
				}
			}
			
			// check for ladder's downstep 
			for (int j = 0; j < board.getLadders().length ; j++) {
				if (board.getLadders()[j].getDownStepId() == newPosition) {
					if (board.getLadders()[j].getBroken() == false) {
						flag = true;
						newPosition = board.getLadders()[j].getUpStepId();
					//	result[2]++;
						// System.out.println(name + " climbed a ladder");
						// board.getLadders()[j].setBroken(true);
						break;
					}
				}
			}
			
			// check for apple
			for (int j = 0; j < board.getApples().length ; j++) {
				if (board.getApples()[j].getAppleTileId() == newPosition) {
					if (board.getApples()[j].getColor() == "red"){
					     score += board.getApples()[j].getPoints(); 
					  //   result[3]++;
					}else {
						score -= board.getApples()[j].getPoints();
					//	result[4]++;
					}
					// board.getApples()[j].setPoint(0); 
					// System.out.println(name + " ate a " + board.getApples()[j].getColor() + " apple");
					break;
				}
			}
		} while(flag);

		double pontoi = (0.7 * (newPosition-currentPos)) + (0.3* (score - bPoints));
		score=bPoints;		
	return pontoi;
		
	
	}
	
	public int getNextMove(int currentPos) {
		double max=-1000000;
		int maxDie=-1;
		double[][] pinakas = new double[6][2];
		int[] matrix1 = new int[5];
		int[] matrix2 = new int[7];
		int bPoints = score;
		for(int i=0;i<6;) {
			pinakas[i][0]=++i;
			pinakas[i-1][1]=evaluate(currentPos,i);
			if(pinakas[i-1][1]>max) {
				max = pinakas[i-1][1];
				maxDie = i;
			}
		}
		
		matrix1=move(currentPos,maxDie);
		
		/* m0 =stepsNum
		* m1=snakes
		* m2=ladders
		* m3=redApples
		* m4=blackApples
		* m5=dieNum
		* m6=ApplePoints
		*/
		matrix2[0]=matrix1[0]- currentPos;
		matrix2[1]=matrix1[1];
		matrix2[2]=matrix1[2];
		matrix2[3]=matrix1[3];
		matrix2[4]=matrix1[4];
		matrix2[5]=maxDie;
		matrix2[6]=score- bPoints;
		path.add(matrix2);
		
		return matrix1[0];
	}
	
	public void statistics() {
		int round=1;
		for (int[] tmp: path){ 
		         System.out.print(" Sto round "+round+" o HeuristicPlayer e8ese to zari iso me "+tmp[5]+"\n ");
		         if(tmp[1]!=0)
		        	System.out.print(" ,katebike "+tmp[1]+" fidi "+"\n");
		         if(tmp[2]!=0)
		        	System.out.print(" ,anebike "+tmp[2]+" skala "+"\n");
		         if(tmp[3]!=0)
		        	System.out.print(" ,efage "+tmp[3]+" kokkino milo "+"\n");
		         if(tmp[4]!=0)
		        	System.out.print(" ,efage "+tmp[4]+" mauro milo "+"\n");
		         statistika[0]+=tmp[1];//fidia
		         statistika[1]+=tmp[2];//skales
		         statistika[2]+=tmp[3];//kokkina mila
		         statistika[3]+=tmp[4];//mavra mila
		         round++;
		        	 
		}
		System.out.println();
		System.out.println();
		System.out.println("Synolika o HeuristicPlayer katevike "+statistika[0]+" fidia");
		System.out.println("anebike "+statistika[1]+" skales");
		System.out.println("efage "+statistika[2]+" kokkina mila");
		System.out.println("efage "+statistika[3]+" maura mila");
		System.out.println();
		System.out.println();
		
	}
	

}