/**
 * Each edge consists of two integers (naming the two vertices) and a real-value weight. 
 * The data type provides methods for accessing the two endpoints of the directed edge and the weight.
 */
public class MyDirectedEdge {

	private final int from;
	private final int to;
	private final double weight;

	public MyDirectedEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int from() {
		return from;
	}

	public int to() {
		return to;
	}

	public double weight() {
		return weight;
	}
	
}
