import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// -------------------------------------------------------------------------

/**
 *  Version Control :
 *  I have used git and connected it to GitHub: https://github.com/Can-ZHOU/CSU22010-Algorithm-and-Data-Structures
 *  Version control has been used sensibly ¨C using meaningful commit messages, committing changes frequently at appropriate points.
 *  The screenshot of the history of commits in my GitHub repository has been submitted in both webcat and blackbroad.
 * 
 *  Time Comparison and Question Answers are in the comment at the top of SortComparisonTest.java file.
 * 
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Can Zhou (19324118 zhouc@tcd.ie haven't share code or write code for others)
 *  @version HT 2020
 */

 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){
    	// If a is an empty array or only has one element, return a.
    	if(a==null || a.length < 2) {
    		return a;
    	}
    	
    	for(int i=0; i<a.length; i++) {
    		for(int j=i; j>0; j--) {
    			if(a[j] < a[j-1]) {
    				double tmp = a[j];
    				a[j] = a[j-1];
    				a[j-1] = tmp;
    			}
    		}
    	}
    	return a;
    }
	
	/**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
    	// If a is an empty array or only has one element, return a.
    	if(a==null || a.length < 2) {
    		return a;
    	}
    	
    	for(int i=0; i<a.length-1; i++) {
    		int min_index = i;
    		// Find element with min value in the unsorted list.
    		for(int j=i+1; j<a.length; j++) {
    			if(a[j] < a[min_index]) {
    				min_index = j;
    			}
    		}
    		// Swap i element and min element.
    		double tmp = a[i];
    		a[i] = a[min_index];
    		a[min_index] = tmp;
    	}
    	return a;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
    	// If a is an empty array or only has one element, return a.
    	if(a==null || a.length < 2) {
    		return a;
    	}
    	
    	// Call recursive quick sort recursive
    	recursiveQuick(a, 0, a.length-1);
    	return a;
    }
    
    private static void recursiveQuick(double a[], int lo, int hi) {
    	if(hi<=lo) {
    		return;
    	}
    	
    	// Partition
    	int pivotPos = partition(a, lo, hi);
    	
    	// Recursive
    	recursiveQuick(a, lo, pivotPos-1);
    	recursiveQuick(a, pivotPos+1, hi);
    }
    
    private static int partition(double a[], int lo, int hi) {
    	int i = lo;
    	int j = hi+1;
    	double pivot = a[lo];
    	while(true) {
    		while(a[++i] < pivot) {
    			if(i == hi) break;
    		}
    		while(a[--j] >= pivot) {
    			if(j == lo) break;	
    			
    		}
    		if(i >= j) break;
    		double temp = a[i];
    		a[i] = a[j];
    		a[j] = temp;
    	}
    	a[lo] = a[j];
    	a[j] = pivot;
    	return j;
    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {
    	// If a is an empty array or only has one element, return a.
    	if(a==null || a.length < 2) {
    		return a;
    	}
    	int N = a.length;
    	double[] aux = new double[N];
    	for(int sz=1; sz<N; sz = sz+sz) {
    		for(int lo = 0; lo<N-sz; lo+=sz+sz) {
    			mergeIterative(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    		}
    	}
    	
    	return a;
    }
    
    private static void mergeIterative (double a[], double aux[], int lo, int mid, int hi) {
    	// Copy
    	for(int k=lo; k<=hi; k++) {
    		aux[k] = a[k];
    	}
    	
    	// Merge
    	int i=lo, j=mid+1;
    	for(int k=lo; k<=hi; k++) {
    		if(i>mid) {
    			a[k] = aux[j++];
    		} else if(j>hi) {
    			a[k] = aux[i++];
    		} else if(aux[i]<=aux[j]) {
    			a[k] = aux[i++];
    		} else {
    			a[k] = aux[j++];
    		}
    	}
    }
    
    
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
    	// If a is an empty array or only has one element, return a.
    	if(a==null || a.length < 2) {
    		return a;
    	}
    	double[] aux = new double[a.length];
    	mergeSortRecursive(a, aux, 0, a.length-1);
    	return a;
    }
    
    private static void mergeSortRecursive(double a[], double aux[], int lo, int hi) {
    	if(hi<=lo) {
    		return;
    	}
    	
    	int mid = lo + (hi-lo)/2;
    	
    	mergeSortRecursive(a, aux, lo, mid);
    	mergeSortRecursive(a, aux, mid+1, hi);
    	
    	mergeRecursive(a, aux, lo, mid, hi);
    }
    
    private static void mergeRecursive(double a[], double aux[], int lo, int mid, int hi) {
    	// Copy
    	for(int k=lo; k<=hi; k++) {
    		aux[k] = a[k];
    	}
    	
    	// Merge
    	int i=lo, j=mid+1;
    	for(int k=lo; k<=hi; k++) {
    		if(i>mid) {
    			a[k] = aux[j++];
    		} else if(j>hi) {
    			a[k] = aux[i++];
    		} else if(aux[i]<=aux[j]) {
    			a[k] = aux[i++];
    		} else {
    			a[k] = aux[j++];
    		}
    	}
    }
    


//  public static void main(String[] args) throws IOException {
//	
////~ Read file ---------------------------------------------
//	Scanner sc = new Scanner(System.in); 
//    System.out.println("Please input file name: "); 
//    String fileName = sc.nextLine();
//    sc.close();
//    
//    File file = new File(fileName);
//    
//    BufferedReader reader = null;
//    ArrayList<Double> list = new ArrayList<Double>();
//    
//    try {
//    	reader = new BufferedReader(new FileReader(file));
//    	String tempString;
//    	while ((tempString = reader.readLine()) != null) {
//    		list.add(Double.parseDouble(tempString));
//    	}
//    	reader.close();
//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//    	if(reader != null) {
//    		reader.close();
//    	}
//    }
//    
////~ Comparison -------------------------------------------
//    double[] a = new double[list.size()];
//    long timeSum = 0;
//    double average;
//    
//    // Insert
//    timeSum = 0;
//    average = 0;
//    for(int n=0; n<10; n++) {
//    	for(int i=0; i<list.size(); i++) {
//        	a[i] = list.get(i);
//        }
//        long startInsert = System.nanoTime();
//        SortComparison.insertionSort(a);
//        long endInsert = System.nanoTime();
//        timeSum += endInsert - startInsert;
//    }
//    average = (double)timeSum/(10.0 * 1000000);
//    System.out.println("The insertionSort average time is " + average + " ms");
//    
//    // Selection
//    timeSum = 0;
//    average = 0;
//    for(int n=0; n<10; n++) {
//    	for(int i=0; i<list.size(); i++) {
//        	a[i] = list.get(i);
//        }
//        long startInsert = System.nanoTime();
//        SortComparison.selectionSort(a);
//        long endInsert = System.nanoTime();
//        timeSum += endInsert - startInsert;
//    }
//    average = (double)timeSum/(10.0 * 1000000);
//    System.out.println("The selectionSort average time is " + average + " ms");
//    
//    // Merge Recursive
//    timeSum = 0;
//    average = 0;
//    for(int n=0; n<10; n++) {
//    	for(int i=0; i<list.size(); i++) {
//        	a[i] = list.get(i);
//        }
//        long startInsert = System.nanoTime();
//        SortComparison.mergeSortRecursive(a);
//        long endInsert = System.nanoTime();
//        timeSum += endInsert - startInsert;
//    }
//    average = (double)timeSum/(10.0 * 1000000);
//    System.out.println("The mergeSortRecursive average time is " + average + " ms");
//    
//    // Merge Iterative
//    timeSum = 0;
//    average = 0;
//    for(int n=0; n<10; n++) {
//    	for(int i=0; i<list.size(); i++) {
//        	a[i] = list.get(i);
//        }
//        long startInsert = System.nanoTime();
//        SortComparison.mergeSortIterative(a);
//        long endInsert = System.nanoTime();
//        timeSum += endInsert - startInsert;
//    }
//    average = (double)timeSum/(10.0 * 1000000);
//    System.out.println("The mergeSortIterative average time is " + average + " ms");
//    
//    // Quick
//    timeSum = 0;
//    average = 0;
//    for(int n=0; n<10; n++) {
//    	for(int i=0; i<list.size(); i++) {
//        	a[i] = list.get(i);
//        }
//        long startInsert = System.nanoTime();
//        SortComparison.quickSort(a);
//        long endInsert = System.nanoTime();
//        timeSum += endInsert - startInsert;
//    }
//    average = (double)timeSum/(10.0 * 1000000);
//    System.out.println("The quickSort average time is " + average + " ms");
//}

 }//end class

