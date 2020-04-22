import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants鈥�
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    飩� Each contestant walks at a given estimated speed.
 *    飩� The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
	
	public int slowestSpeed;
	public double maxDist;
	public String filename;
	public double[][] dist;
	public boolean validFile;
	public boolean hasPath, hasNegativeCycle;
	public int sA, sB, sC;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){

    	try{
			this.filename = filename;
			File file = new File(filename);
			Scanner in = new Scanner(file);
			this.sA = sA;
			this.sB = sB;
			this.sC = sC;
			this.slowestSpeed = Math.min(Math.min(sA,sB),sC);
			this.maxDist = 0.0;
			
			this.validFile = true;
			this.hasPath = true;
			this.hasNegativeCycle = false;
			
			int V = in.nextInt();
			int E = in.nextInt();
			
			if(V <=0 || E <= 0) {
				this.validFile = false;
				return;
			}
			
			dist = new double[V][V];
			
			for(int i=0; i<V; i++) {
				for(int j=0; j<V; j++) {
					dist[i][j] = Double.POSITIVE_INFINITY;
				}
			}
			
			while(in.hasNextLine() && in.hasNextInt()) {
				int from = in.nextInt();
				int to = in.nextInt();
				double weight = in.nextDouble();
				dist[from][to] = weight;
			}
			
			for(int i=0; i<V; i++) {
				dist[i][i] = 0.0;
			}
			
			for(int k=0; k<V; k++) {
				for(int i=0; i<V; i++) {
					for(int j=0; j<V; j++) {
						if(dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					}
					
					if(dist[i][i] < 0.0) {
						this.hasNegativeCycle = true;
					}
				}
			}
			
			if(this.validFile) {
	    		for(int i=0; i<V; i++) {
	    			for(int j=0; j<V; j++) {
	    				if(dist[i][j] == Double.POSITIVE_INFINITY) {
	    					this.hasPath = false;
	    				} else {
	    					if(dist[i][j] > this.maxDist) {
	    						this.maxDist = dist[i][j];
	    					}
	    				}
	    			}
	    		}
	    	}
		
		} catch (FileNotFoundException | NullPointerException e){
			this.validFile = false;
			this.hasPath = false;
		}
    	
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        if((!this.hasPath) || (!this.validFile) || (!this.validSpeed(sA)) || (!this.validSpeed(sB)) || (!this.validSpeed(sC)) || this.hasNegativeCycle) {
        	return -1;
        }
        
        double time = (1000*this.maxDist)/this.slowestSpeed;
        return (int) Math.ceil(time);
        
    }
    
    public boolean validSpeed(int s) {
    	return s<=100&&s>=50;
    }
    
//  public static void main(String[] args) {
//	  CompetitionFloydWarshall test = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);
//	int time = test.timeRequiredforCompetition();
//	System.out.println(time);
//}

}