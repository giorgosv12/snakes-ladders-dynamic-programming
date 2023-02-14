
/*Βέλλιος Γεώργιος-Σεραφείμ ΑΕΜ:9471 velliosg@ece.auth.gr    Πέτρος Θεολόγου ΑΕΜ:9464 petrtheo@ece.auth.gr  */

public class Snake {
	
	int snakeId;
	int headId;
	int tailId;
	
	public Snake() {
		snakeId=0;
		headId=0;
		tailId=0;
	}
	
	public Snake(Snake snake) {
		snakeId=snake.snakeId;
		headId=snake.headId;
		tailId=snake.tailId;
	}
	
	public Snake(int snakeId, int headId, int tailId) {
		this.snakeId=snakeId;
		this.headId=headId;
		this.tailId=tailId;
	}
	
	public int getSnakeId() {
		return snakeId;
	}
	
	public int getHeadId() {
		return headId;
	}
	
	public int getTailId() {
		return tailId;
	}
	
	public void setSnakeId(int a) {
		snakeId=a;
	}
	
	public void setHeadId(int a) {
		headId=a;
	}
	
	public void setTailId(int a) {
		tailId=a;
	}
	
}
