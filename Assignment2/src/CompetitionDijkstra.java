import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    - Each contestant walks at a given estimated speed.
 *    - The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

/**
 *  @author Can Zhou (19324118 zhouc@tcd.ie haven't share code or write code for others)
 * 
 * 	Discussion has been commented at the top of CompetitionTests.java file.
 *  Four additional files has been used to implement Dijkstra's data structure:
 * 	a) MyBag.java
 * 		- A generic bag or multiset, implemented using a singly linked list.
 * 		- Being used to implement MyEdgeWeightedDiGraph.java.
 * 
 * 	b) MyDirectedEdge.java 
 * 		- The data type provides methods for accessing the two endpoints of the directed edge and the weight.
 * 		- Being used to implement MyEdgeWeightedDiGraph.java.
 * 
 *  c) MyEdgeWeightedDiGraph.java
 *  	- An Edge-Weighted Directional Graph.
 *  
 *  d) MyIndexMinPQ.java
 *  	- Minimum-oriented indexed PQ implementation using a binary heap.
 *  	- Being used to implement MyEdgeWeightedDiGraph.java.
 */

public class CompetitionDijkstra {
	
	public MyEdgeWeightedDiGraph graph;
	public int slowestSpeed;
	public double maxDist;
	public String filename;
	public double[] distTo;
	public MyDirectedEdge[] edgeTo;
	public MyIndexMinPQ<Double> priorityQueue;
	public int sA, sB, sC;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
    	
    	// Try to read input file.
    	try{
			this.filename = filename;
			File file = new File(filename);
			Scanner in = new Scanner(file);
			
			this.sA = sA;
			this.sB = sB;
			this.sC = sC;
			
			// Implement an Edge-Weighted Directional Graph for Dijkstra.
			this.graph = new MyEdgeWeightedDiGraph(in);
			this.slowestSpeed = Math.min(Math.min(sA,sB),sC);

			this.maxDist = 0.0;
		
		} catch (FileNotFoundException | NullPointerException e){
			this.graph = null;	
			this.filename = null;
		}
    	
    	
    	// If graph is valid
    	if(this.graph != null && this.graph.isValid()) {
    		// For each intersection
    		for(int intersection=0; intersection<graph.getV(); intersection++) {
    			this.distTo = new double[this.graph.getV()];
    			this.edgeTo = new MyDirectedEdge[this.graph.getV()];
    			
    			// Initialize all distances to max value
    			for (int i=0; i<graph.getV(); i++) {
    				distTo[i] = Double.POSITIVE_INFINITY;
    			}
    			
    			// Initialize source distance to 0.0
    			distTo[intersection] = 0.0;
    			
    			priorityQueue = new MyIndexMinPQ<Double>(graph.getV());
    			priorityQueue.insert(intersection, distTo[intersection]);
    			
    			while(!priorityQueue.isEmpty()) {
    				// Get node with minimum distance and remove from PQ
    				int v = priorityQueue.delMin();
    				
    				// For every edge related to source, relax them.
    				for(MyDirectedEdge e : graph.edgesAdjacentTo(v)) {
    					relax(e);
    				}
    			}
    			
    			// Find max distance
    			for(int j=0; j<this.graph.getV(); j++) {
    				if(this.distTo[j] < Double.POSITIVE_INFINITY) {
    					if(this.maxDist < this.distTo[j]) {
    						this.maxDist = this.distTo[j];
    					}
    				}
    			}
    			
    		}
    	}
    }

    private void relax(MyDirectedEdge e) {
    	int from = e.from();
    	int to = e.to();
    	
    	// If new route is lower
    	if(this.distTo[to] > this.distTo[from] + e.weight()) {
    		this.distTo[to] = this.distTo[from] + e.weight();
    		this.edgeTo[to] = e;
    		
    		// If PQ already contains to
    		if(this.priorityQueue.contains(to)) {
    			// Update
    			this.priorityQueue.decreaseKey(to, this.distTo[to]);
    		} else {
    			// else insert
    			this.priorityQueue.insert(to, this.distTo[to]);
    		}
    	}
    }
    
    
    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        if(this.maxDist<=0.0 || this.slowestSpeed <= 0 || this.filename == null || this.graph == null || (!this.validSpeed(sA)) || (!this.validSpeed(sB)) || (!this.validSpeed(sC))) {
        	return -1;
        }
        double time = (1000*this.maxDist)/this.slowestSpeed;
        return (int) Math.ceil(time);
    }
    
    public boolean validSpeed(int s) {
    	return s<=100&&s>=50;
    }

}

