import java.util.Iterator;

/**
 * A generic bag or multiset, implemented using a singly linked list.
 */
public class MyBag<Item> implements Iterable<Item> {
		
		private Node<Item> first;
		public int n;

		public class Node<Item> {
			private Item item;
			private Node<Item> next;
		}

		public MyBag() {
			first = null;
			n = 0;
		}

		// Appends an Item to the top of the Bag
		public void add(Item item) {
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			n++;
		}

		// Returns an iterator that iterates over the items in this bag in arbitrary order.
		public Iterator<Item> iterator() {
			return new ListIterator<Item>(first);
		}

		// An iterator, doesn't implement remove() since it's optional
		public class ListIterator<Item> implements Iterator<Item> {
			private Node<Item> current;

			public ListIterator(Node<Item> first) {
				current = first;
			}

			public boolean hasNext() {
				return current != null;
			}

			public Item next() {
				Item item = current.item;
				current = current.next;
				return item;
			}
		}

}