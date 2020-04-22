import java.util.Iterator;

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

		//Appends an Item to the top of the Bag
		public void add(Item item) {
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			n++;
		}

		public Iterator<Item> iterator() {
			return new ListIterator<Item>(first);
		}

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