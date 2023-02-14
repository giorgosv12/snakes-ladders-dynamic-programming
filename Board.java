
/*Βέλλιος Γεώργιος-Σεραφείμ ΑΕΜ:9471 velliosg@ece.auth.gr    Πέτρος Θεολόγου ΑΕΜ:9464 petrtheo@ece.auth.gr  */

public class Board {
	
	int N;
	int M;
	int[][]tiles;
	Snake[] snakes;
	Ladder[] ladders;
	Apple[] apples;
	
	public void setN(int a) {
		N=a;
	}
	
	public void setM(int a) {
		M=a;
	}
	
	public void setTiles (int[][]a, int n, int m) {
		for(int i=0; i<n; i++) 
			for( int j=0; j<m; j++) 
				tiles[i][j]=a[i][j];

	}
	
	public void setSnakes(Snake[] a, int b) {
		for(int i=0; i<b; i++)
			snakes[i]=a[i];
	}
	
	public void setLadders(Ladder[] a, int b) {
		for(int i=0; i<b; i++)
			ladders[i]=a[i];
	}
	
	public void setApples(Apple[] a, int b) {
		for(int i=0; i<b; i++)
			apples[i]=a[i];
	}
	
	public int getN() {
		return N;
	}
	
	public int getM() {
		return M;
	}
	
	public int[][] getTiles(){
		return tiles;
	}
	
	public Snake[] getSnakes() {
		return snakes;
	}
	
	public Ladder[] getLadders() {
		return ladders;
	}
	
	public Apple[] getApples() {
		return apples;
	}
	
	public Board() {
		N=M=2;
		tiles = new int[2][2];
		for(int i=0; i<2; i++)
			for(int j=0; j<2; j++)
				tiles[i][j]=0;
		snakes = new Snake[1];
		snakes[0]=new Snake();
		ladders= new Ladder[1];
		ladders[0]=new Ladder();
		apples= new Apple[1];
		apples[0]=new Apple();
		
	}
	
	public Board(int N, int M, int snakeNum, int ladderNum, int appleNum) {
		this.N=N;
		this.M=M;
		tiles = new int[N][M];
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				tiles[i][j]=0;
		snakes = new Snake[snakeNum];
		for(int i=0; i<snakeNum; i++) {
			snakes[i]=new Snake();
		}
		ladders= new Ladder[ladderNum];
		for(int i=0; i< ladderNum; i++) {
			ladders[i]=new Ladder();
		}
		apples= new Apple[appleNum];
		for(int i=0; i<appleNum; i++) {
			apples[i]=new Apple();
		}
	}
	
	public Board(Board a) {
		N=a.N;
		M=a.M;
		tiles= new int[a.N][a.M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++)
				tiles[i][j]=a.tiles[i][j];
		}
		snakes=new Snake[a.snakes.length];
		for(int i=0; i<a.snakes.length; i++)
			snakes[i]=a.snakes[i];
		
		ladders= new Ladder[a.ladders.length];
		for(int i=0;i<a.ladders.length;i++)
			ladders[i]=a.ladders[i];
		
		apples=new Apple[a.apples.length];
		for(int i=0;i<a.apples.length;i++)
			apples[i]=a.apples[i];
		
	}
	
	public void createBoard() {
		int headId, tailId, upStepId, downStepId, appleTileId;
		boolean touch=false, headTouch=false, stepDownTouch=false , snakeTouch=false;
		int boolId;
		int snakeId = 0, ladderId = 0, appleId = 0;
		boolean right=true;
		int num=1;
		for(int i=N-1; i>=0; i-- ) {
			if(right) {
				for(int j=0; j<M; j++) {
					tiles[i][j]=num;
					num++;
				}//endfor
				right=false;
			}//endif
			else {
				for(int j=M-1; j>=0; j--) {
					tiles[i][j]=num;
					num++;
				}//endfor
				right=true;
			}//endif
		}//endforMegalo
		
			for(int i=0; i<snakes.length; i++) {

				do {
					headTouch=false;
					headId = (int) (Math.random()*num+1);
					tailId = (int) (Math.random()*num+1);
					for(int j=0; j<snakes.length; j++)
						if(headId==snakes[j].headId) {
							headTouch=true;
							break;
						}
				}while( (headId-tailId)<=N || headTouch==true);
				snakes[i].setHeadId(headId);
				snakes[i].setTailId(tailId);
				snakes[i].setSnakeId(snakeId);
				snakeId++;
			}
			for(int i=0; i< ladders.length; i++) {

				do {
					stepDownTouch=false;
					snakeTouch=false;
					upStepId = (int) (Math.random()*num+1);
					downStepId= (int) (Math.random()*num+1);
					for(int j=0;j<ladders.length;j++)
						if(downStepId==ladders[j].downStepId) {
							stepDownTouch=true;
							break;
						}
					for(int j=0;j<snakes.length;j++)
						if(downStepId==snakes[j].headId) {
							snakeTouch=true;
							break;
						}
				}while( upStepId-downStepId <=N  || stepDownTouch || snakeTouch);
				ladders[i].setLadderId(ladderId);
				ladders[i].setUpStepId(upStepId);
				ladders[i].setDownStepId(downStepId);
				ladders[i].setBroken(false);
				ladderId++;
			}		
			for(int i=0; i<apples.length; i++) {
				do {
					touch=false;
					appleTileId= (int) (Math.random()*num+1);
					for(int j=0; j< snakes.length ; j++) {
						if(appleTileId==snakes[j].getHeadId()) {
							touch=true;
								break;
						}
					}
				}while(touch==true);
				boolId= (int) (Math.random()*2+1);
				if(boolId==1) {
					apples[i].setAppleId(appleId);
					apples[i].setAppleTileId(appleTileId);
					apples[i].setColor("red");
					apples[i].setPoints((int) (Math.random()*10+1));
					appleId++;	
				}
				else {
					apples[i].setAppleId(appleId);
					apples[i].setAppleTileId(appleTileId);
					apples[i].setColor("black");
					apples[i].setPoints( (-1) * (int) (Math.random()*10+1) );
					appleId++;
				}
					
				
			}
		
	}
	
	public void createElementBoard() {
		String[][] elementBoardSnakes = new String[N][M];
		String[][] elementBoardLadders = new String[N][M];
		String[][] elementBoardApples = new String[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				elementBoardSnakes[i][j]=" ___ ";
				elementBoardLadders[i][j]=" ___ ";
				elementBoardApples[i][j]=" ___ ";
				for(int k=0; k<snakes.length; k++) {
					if(snakes[k].getHeadId()==tiles[i][j])
						elementBoardSnakes[i][j]=" SH"+ snakes[k].getSnakeId();
					if(snakes[k].getTailId()==tiles[i][j])
						elementBoardSnakes[i][j]=" ST"+ snakes[k].getSnakeId();
				}
				for(int l=0; l< ladders.length; l++) {
					if(ladders[l].getDownStepId()==tiles[i][j] && ladders[l].getBroken()==false)
						elementBoardLadders[i][j]=" LD" + ladders[l].getLadderId();
					if(ladders[l].getUpStepId()==tiles[i][j] && ladders[l].getBroken()==false)
						elementBoardLadders[i][j]=" LU" + ladders[l].getLadderId();
				}
				for(int a=0; a<apples.length; a++) {
					if(apples[a].getAppleTileId()==tiles[i][j] && apples[a].getColor()=="red" && apples[a].getPoints() != 0)
						elementBoardApples[i][j]=" AR" + apples[a].getAppleId();
					if(apples[a].getAppleTileId()==tiles[i][j] && apples[a].getColor()=="black" && apples[a].getPoints() != 0)
						elementBoardApples[i][j]=" AB" + apples[a].getAppleId();
				
				}
			}
		}
		
		System.out.println("elementBoardSnakes");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(elementBoardSnakes[i][j]);
			}System.out.println();
		}
		
		System.out.println();
		System.out.println();
		System.out.println("elementBoardLadders");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(elementBoardLadders[i][j]);
			}System.out.println();
		}
		
		System.out.println();
		System.out.println();
		System.out.println("elementBoardAppless");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(elementBoardApples[i][j]);
			}System.out.println();
		}
	
	
	}
	
}
