
/*Βέλλιος Γεώργιος-Σεραφείμ ΑΕΜ:9471 velliosg@ece.auth.gr    Πέτρος Θεολόγου ΑΕΜ:9464 petrtheo@ece.auth.gr  */

public class Apple {
	
	int appleId;
	int appleTileId;
	String color;
	int points;
	
	public Apple(Apple a) {
		appleId=a.appleId;
		appleTileId=a.appleTileId;
		color=a.color;
		points=a.points;
	}
	
	public Apple() {
		appleId=0;
		appleTileId=0;
		color="red";
		points=0;
	}
	
	public Apple(int a, int b, String c, int d) {
		appleId=a;
		appleTileId=b;
		color=c;
		points=d;
	}
	
	public void setAppleId(int a) {
		appleId=a;
	}
	
	public void setAppleTileId(int a) {
		appleTileId=a;
	}
	
	public void setColor(String a) {
		color=a;
	}
	
	public void setPoints(int a) {
		points=a;
	}
	
	public int getAppleId() {
		return appleId;
	}
	
	public int getAppleTileId() {
		return appleTileId;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getPoints() {
		return points;
	}
}
