import java.util.Scanner;
/**
 * An Edge-Weighted Directional Graph.
 */
public class MyEdgeWeightedDiGraph {
	private int V;
	private int E;
	private boolean isValid;
	private MyBag<MyDirectedEdge>[] adjacency;
	private int[] indegree;
	
	@SuppressWarnings("unchecked")
	public MyEdgeWeightedDiGraph(Scanner in) {
		
		this.isValid = true;
		
		// Read from file.
		this.V = in.nextInt();
		this.E = in.nextInt();
		
		if(this.V <= 0 || this.E <= 0) {
			this.isValid = false;
		}
		
		// If the file is valid.
		if(this.isValid) {
			this.adjacency = (MyBag<MyDirectedEdge>[]) new MyBag[V];
			this.indegree = new int[this.V];
			
			// Create empty adjacency matrix for adjacent edges
			for(int i=0; i<this.V; i++) {
				this.adjacency[i] = new MyBag<MyDirectedEdge>();
			}
			
			// Read and add edges from file
			for(int i=0; i<this.E; i++) {
				int from = in.nextInt();
				int to = in.nextInt();
				double weight = in.nextDouble();
				
				if( from>=0 && to>=0 && weight >= 0.0) {
					addEdge(new MyDirectedEdge(from, to ,weight));
				} else {
					this.isValid = false;
				}
			}
			
			// If there has any vertex in graph cannot be connected, then the graph is invalid.
			for(int i=0; i<this.V; i++) {
				if(indegree[i] < 1) {
					this.isValid = false;
					break;
				}
			}
		}
	}
	
	// Add edge to graph
	public void addEdge(MyDirectedEdge edge) {
		int from = edge.from();
		int to = edge.to();
		
		indegree[to]++;
		this.adjacency[from].add(edge);
	}
	
	public boolean isValid() {
		return this.isValid;
	}
	
	public int getV() {
		return this.V;
	}
	
	public Iterable<MyDirectedEdge> edgesAdjacentTo(int v) {
		return adjacency[v];
	}
}
