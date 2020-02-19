import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
    	Scanner sc = new Scanner(System.in); 
        System.out.println("Please input file name: "); 
        String fileName = sc.nextLine();
        sc.close();
        
        try {
			InputStream file = new FileInputStream(fileName);
			int size = file.available();
			
			for(int i=0; i<size; i++) {
				
			}
			
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
    }


}

