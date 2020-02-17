import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    	
//    	// Test selectionSort
//    	a = {};
//    	a = SortComparison.selectionSort(a);
//    	assertNull("Test empty array for selectionSort", a);
//    	
//    	// Test quickSort
//    	a = {};
//    	a = SortComparison.quickSort(a);
//    	assertNull("Test empty array for quickSort", a);
//    	
//    	// Test mergeSortIterative
//    	a = null;
//    	a = SortComparison.mergeSortIterative(a);
//    	assertNull("Test empty array for mergeSortIterative", a);
//    	
//    	// Test mergeSortRecursive
//    	a = null;
//    	a = SortComparison.mergeSortRecursive(a);
//    	assertNull("Test empty array for mergeSortRecursive", a);
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
    
//    @Test
//    public void testSelectionSort() {
//    	
//    	double[] a = {5, 2, 1, 3, 4};
//    	double[] a_sorted = {1, 2, 3, 4, 5};
//    	
//    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
//    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
//    	
//    	double[] c = {1.0};
//    	
//    	a = SortComparison.selectionSort(a);
//    	assertEquals("Test input as an integer array", a, a_sorted);
//    	
//    	b = SortComparison.selectionSort(b);
//    	assertEquals("Test input as a decimal array", b, b_sorted);
//    	
//    	c = SortComparison.selectionSort(c);
//    	assertEquals("Test input array only has one element", c, c);
//    	
//    }
//    
//    @Test
//    public void testQuickSort() {
//    	
//    	double[] a = {5, 2, 1, 3, 4};
//    	double[] a_sorted = {1, 2, 3, 4, 5};
//    	
//    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
//    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
//    	
//    	double[] c = {1.0};
//    	
//    	a = SortComparison.quickSort(a);
//    	assertEquals("Test input as an integer array", a, a_sorted);
//    	
//    	b = SortComparison.quickSort(b);
//    	assertEquals("Test input as a decimal array", b, b_sorted);
//    	
//    	c = SortComparison.quickSort(c);
//    	assertEquals("Test input array only has one element", c, c);
//    	
//    }
//    
//    @Test
//    public void testMergeSortIterative() {
//    	
//    	double[] a = {5, 2, 1, 3, 4};
//    	double[] a_sorted = {1, 2, 3, 4, 5};
//    	
//    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
//    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
//    	
//    	double[] c = {1.0};
//    	
//    	a = SortComparison.mergeSortIterative(a);
//    	assertEquals("Test input as an integer array", a, a_sorted);
//    	
//    	b = SortComparison.mergeSortIterative(b);
//    	assertEquals("Test input as a decimal array", b, b_sorted);
//    	
//    	c = SortComparison.mergeSortIterative(c);
//    	assertEquals("Test input array only has one element", c, c);
//    	
//    }
//    
//    @Test
//    public void testMergeSortRecursive() {
//    	
//    	double[] a = {5, 2, 1, 3, 4};
//    	double[] a_sorted = {1, 2, 3, 4, 5};
//    	
//    	double[] b = {5.1, 2.5, 1.0, 3.4, 4.7};
//    	double[] b_sorted = {1.0, 2.5, 3.4, 4.7, 5.1};
//    	
//    	double[] c = {1.0};
//    	
//    	a = SortComparison.mergeSortRecursive(a);
//    	assertEquals("Test input as an integer array", a, a_sorted);
//    	
//    	b = SortComparison.mergeSortRecursive(b);
//    	assertEquals("Test input as a decimal array", b, b_sorted);
//    	
//    	c = SortComparison.mergeSortRecursive(c);
//    	assertEquals("Test input array only has one element", c, c);
//    	
//    }
//
}

