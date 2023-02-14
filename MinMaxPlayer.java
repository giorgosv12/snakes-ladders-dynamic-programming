/*Βέλλιος Γεώργιος-Σεραφείμ ΑΕΜ:9471 velliosg@ece.auth.gr    Πέτρος Θεολόγου ΑΕΜ:9464 petrtheo@ece.auth.gr  */


import java.util.ArrayList;
public class MinMaxPlayer extends Player{
	
	public ArrayList<int[]> path;
	int[] statistika;

	public MinMaxPlayer() {											//constructor χωρίς ορίσματα
		super();
		path = new ArrayList<int[]>();	
		statistika=new int[4];                               //το συνολο των στοιχείων που επισκεύθηκε συνολικα ο παίκτης. αρχικά τιθενται ισα με μηδέν
		for(int i=0;i<4;i++) {
			statistika[i]=0;
		}
	}
	
	public MinMaxPlayer(int id, String n, int s, Board b ) {           //constructor με ορισματα. Η ArrayList δεν ζητήται καθώς θα δημιουγηθεί μεσω του παρακάτω κώδικα.
		super(id, n, s, b);												// καλείται η εντολή super() που καλεί τον constructor της κλάσης Player  την οποία κληρονομεί.
		path= new ArrayList <int[]>();
		statistika=new int[4];                               //το συνολο των στοιχείων που επισκεύθηκε συνολικα ο παίκτης. αρχικά τιθενται ισα με μηδέν
		for(int i=0;i<4;i++) {
			statistika[i]=0;
		}
	}
	
	public double evaluate(int currentPos, int opponentCurrentPos ,int dice, Board board) {
		int newPosition = currentPos + dice;
		int[] temp= new int[5];
		int ofa=0,ofl=0;
		double pontoi=0.0 ;
		boolean reach = false;
		
		int opponentBestPosition;
		double max=-1000000;
		int maxDie=0;
		double[][] pinakas = new double[6][2];
		
		if(currentPos-opponentCurrentPos<6 && currentPos-opponentCurrentPos >=1) {
			reach=true;
		}
		
		if(reach==true) {
			for(int i=1; i<7; i++) {
				
				for(int j=0; j<board.getApples().length; j++) {
					if((opponentCurrentPos+i) == board.getApples()[j].getAppleTileId() && board.getApples()[j].getColor()=="red" && board.getApples()[j].getPoints()!=0)
						ofa++;
				}
				for(int j=0; j<board.getLadders().length; j++) {
					if((opponentCurrentPos+i) == board.getLadders()[j].getDownStepId() && board.getLadders()[j].getBroken()==false)
						ofl++;
				}
			}
			
			for(int i=2; i<7; i++) {
				pinakas[i-2][0]=i;
				
				Board newBoard= new Board(board);
				int bPoints=score;
				temp=move(opponentCurrentPos, i, newBoard,false);
				newPosition=temp[0];
				pinakas[i-2][1] = (0.7 * (newPosition-currentPos)) + (0.3* (score - bPoints));
				score=bPoints;
				
				if(pinakas[i-2][1]>max) {
					max = pinakas[i-2][1];
					maxDie = i;
				}
			}
			opponentBestPosition= opponentCurrentPos + maxDie;
				if((opponentBestPosition == (currentPos + dice)) && (ofa!= 0 || ofl != 0)) {
					pontoi=200;
					return pontoi;
				}
				else {
					reach=false;
				}

		}
		
		if(reach==false) {
			Board newBoard= new Board(board);
			int bPoints = score;
			temp=move(currentPos, dice, newBoard,false);
			newPosition=temp[0];
			pontoi = (0.7 * (newPosition-currentPos)) + (0.3* (score - bPoints));
			score=bPoints;
			}

		if(newPosition >= (board.getM()* board.getN()))
				pontoi=Double.POSITIVE_INFINITY;

			
		return pontoi;
	
	}
	
	public int chooseMinMaxMove(Node root) {
		
		double evalMinOp;
		double evalMax = Double.NEGATIVE_INFINITY;
		int pointer=-1;
		Node child1 = new Node();
		
		for(int i=0; i<6; i++) {
			evalMinOp = Double.POSITIVE_INFINITY;
			child1 = root.getChildren().get(i);
			for(int j=0; j<6; j++) {
				if(child1.getChildren().get(j).getNodeEvaluation()<evalMinOp) {
					evalMinOp=child1.getChildren().get(j).getNodeEvaluation();
				}
			}
			root.getChildren().get(i).setNodeEvaluation(evalMinOp);
		}
		
		for(int i=0; i<6; i++) {
			if(root.getChildren().get(i).getNodeEvaluation()>evalMax) {
				evalMax=root.getChildren().get(i).getNodeEvaluation();
				pointer = i;
			}
		}

		return (pointer+1);
	}
	
	
	
	public int [] getNextMove (int currentPos, int opponentCurrentPos ) {//alagi epistrefei pinaka
		
		int[] result = new int[2]; // (thesi, zari)
		int bPoints = score;
		int[] matrix1 = new int[5];
		int[] matrix2 = new int[7];
		Node root = new Node();
		root.setNodeBoard(board);
		root.setNodeDepth(0);
		
		createMySubtree(root, 0, currentPos, opponentCurrentPos);
		
		int dice = chooseMinMaxMove(root);
		matrix1 = move(currentPos, dice, board, true);
		
		

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
		matrix2[5]=dice;
		matrix2[6]=score- bPoints;
		path.add(matrix2);
		
		result[0]=matrix1[0];
		result[1]=matrix2[5];
		
		//Node.printTree(root);
		
		return result;
	}
	
	
	
	public void createMySubtree(Node parent, int depth, int currentPos, int opponentCurrentPos) {
		int bScore;
		
		for(int i=0; i< 6; i++) {
			Node[] newChild = new Node[6];
			Board childBoard;
			childBoard= new Board(parent.getNodeBoard());
			int[] temp= new int[5];
			double eval= evaluate(currentPos, opponentCurrentPos, i+1, childBoard);
			bScore = score;
			temp=move(currentPos, (i+1),childBoard, false);
			score = bScore;
			int[] temp2= new int[2];
			temp2[0]=temp[0];
			temp2[1]=i+1;
			newChild[i]= new Node(parent, depth+1, temp2, childBoard, eval);
			
			parent.getChildren().add(newChild[i]);
			createOpponentSubtree(newChild[i], newChild[i].getNodeDepth(), temp2[0], opponentCurrentPos, newChild[i].getNodeEvaluation());
		}
		
		
	}
	
	public void createOpponentSubtree(Node  parent, int depth,int currentPos,int opponentCurrentPos, double parentEval) {
		int bScore;
		Node[] newChild1= new Node[6];
		Board childBoard2;
		for(int j=0; j<6; j++) {
			childBoard2= new Board(parent.getNodeBoard());

			int[] temp= new int[5];
			double eval1= evaluate(opponentCurrentPos, currentPos, j+1, childBoard2);
			bScore = score;
			temp=move(opponentCurrentPos, j+1, childBoard2, false);
			score = bScore;
			
			int[] temp2= new int[2];
			temp2[0]=temp[0];
			temp2[1]=j+1;
			newChild1[j]= new Node(parent, depth+1, temp2, childBoard2,parentEval-eval1);
			parent.getChildren().add(newChild1[j]);
		}
	}
	
	
	
	public void statistics() {
		int round=1;
		for (int[] tmp: path){ 
		         System.out.print(" Sto round "+round+" o MinMaxPlayer e8ese to zari iso me "+tmp[5]+"\n ");
		         if(tmp[5]==0)
		        	 System.out.println("O NormalPlayer sxedon nikise kai o MinMaxPlayer den kounietai");
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
		System.out.println("Synolika o MinMaxPlayer katevike "+statistika[0]+" fidia");
		System.out.println("anebike "+statistika[1]+" skales");
		System.out.println("efage "+statistika[2]+" kokkina mila");
		System.out.println("efage "+statistika[3]+" maura mila");
		System.out.println();
		System.out.println();
		
	}
	
}

	
