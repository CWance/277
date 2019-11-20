import java.util.ArrayList;

/**
 * @author Christian Wance 012306864
 * 
 *         Makes a Heap class that can hold any comparable class
 */
public class Heap<T extends Comparable<T>> {

	private ArrayList<T> heap;

	/**
	 * Constructor
	 */
	public Heap() {

		heap = new ArrayList<T>();

	}

	/**
	 * Returns size of heap
	 * @return size
	 */
	public int size() {

		return heap.size();

	}

	/**
	 * returns boolean if heap is empty
	 * @return
	 */
	public boolean isEmpty() {

		return heap.isEmpty();

	}

	/**
	 * gets the first node
	 * @return T node
	 */
	public T getCurrent() {

		return heap.get(0);

	}

	/**
	 * gets the parent index
	 * @param i int child Index
	 * @return index
	 */
	private int getPLoc(int i) {

		return (i - 1) / 2;

	}

	/**
	 * gets the left child index
	 * @param i int parent Index
	 * @return index
	 */
	
	private int getLCLoc(int i) {

		return 2 * i + 1;

	}

	
	/**
	 * gets the left child index
	 * @param i int parent Index
	 * @return index
	 */
	
	private int getRCLoc(int i) {

		return 2 * i + 2;

	}

	/**
	 * Adds a node to the heap
	 * @param i T item
	 */
	public void addItem(T i) {

		System.out.println(i);
		heap.add(i);
		int index = heap.size() - 1;
		while (index > 0 && heap.get(getPLoc(index)).compareTo(i) > 0) {
			heap.set(index, heap.get(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, i);
	}

	/**
	 * ToString for Heap
	 * Return String heap
	 */
	public String toString() {
		// returns string in tree order not sorted order
		String s = "";
		for (int i = 0; i < heap.size(); i++) {
			s += heap.get(i).toString() + " ";
		}
		return s;
	}

	/**
	 * Removes the top node, then finds the smallest node
	 * @return first Node
	 */
	public T removeItem() {
		T min = heap.get(0);
		int index = heap.size() - 1;
		T last = heap.remove(index);
		if (index > 0) {
			heap.set(0, last);
			T root = heap.get(0);
			
			int end = heap.size() - 1;
			index = 0;
			boolean done = false;
			while (!done) {
				// check if left exists
				if (getLCLoc(index) <= end) {
					T child = heap.get(getLCLoc(index));
					int childLoc = getLCLoc(index);
					// check if right exists
					if (getRCLoc(index) <= end) {
						if (heap.get(getRCLoc(index)).compareTo(child) < 0) {
							child = heap.get(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if (child.compareTo(root) < 0) {
						heap.set(index, child);
						index = childLoc;
					} else {
						done = true;
					}
				} else { // no children
					done = true;
				}
			}

			heap.set(index, root);
		}
		return min;
	}
}