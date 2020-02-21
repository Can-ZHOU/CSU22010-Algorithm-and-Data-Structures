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
 * Time Comparison [All values are in Nano Seconds (ns) and are the average time from 10 experiments]
 * 
 * | Nano Seconds (ns)   | Insert    | Selection   | Merge Recursive | Merge Iterative | Quick      |
 * |---------------------|-----------|-------------|-----------------|-----------------|------------|
 * | 10   random         | 72690.0   | 2440.0      | 5710.0          | 6500.0          | 1760.0     |
 * | 100  random         | 149410.0  | 150700.0    | 34220.0         | 44430.0         | 21440.0    |
 * | 1000 random         | 3248300.0 | 2368166.667 | 253433.333      | 248600.0        | 180633.333 |
 * | 1000 few unique     | 1748290.0 | 1038250.0   | 157940.0        | 178520.0        | 111660.0   |
 * | 1000 nearly ordered | 1482530.0 | 1402020.0   | 235100.0        | 255060.0        | 272550.0   |
 * | 1000 reverse order  | 1327770.0 | 1493130.0   | 121870.0        | 133430.0        | 1240760.0  |
 * | 1000 sorted         | 1156040.0 | 1129780.0   | 120020.0        | 145560.0        | 827120.0   |
 * 
 * Questions:
 * a. 
 */

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2020
 */
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
    public static void main(String[] args) throws IOException {
    	
    //~ Read file ---------------------------------------------
    	Scanner sc = new Scanner(System.in); 
        System.out.println("Please input file name: "); 
        String fileName = sc.nextLine();
        sc.close();
        
        File file = new File(fileName);
        
        BufferedReader reader = null;
        ArrayList<Double> list = new ArrayList<Double>();
        
        try {
        	reader = new BufferedReader(new FileReader(file));
        	String tempString;
        	while ((tempString = reader.readLine()) != null) {
        		list.add(Double.parseDouble(tempString));
        	}
        	reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(reader != null) {
        		reader.close();
        	}
        }
        
    //~ Comparison -------------------------------------------
        double[] a = new double[list.size()];
        long timeSum = 0;
        double average;
        
        // Insert
        timeSum = 0;
        average = 0;
        for(int n=0; n<10; n++) {
        	for(int i=0; i<list.size(); i++) {
            	a[i] = list.get(i);
            }
            long startInsert = System.nanoTime();
            SortComparison.insertionSort(a);
            long endInsert = System.nanoTime();
            timeSum += endInsert - startInsert;
        }
        average = (double)timeSum/10.0;
        System.out.println("The insertionSort average time is " + average + " ns");
        
        // Selection
        timeSum = 0;
        average = 0;
        for(int n=0; n<10; n++) {
        	for(int i=0; i<list.size(); i++) {
            	a[i] = list.get(i);
            }
            long startInsert = System.nanoTime();
            SortComparison.selectionSort(a);
            long endInsert = System.nanoTime();
            timeSum += endInsert - startInsert;
        }
        average = (double)timeSum/10.0;
        System.out.println("The selectionSort average time is " + average + " ns");
        
        // Merge Recursive
        timeSum = 0;
        average = 0;
        for(int n=0; n<10; n++) {
        	for(int i=0; i<list.size(); i++) {
            	a[i] = list.get(i);
            }
            long startInsert = System.nanoTime();
            SortComparison.mergeSortRecursive(a);
            long endInsert = System.nanoTime();
            timeSum += endInsert - startInsert;
        }
        average = (double)timeSum/10.0;
        System.out.println("The mergeSortRecursive average time is " + average + " ns");
        
        // Merge Iterative
        timeSum = 0;
        average = 0;
        for(int n=0; n<10; n++) {
        	for(int i=0; i<list.size(); i++) {
            	a[i] = list.get(i);
            }
            long startInsert = System.nanoTime();
            SortComparison.mergeSortIterative(a);
            long endInsert = System.nanoTime();
            timeSum += endInsert - startInsert;
        }
        average = (double)timeSum/10.0;
        System.out.println("The mergeSortIterative average time is " + average + " ns");
        
        // Quick
        timeSum = 0;
        average = 0;
        for(int n=0; n<10; n++) {
        	for(int i=0; i<list.size(); i++) {
            	a[i] = list.get(i);
            }
            long startInsert = System.nanoTime();
            SortComparison.quickSort(a);
            long endInsert = System.nanoTime();
            timeSum += endInsert - startInsert;
        }
        average = (double)timeSum/10.0;
        System.out.println("The quickSort average time is " + average + " ns");
    }


}

