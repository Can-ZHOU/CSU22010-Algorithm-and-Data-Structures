import java.util.Arrays;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Can Zhou (19324118 zhouc@tcd.ie)
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
    	if(a==null || a.length < 2) {
    		return a;
    	}
    	for(int i=0; i<a.length-1; i++) {
    		int min_index = i;
    		for(int j=i+1; j<a.length; j++) {
    			if(a[j] < a[min_index]) {
    				min_index = j;
    			}
    		}
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
    	if(a==null || a.length < 2) {
    		return a;
    	}
    	recursiveQuick(a, 0, a.length-1);
    	return a;
    }
    
    private static void recursiveQuick(double a[], int lo, int hi) {
    	if(hi<=lo) {
    		return;
    	}
    	int pivotPos = partition(a, lo, hi);
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
    			if(j == lo) 
    				break;	
    			
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
    	// copy
    	for(int k=lo; k<=hi; k++) {
    		aux[k] = a[k];
    	}
    	
    	// merge
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
    	// copy
    	for(int k=lo; k<=hi; k++) {
    		aux[k] = a[k];
    	}
    	
    	// merge
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
    


   


    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

 }//end class

