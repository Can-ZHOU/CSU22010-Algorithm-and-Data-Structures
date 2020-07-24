import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Can Zhou (19324118 zhouc@tcd.ie haven't share code or write code for others)
 * 
 * Discuss the differences between Dijkstra and Floyd Warshall's implementation and performance.
 * 
 * 0. Implementation:
 * 	a) CompetitionDijkstra:
 * 		As in Dijkstra's algorithm, only one vertex is allocated to be the source node,
 * 		in order to solve this all-pairs shortest path problem, we need to go through all the vertices and implement Dijkstra algorithm for each vertex.
 * 		I implemented the Dijkstra's algorithm with a binary heap Priority Queue in an Edge-Weighted Directional Graph.
 * 		Since Dijkstra's shortest path algorithm requires a graph structure to contain both edge weightings and directions for each vertex,
 * 		I chose the Edge-Weighted Directional Graph as the data structure which adapted from the book (link: https://algs4.cs.princeton.edu/44sp/).
 * 		This can be found within the MyEdgeWeightedDiGraph.java. 
 * 		MyEdgeWeightedDiGraph allows us to access the adjacency metric of vertices efficiently without storing unnecessary data by using another data structure from the book -- the Bag of Directed Edges data structure.
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
 *  	Floyd-Warhsall's algorithm can find the shortest path from all nodes to every other node.
 *  	I used a 2D array to represent the graph in CompetitionFloydWarshall.
 *  	This is because the Floyd Warshall algorithm works based of an adjacency matrix and a 2D array can be considered as a matrix. 
 *  	It is a V*V 2D array (where V is number of vertices) and is of the form [from][to] and value is the weight between two vertices.
 *  
 *  
 * 1. Performance:
 * 	a) Time complexity in theory:
 * 		- Dijkstra:
 * 			Dijkstra performance depends on PQ implementation.
 * 			As I used binary heap to implement the PQ, 
 * 			the time complexity for finding the shortest path from one source node is O(E*logV) where E is the number of Edges and V is the number of vertices.
 * 			However this problem is an all-pairs shortest path problem 
 * 			which means we need to go through all the vertices.
 * 			So, the time complexity in this problem is V * O(E*logV) = O(E*V*logV). (where E is the number of Edges and V is the number of vertices)
 * 			As Dijkstra's time complexity is O(E*V*logV), when the input is a sparse graph, the E is not large, Dijkstra will faster.
 * 
 * 		- Floyd Warshall:
 * 			Floyd-Warshall is based on Dynamic programming which uses 3 nested for loop.
 * 			So, the time complexity is O(V^3) where V is the number of vertices.
 * 			As Floyd Warshall's time complexity is O(V^3), when the input is a dense graph, large E won't affect Floyd Warshall's running time, Floyd Warshall will faster.
 * 
 * 	b) Real running time in different inputs:
 * 		- In a sparse graph (input-F.txt [has 13 vertices and 13 edges], results are the average time from 10 tests)
 * 			Average time with Dijkstra       is 565380 nanosecond.
 * 			Average time with Floyd Warshall is 641520 nanosecond.
 * 			Dijkstra is faster when the input is a sparse graph.
 * 
 * 		- In a dense graph (input-N.txt [has 10 vertices and 90 edges], results are the average time from 10 tests)
 * 			Average time with Dijkstra       is 919280 nanosecond.
 * 			Average time with Floyd Warshall is 537920 nanosecond.
 * 			Floyd Warshall is faster when the input is a dense graph.
 * 
 * 		- In a tiny graph (tinyEWD.txt, [has 8 vertices and 15 edges], results are the average time from 10 tests)
 * 			Average time with Dijkstra       is 399600 nanosecond.
 * 			Average time with Floyd Warshall is 274180 nanosecond.
 * 			Floyd Warshall is faster in this certain input.
 * 
 * 		- In a large graph (1000EWD.txt, [has 1000 vertices and 16866 edges], results are the average time from 10 tests)
 * 			Average time with Dijkstra       is 715444870 nanosecond.
 * 			Average time with Floyd Warshall is 453808010 nanosecond.
 * 			Floyd Warshall is faster in this certain input.
 * 
 * 		As the results above, we can see 
 * 		when the input is a sparse graph, Dijkstra is faster;
 * 		when the input is a dense graph, Floyd Warshall is faster.
 * 		when the vertices size is tiny, Floyd Warshall may faster as Dijkstra's implementation will cost longer time than Floyd Warshall which only needs a 2D array.
 * 		when the vertices size is large, we need to consider the graph type (sparse or dense),
 * 		if this large graph has huge number of edges (dense graph), then Floyd Warshall is faster.
 * 		else (sparse graph) Dijkstra will faster.
 * 
 * 	c) When the weight is negative.
 * 		- Dijkstra: cannot be used.
 * 		- Floyd Warshall: weight can be negative but graph cannot have negative cycle.
 * 
 * 
 * 2. When to use Dijkstra or Floyd Warshall?
 * 	a) When the input is a sparse graph:
 * 		- I would use Dijkstra, as its time complexity is O(E*V*logV), the E in sparse graph is not large.
 * 
 * 	b) When the input is a dense graph:
 * 		- I would use Floyd Warshall, as its time complexity is O(V^3), large E in dense graph won't affect Floyd Warshall's running time.
 * 	
 * 	c) When the input size (vertices number) is tiny:
 * 		- I would use Floyd Warshall, as the data structure used to implement Dijkstra is more complex and will cost longer time than Floyd Warshall which only needs a 2D array.
 * 
 *  d) When the input size (vertices number) is large:
 *  	- If the input is a sparse graph: Dijkstra.
 *  	- If the input is a dense graph: Floyd Warshall.
 * 
 * 	e) When the weights in graph have negative value:
 * 		- I would use Floyd Warshall, as the weights in Dijkstra cannot be negative, but if the graph has negative cycle, then the Floyd Warshall cannot be used neither.
 * 
 * 
 * 
 *3. Additional files:
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
 *  
 *  e) Test_empty.txt
 *  	- a test file which has 0 vertex and 0 edge.
 *  
 *  f) Test_emptyEdge.txt
 *  	- a test file which has 3 vertices and 0 edge.
 *  
 *  g) Test_hasNegativeCycle.txt
 *  	- a test file which has negative cycle.
 * 		- 3
 * 		  3
 * 		  0 1 -0.5
 * 		  1 2 -0.75
 * 		  2 0 -0.2
 * 
 *  h) Given test files
 *  	- tinyEWD.txt
 *  	- 1000EWD.txt
 *  	- input-F.txt
 *  	- input-N.txt
 */


@RunWith(JUnit4.class)
public class CompetitionTests {
	
	//~ Constructor .......................................................................................................
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
    
    //~ Testing in real running time
    @Test
    public void testTiming() {
    
    	// Sparse graph
    	long start = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            CompetitionDijkstra dijkstra = new CompetitionDijkstra("input-F.txt", 50, 50, 50);
            dijkstra.timeRequiredforCompetition();
        }
        long finish = System.nanoTime();
        long time = (finish - start)/10;
        //System.out.println("Average time with Dijkstra " + time);
        
        start = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("input-F.txt", 50, 50, 50);
            floyd.timeRequiredforCompetition();
        }
        finish = System.nanoTime();
        time = (finish - start)/10;
        //System.out.println("Average time with Floyd Warshall " + time);
        
        // Dense graph
        start = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("input-N.txt", 100, 100, 100);
            floyd.timeRequiredforCompetition();
        }
        finish = System.nanoTime();
        time = (finish - start)/10;
        System.out.println("Average time with Floyd Warshall " + time);
        
        start = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            CompetitionDijkstra dijkstra = new CompetitionDijkstra("input-N.txt", 50, 50, 50);
            dijkstra.timeRequiredforCompetition();
        }
        finish = System.nanoTime();
        time = (finish - start)/10;
        System.out.println("Average time with Dijkstra " + time);
        
        // Tiny input
        start = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", 100, 100, 100);
            floyd.timeRequiredforCompetition();
        }
        finish = System.nanoTime();
        time = (finish - start)/10;
        System.out.println("Average time with Floyd Warshall " + time);
        
        start = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 50, 50);
            dijkstra.timeRequiredforCompetition();
        }
        finish = System.nanoTime();
        time = (finish - start)/10;
        System.out.println("Average time with Dijkstra " + time);
        
        // large Input
        start = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("1000EWD.txt", 100, 100, 100);
            floyd.timeRequiredforCompetition();
        }
        finish = System.nanoTime();
        time = (finish - start)/10;
        System.out.println("Average time with Floyd Warshall " + time);
        
        start = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            CompetitionDijkstra dijkstra = new CompetitionDijkstra("1000EWD.txt", 50, 50, 50);
            dijkstra.timeRequiredforCompetition();
        }
        finish = System.nanoTime();
        time = (finish - start)/10;
        System.out.println("Average time with Dijkstra " + time);
    }
}
