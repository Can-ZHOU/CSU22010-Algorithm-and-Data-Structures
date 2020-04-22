import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Can Zhou 19324118 zhouc@tcd.ie
 * 
 * Discuss the differences between Dijkstra and Floyd Warshall's implementation and performance.
 * 
 * 0. Implementation:
 * 	a) CompetitionDijkstra:
 * 		My implementation uses Dijkstra's algorithm with a binary heap priority queue.
 * 		Since Dijkstra's shortest path algorithm requires a graphs Nodes and Vertices to contain both edge weightings and directions,
 * 		I chose binary heap priority queue to represent an Edge-Weighted Directional Graph.
 * 		I implemented binary heap priority queue by using the similar data structure in the book but having many adjustments(Sedgeick & Wayne - https://algs4.cs.princeton.edu/home).
 * 		This can be found within the MyEdgeWeightedDiGraph.java. 
 * 		MyEdgeWeightedDiGraph efficiently and easily allows us to access the adjacency metric of vertices by using another data structure from the book which is the Bag of Directed Edges data structure.
 * 		
 * 		Here is a part of an example graph for using MyEdgeWeightedDiGraph:
 * 		tinyEWD.txt, intersection 0 to some other intersections:
 *  	0 to 0 (0.00)  
 *  	0 to 1 (1.05)  0->4  0.38   4->5  0.35   5->1  0.32   
 *  	0 to 2 (0.26)  0->2  0.26
 * 		0 to 3 (0.99)  0->2  0.26   2->7  0.34   7->3  0.39   
 *  	0 to 4 (0.38)  0->4  0.38
 *  
 *  b) CompetitionFloydWarshall:
 *  	
 * 		
 * 
 * 
 *
 */

@RunWith(JUnit4.class)
public class CompetitionTests {
	
	//~ Constructor ........................................................
    @Test
    public void testDijkstraConstructor() {
    	
    	// Test correct inputs.
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);
    	assertTrue("Build the correct graph && all inputs are valid", dijkstra.timeRequiredforCompetition()!=-1);
    	
    	// Test invalid file.
    	dijkstra = new CompetitionDijkstra("wrong", 50, 75, 100);
    	assertTrue("Test inexistent file.", dijkstra.timeRequiredforCompetition()==-1);
    	dijkstra = new CompetitionDijkstra("Test_empty.txt", 50, 75, 100);
    	assertTrue("Test empty file.", dijkstra.timeRequiredforCompetition()==-1);
    	dijkstra = new CompetitionDijkstra("Test_emptyEdge.txt", 50, 75, 100);
    	assertTrue("Test file without any edge.", dijkstra.timeRequiredforCompetition()==-1);
    	dijkstra = new CompetitionDijkstra("Test_hasNegativeCycle.txt", 50, 75, 100);
    	assertTrue("Test file with negative cycle.", dijkstra.timeRequiredforCompetition()==-1);
    	
    	
    	// Test invalid speed.
    	dijkstra = new CompetitionDijkstra("tinyEWD.txt", 0, 75, 100);
    	assertTrue("Test invalid speed.", dijkstra.timeRequiredforCompetition()==-1);
    	dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, -75, 100);
    	assertTrue("Test invalid speed.", dijkstra.timeRequiredforCompetition()==-1);
    	dijkstra = new CompetitionDijkstra("tinyEWD.txt", 0, -75, 1000);
    	assertTrue("Test invalid speed.", dijkstra.timeRequiredforCompetition()==-1);
    	
    }

    @Test
    public void testFWConstructor() {
    	// Test correct inputs.
    	CompetitionFloydWarshall fw = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);
    	assertTrue("Build the correct graph && all inputs are valid", fw.timeRequiredforCompetition()!=-1);
    	
    	// Test invalid file.
    	fw = new CompetitionFloydWarshall("wrong", 50, 75, 100);
    	assertTrue("Test inexistent file.", fw.timeRequiredforCompetition()==-1);
    	fw = new CompetitionFloydWarshall("Test_empty.txt", 50, 75, 100);
    	assertTrue("Test empty file.", fw.timeRequiredforCompetition()==-1);
    	fw = new CompetitionFloydWarshall("Test_emptyEdge.txt", 50, 75, 100);
    	assertTrue("Test file without any edge.", fw.timeRequiredforCompetition()==-1);
    	fw = new CompetitionFloydWarshall("Test_hasNegativeCycle.txt", 50, 75, 100);
    	assertTrue("Test file with negative cycle.", fw.timeRequiredforCompetition()==-1);
    	
    	// Test invalid speed.
    	fw = new CompetitionFloydWarshall("tinyEWD.txt", 0, 75, 100);
    	assertTrue("Test invalid speed.", fw.timeRequiredforCompetition()==-1);
    	fw = new CompetitionFloydWarshall("tinyEWD.txt", 50, -75, 100);
    	assertTrue("Test invalid speed.", fw.timeRequiredforCompetition()==-1);
    	fw = new CompetitionFloydWarshall("tinyEWD.txt", 0, -75, 1000);
    	assertTrue("Test invalid speed.", fw.timeRequiredforCompetition()==-1);
    }

    
    //~ Testing CompetitionDijkstra.timeRequiredforCompetition()........................................................
    @Test
    public void testCompetitionDijkstra_timeRequiredforCompetition() {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);
    	assertEquals("Test tinyEWD", 38, dijkstra.timeRequiredforCompetition());
    	dijkstra = new CompetitionDijkstra("1000EWD.txt", 50, 75, 100);
    	assertEquals("Test 1000EWD", 28, dijkstra.timeRequiredforCompetition());
    }
    
    //~ Testing Data Structure in CompetitionDijkstra
    @Test
    public void testMyIndexMinPQ() {
    	MyIndexMinPQ<Integer> pq = new MyIndexMinPQ<Integer>(3);
    	pq.insert(1, 1);
    	pq.insert(2, 2);
    	pq.insert(3, 3);
    	pq.iterator();
    	MyIndexMinPQ.HeapIterator PQHeap = pq.new HeapIterator();
    	MyIndexMinPQ<Integer> pq2 = new MyIndexMinPQ<Integer>(0);
    	MyIndexMinPQ.HeapIterator PQHeap2 = pq2.new HeapIterator();
    	
    	// Testing hasNext() in MyIndexMinPQ.HeapIterator
    	assertTrue("Testing hasNext() in MyIndexMinPQ.HeapIterator", PQHeap.hasNext());
    	
    	// Testing next() in MyIndexMinPQ.HeapIterator
    	assertEquals("Testing next() in MyIndexMinPQ.HeapIterator", 1, (int)PQHeap.next());
    	try {
    		PQHeap2.next();
		} catch (Exception e) {
			assertTrue(" Testing next() in MyIndexMinPQ.HeapIterator and throw a exception", e!=null);
		}
    	
    	// Testing remove() in MyIndexMinPQ.HeapIterator
    	try {
    		PQHeap2.remove();
		} catch (Exception e) {
			assertTrue(" Testing remove() in MyIndexMinPQ.HeapIterator and throw a exception", e!=null);
		}

    	
    }
    
    
    //~ Testing CompetitionFloydWarshall.timeRequiredforCompetition()........................................................
    @Test
    public void testCompetitionFloydWarshall_timeRequiredforCompetition() {
    	CompetitionFloydWarshall fw = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);
    	assertEquals("Test tinyEWD", 38, fw.timeRequiredforCompetition());
    	fw = new CompetitionFloydWarshall("1000EWD.txt", 50, 75, 100);
    	assertEquals("Test 1000EWD", 28, fw.timeRequiredforCompetition());
    }
}
