/*Βέλλιος Γεώργιος-Σεραφείμ ΑΕΜ:9471 velliosg@ece.auth.gr    Πέτρος Θεολόγου ΑΕΜ:9464 petrtheo@ece.auth.gr  */
import java.util.ArrayList;

public class Node {

	Node parent;
	ArrayList<Node> children;
	int nodeDepth;
	int[] nodeMove;     //(τρέχουσα θέση, ζάρι)
	Board nodeBoard;
	double nodeEvaluation;
	
	public Node getParent() {
		return parent;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
	
	public int getNodeDepth() {
		return nodeDepth;
	}
	
	public int[] getNodeMove() {
		return nodeMove;
	}
	
	public Board getNodeBoard() {
		return nodeBoard;
	}
	
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	public void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	
	public void setNodeMove(int[] nodeMove) {
		this.nodeMove = nodeMove;
	}
	
	public void setNodeBoard(Board nodeBoard) {
		this.nodeBoard = nodeBoard;
	}
	
	public void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
	
	public Node() {                      //constructor χωρίς ορίσματα
		parent = null;
		children = new ArrayList<Node>();
		nodeDepth=0;
		nodeMove = new int[2];
		nodeMove[1]=nodeMove[0]=0;
		nodeBoard = new Board();
		nodeEvaluation = 0.0;
	}
	
	public Node(Node p,  int nd, int[] nm, Board nb, double ne) {               //constructor με ορίσματα
		parent = p;
		children = new ArrayList<Node>();
		nodeDepth=nd;
		nodeMove = nm;
		nodeBoard = nb;
		nodeEvaluation = ne;
	}
	
	public static void printTree(Node root) {         //συνάρτηση που εκτυπώνει μερικά από τα στοιχεία του δέντρου (
		Node child1 = new Node();              //έγινε για έλγχο της ορθότητας της δημιουργίας του δέντρου)
		Node child2 = new Node();
		
		System.out.println("r:"+root.getNodeEvaluation());
		
		for(int i=0; i<6; i++) {
			child1 = root.getChildren().get(i);
			System.out.print((i+1) +"d:"+child1.getNodeMove()[1]+"e:"+child1.getNodeEvaluation());
			System.out.print("\t");
		}
		System.out.println("");
		System.out.println("");
		for(int i=0; i<6; i++) {
			child1 = root.getChildren().get(i);
			for(int j=0; j<6; j++) {
				child2 = child1.getChildren().get(j);
				System.out.print((i+1)+ "d:"+child2.getNodeMove()[1]+"e:"+child2.getNodeEvaluation());
				System.out.print("\n \n");
			}
		}
	}
	
	
}
