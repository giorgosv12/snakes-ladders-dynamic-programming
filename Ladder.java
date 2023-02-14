
/*Βέλλιος Γεώργιος-Σεραφείμ ΑΕΜ:9471 velliosg@ece.auth.gr    Πέτρος Θεολόγου ΑΕΜ:9464 petrtheo@ece.auth.gr  */

public class Ladder {
	
	int ladderId;
	int upStepId;
	int downStepId;
	boolean broken;
	
	public Ladder(Ladder a){
		ladderId=a.ladderId;
		upStepId=a.upStepId;
		downStepId=a.downStepId;
		broken=a.broken;
		
	}
	
	public Ladder() {
		ladderId=0;
		upStepId=0;
		downStepId=0;
		broken=false;
	}
	
	public Ladder(int a, int b, int c, boolean d) {
		ladderId=a;
		upStepId=b;
		downStepId=c;
		broken=d;
	}
	
	public int getLadderId() {
		return ladderId;
	}
	
	public int getUpStepId() {
		return upStepId;
	}
	
	public int getDownStepId() {
		return downStepId;
	}
	
	public boolean getBroken() {
		return broken;
	}
	
	public void setLadderId(int a) {
		ladderId=a;
	}
	
	public void setUpStepId(int a) {
		upStepId=a;
	}
	
	public void setDownStepId(int a) {
		downStepId=a;
	}
	
	public void setBroken(boolean a) {
		broken=a;
	}
}
