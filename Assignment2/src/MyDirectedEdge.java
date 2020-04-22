
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
