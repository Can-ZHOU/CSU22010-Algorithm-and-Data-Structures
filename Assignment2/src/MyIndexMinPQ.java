import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Minimum-oriented indexed PQ implementation using a binary heap.
 */

public class MyIndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {

	private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i
	
	@SuppressWarnings("unchecked")
	public MyIndexMinPQ(int maxN) {

		this.maxN = maxN;
		n = 0;
		keys = (Key[]) new Comparable[maxN + 1]; 
		
		pq = new int[maxN + 1];
		qp = new int[maxN + 1]; 
		
		//Initialize all indices in PQ to -1
		for (int i = 0; i <= maxN; i++)
			qp[i] = -1;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public boolean contains(int i) {
		return qp[i] != -1;
	}

	public void insert(int i, Key key) {
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = key;
		swim(n);
	}

	public int delMin() {
		int min = pq[1];
		exch(1, n--);
		sink(1);
		qp[min] = -1;     // delete
		keys[min] = null; // to help with garbage collection
		pq[n + 1] = -1;   // not needed
		return min;
	}

	public void decreaseKey(int i, Key key) {

		keys[i] = key;
		swim(qp[i]);
	}


	private boolean greater(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	}

	private void exch(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}

	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(j, j + 1)) {
				j++;
			}
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	
	@Override
	public Iterator<Integer> iterator() {
		return new HeapIterator();
	}
	
	public class HeapIterator implements Iterator<Integer> {
		
		public MyIndexMinPQ<Key> copy;

		
		public HeapIterator() {
			copy = new MyIndexMinPQ<Key>(pq.length - 1);
			for (int i = 1; i <= n; i++)
				copy.insert(pq[i], keys[pq[i]]);
		}

		public boolean hasNext() {
			return !copy.isEmpty();
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return copy.delMin();
		}
	}

}
