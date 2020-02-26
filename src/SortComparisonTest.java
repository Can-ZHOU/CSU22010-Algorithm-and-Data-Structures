import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
*  Test class for SortComparison.java
*
*  @author Can Zhou (19324118 zhouc@tcd.ie haven't share code or write code for others)
*  @version HT 2020
*/

//-------------------------------------------------------------------------

/**
 * Version Control :
 * I have used git and connected it to GitHub: https://github.com/Can-ZHOU/CSU22010-Algorithm-and-Data-Structures
 * Version control has been used sensibly ¨C using meaningful commit messages, committing changes frequently at appropriate points.
 * The screenshot of the history of commits in my GitHub repository has been submitted in both webcat and blackbroad.
 *  
 *  
 * Time Comparison: [All values are in Milliseconds (ms) and are the average time from 10 experiments' results]
 * 
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * | Milliseconds (ms)   | Insert  | Selection | Merge Recursive | Merge Iterative | Quick   |
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * | 10   random         | 0.07269 | 0.00244   | 0.00405         | 0.00467         | 0.00176 |
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * | 100  random         | 0.14941 | 0.1507    | 0.03422         | 0.04443         | 0.02144 |
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * | 1000 random         | 1.27526 | 1.06997   | 0.16305         | 0.17827         | 0.0995  |
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * | 1000 few unique     | 1.21071 | 1.03825   | 0.15794         | 0.17852         | 0.11166 |
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * | 1000 nearly ordered | 1.05467 | 1.40202   | 0.14005         | 0.14995         | 0.32527 |
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * | 1000 reverse order  | 1.32777 | 1.49313   | 0.12187         | 0.13343         | 1.24076 |
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * | 1000 sorted         | 0.91956 | 1.12978   | 0.12002         | 0.14556         | 0.93637 |
 * |---------------------+---------+-----------+-----------------+-----------------+---------|
 * 
 * Questions:
 * a. Which of the sorting algorithms does the order of input have an impact on? Why?
 * 	  ANSWER :
 * 	  Two algorithms -- Insertion Sort and Quick Sort will be heavily affected by the order of input.
 * 	  Insertion Sort: If the input array is sorted, it will cause the best case that there won't have any swap while sorting.
 * 					  But if reverse the input list, it will cause the worst the case that all iterations in inner loop will cost a swap.
 * 					  Time complexity: Best --> O(n), Worst --> O(n^2).
 *    Quick Sort:     If the array is in this order: the pivot element divides the list into two equal halves by coming exactly in the middle position, it will cause the best case.
 *    				  But if array is already sorted in same order or reverse order, it will cause the worst case.
 *    			      Time complexity: Best --> O(n log(n)), Worst --> O(n^2).
 *    Other algorithms like Selection Sort and Merge Sort are not really affected by the order of input.
 *    
 * b. Which algorithm has the biggest difference between the best and worst performance, based on the type of input, for the input of size 1000? Why?
 * 	  ANSWER:
 *    Insertion Sort.
 *    After comparing the different types of input, 
 *    we can find that for the Insertion Sort, the sorted array has the best performance and the array with reverse order has the worst performance.
 *    Reason: Insertion sort performs two operations: it scans through the list, comparing each pair of elements, 
 *            										  and it swaps elements if they are out of order. 
 *            										  Each operation contributes to the running time of the algorithm.
 *    		  In the best case:  The input array is already in sorted order, 
 *              			     insertion sort compares O(n) elements and performs no swaps.
 *              			     Therefore, in the best case, insertion sort runs in O(n) time.
 *      	  In the worst case: The input array is in decreasing/reverse order.
 *      						 Each comparison will cost a swap.
 *      						 Therefore, in the worst case, insertion sort runs in O(n^2) time.
 *
 * c. Which algorithm has the best/worst scalability, i.e., the difference in performance time based on the input size? Please consider only input files with random order for this answer.
 *    ANSWER:
 *    Merge sort has the best scalability.
 *    Selection sort has the worst scalability.
 *    I think algorithm's scalability is related with time complexity.
 *    Larger time complexity will have worse scalability.
 *    As in average/random, Merge sort's time complexity is O(n log(n)) while selection sort is O(n^2),
 *    when input size (n) is changing, selection sort will have more difference in performance time based on the input size than merge sort which causes worse scalability.
 *    
 * d. Did you observe any difference between iterative and recursive implementations of merge sort?
 *    ANSWER:
 *    There is a slight difference between them that recursive implementation is a slightly faster than iterative.
 *    I have done some research and one of explanations is because of caching improved performances.
 *    Here is the reference link: https://stackoverflow.com/a/17417822/10596389
 *    
 * e. Which algorithm is the fastest for each of the 7 input files?
 *    ANSWER:
 *    1> 10 random: 		  Quick Sort 
 *    				 		  Reason:				 
 *    				 		  In the random and average case, quick sort has the best performance in time complexity.
 *    2> 100 random: 		  Quick Sort 
 *    				 		  Reason:				 
 *    				 		  In the random and average case, quick sort has the best performance in time complexity.
 *    3> 1000 random: 		  Quick Sort
 *    				 		  Reason:				 
 *    				 		  In the random and average case, quick sort has the best performance in time complexity.
 *    4> 1000 few unique: 	  Quick Sort
 *    				 		  Reason:				 
 *    				 		  In the average case, quick sort has the best performance in time complexity.
 *    5> 1000 nearly ordered: Merge Recursive 
 *    						  Reason:
 *    						  The worst case for quick sort and order won't impact for merge sort which also has the same time complexity as quick sort.
 *    6> 1000 reverse order:  Merge Recursive
 *    						  Reason:
 *    						  The worst case for quick sort and order won't impact for merge sort which also has the same time complexity as quick sort.
 *    7> 1000 sorted: 		  Merge Recursive
 *    						  Reason:
 *    						  The worst case for quick sort and order won't impact for merge sort which also has the same time complexity as quick sort.
 *    
 */

//-------------------------------------------------------------------------
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {
    	double[] a = null;
    	
    	// Test insertionSort
    	a = SortComparison.insertionSort(a);
    	assertNull("Test empty array for insertSort", a);
    	
    	// Test selectionSort
    	a = null;
    	a = SortComparison.selectionSort(a);
    	assertNull("Test empty array for selectionSort", a);
    	
    	// Test quickSort
    	a = null;
    	a = SortComparison.quickSort(a);
    	assertNull("Test empty array for quickSort", a);
    	
    	// Test mergeSortIterative
    	a = null;
    	a = SortComparison.mergeSortIterative(a);
    	assertNull("Test empty array for mergeSortIterative", a);
    	
    	// Test mergeSortRecursive
    	a = null;
    	a = SortComparison.mergeSortRecursive(a);
    	assertNull("Test empty array for mergeSortRecursive", a);
    }
    
    @Test
    public void testInsertionSort() {
    	
    	double[] a = {5, 2, 1, 3, 4};
    	double[] a_sorted = {1, 2, 3, 4, 5};
    	
    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
    	
    	double[] c = {1.0};
    	
    	double delta = 0;
    	
    	a = SortComparison.insertionSort(a);
    	assertArrayEquals("Test input as an integer array", a_sorted, a, delta);
    	
    	b = SortComparison.insertionSort(b);
    	assertArrayEquals("Test input as a decimal array", b_sorted, b, delta);
    	
    	c = SortComparison.insertionSort(c);
    	assertArrayEquals("Test input array only has one element", c, c, delta);
    	
    }
    
    @Test
    public void testSelectionSort() {
    	
    	double[] a = {5, 2, 1, 3, 4};
    	double[] a_sorted = {1, 2, 3, 4, 5};
    	
    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
    	
    	double[] c = {1.0};
    	
    	double delta = 0;
    	
    	a = SortComparison.selectionSort(a);
    	assertArrayEquals("Test input as an integer array", a_sorted, a, delta);
    	
    	b = SortComparison.selectionSort(b);
    	assertArrayEquals("Test input as a decimal array", b_sorted, b, delta);
    	
    	c = SortComparison.selectionSort(c);
    	assertArrayEquals("Test input array only has one element", c, c, delta);
    	
    }
    
    @Test
    public void testQuickSort() {
    	
    	double[] a = {5, 2, 1, 3, 4};
    	double[] a_sorted = {1, 2, 3, 4, 5};
    	
    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
    	
    	double[] c = {1.0};
    	
    	double[] d = {5.1, 2.5, 1.0, 3.4, 4.7, 2.3, 1.2, 4.1};
    	double[] d_sorted = {1.0, 1.2, 2.3, 2.5, 3.4, 4.1, 4.7, 5.1};
    	
    	double[] e = {10.0, -123.93, 261.34};
    	double[] e_sorted = {-123.93, 10.0, 261.34};

    	double delta = 0;
    	
    	a = SortComparison.quickSort(a);
    	assertArrayEquals("Test input as an integer array", a_sorted, a, delta);
    	
    	b = SortComparison.quickSort(b);
    	assertArrayEquals("Test input as a decimal array", b_sorted, b, delta);
    	
    	c = SortComparison.quickSort(c);
    	assertArrayEquals("Test input array only has one element", c, c, delta);
    	
    	d = SortComparison.quickSort(d);
    	assertArrayEquals("Test input as a complex decimal array", d_sorted, d, delta);
    	
    	e = SortComparison.quickSort(e);
    	assertArrayEquals("Test input as a complex decimal array", e_sorted, e, delta);
    	
    }
    
    @Test
    public void testMergeSortIterative() {
    	
    	double[] a = {5, 2, 1, 3, 4};
    	double[] a_sorted = {1, 2, 3, 4, 5};
    	
    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
    	
    	double[] c = {1.0};
    	
    	double delta = 0;
    	
    	a = SortComparison.mergeSortIterative(a);
    	assertArrayEquals("Test input as an integer array", a_sorted, a, delta);
    	
    	b = SortComparison.mergeSortIterative(b);
    	assertArrayEquals("Test input as a decimal array", b_sorted, b, delta);
    	
    	c = SortComparison.mergeSortIterative(c);
    	assertArrayEquals("Test input array only has one element", c, c, delta);
    	
    }
    
    @Test
    public void testMergeSortRecursive() {
    	
    	double[] a = {5, 2, 1, 3, 4};
    	double[] a_sorted = {1, 2, 3, 4, 5};
    	
    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
    	
    	double[] c = {1.0};
    	
    	double delta = 0;
    	
    	a = SortComparison.mergeSortRecursive(a);
    	assertArrayEquals("Test input as an integer array", a_sorted, a, delta);
    	
    	b = SortComparison.mergeSortRecursive(b);
    	assertArrayEquals("Test input as a decimal array", b_sorted, b, delta);
    	
    	c = SortComparison.mergeSortRecursive(c);
    	assertArrayEquals("Test input array only has one element", c, c, delta);
    	
    }
    
    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     * @throws IOException 
     *
     */
//    public static void main(String[] args) throws IOException {
//    	
//    //~ Read file ---------------------------------------------
//    	Scanner sc = new Scanner(System.in); 
//        System.out.println("Please input file name: "); 
//        String fileName = sc.nextLine();
//        sc.close();
//        
//        File file = new File(fileName);
//        
//        BufferedReader reader = null;
//        ArrayList<Double> list = new ArrayList<Double>();
//        
//        try {
//        	reader = new BufferedReader(new FileReader(file));
//        	String tempString;
//        	while ((tempString = reader.readLine()) != null) {
//        		list.add(Double.parseDouble(tempString));
//        	}
//        	reader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        	if(reader != null) {
//        		reader.close();
//        	}
//        }
//        
//    //~ Comparison -------------------------------------------
//        double[] a = new double[list.size()];
//        long timeSum = 0;
//        double average;
//        
//        // Insert
//        timeSum = 0;
//        average = 0;
//        for(int n=0; n<10; n++) {
//        	for(int i=0; i<list.size(); i++) {
//            	a[i] = list.get(i);
//            }
//            long startInsert = System.nanoTime();
//            SortComparison.insertionSort(a);
//            long endInsert = System.nanoTime();
//            timeSum += endInsert - startInsert;
//        }
//        average = (double)timeSum/(10.0 * 1000000);
//        System.out.println("The insertionSort average time is " + average + " ms");
//        
//        // Selection
//        timeSum = 0;
//        average = 0;
//        for(int n=0; n<10; n++) {
//        	for(int i=0; i<list.size(); i++) {
//            	a[i] = list.get(i);
//            }
//            long startInsert = System.nanoTime();
//            SortComparison.selectionSort(a);
//            long endInsert = System.nanoTime();
//            timeSum += endInsert - startInsert;
//        }
//        average = (double)timeSum/(10.0 * 1000000);
//        System.out.println("The selectionSort average time is " + average + " ms");
//        
//        // Merge Recursive
//        timeSum = 0;
//        average = 0;
//        for(int n=0; n<10; n++) {
//        	for(int i=0; i<list.size(); i++) {
//            	a[i] = list.get(i);
//            }
//            long startInsert = System.nanoTime();
//            SortComparison.mergeSortRecursive(a);
//            long endInsert = System.nanoTime();
//            timeSum += endInsert - startInsert;
//        }
//        average = (double)timeSum/(10.0 * 1000000);
//        System.out.println("The mergeSortRecursive average time is " + average + " ms");
//        
//        // Merge Iterative
//        timeSum = 0;
//        average = 0;
//        for(int n=0; n<10; n++) {
//        	for(int i=0; i<list.size(); i++) {
//            	a[i] = list.get(i);
//            }
//            long startInsert = System.nanoTime();
//            SortComparison.mergeSortIterative(a);
//            long endInsert = System.nanoTime();
//            timeSum += endInsert - startInsert;
//        }
//        average = (double)timeSum/(10.0 * 1000000);
//        System.out.println("The mergeSortIterative average time is " + average + " ms");
//        
//        // Quick
//        timeSum = 0;
//        average = 0;
//        for(int n=0; n<10; n++) {
//        	for(int i=0; i<list.size(); i++) {
//            	a[i] = list.get(i);
//            }
//            long startInsert = System.nanoTime();
//            SortComparison.quickSort(a);
//            long endInsert = System.nanoTime();
//            timeSum += endInsert - startInsert;
//        }
//        average = (double)timeSum/(10.0 * 1000000);
//        System.out.println("The quickSort average time is " + average + " ms");
//    }


}

