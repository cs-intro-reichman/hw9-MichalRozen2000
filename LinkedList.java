

/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		//// Replace the following statement with your code
		Node currentNode = first;
		for (int i = 0 ; i < index ; i++) {
			currentNode = currentNode.next;
		}
		
		return currentNode;
	
	

	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Index is out of bounds.");
		}

		Node newNode = new Node(block);
		if (index == 0) {
			newNode.next = first;
			first = newNode;
			if (last == null) {
				last = newNode;
			}
			last = newNode;
		}
		
		else if (index == size) {
			if (last != null) {
				last.next = newNode;
			}
			last = newNode;
		} 
		else {
			Node current = first;
			for (int i = 0 ; i < size ; i++) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}
		size++;
		
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		add(size, block);
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		add(0, block);

	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index > size || index < 0) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node currentNode = first;
		for (int i = 0 ; i < index ; i++) {
			currentNode = currentNode.next;
			return currentNode.block;
		}
			
		return null;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		for (int i = 0 ; i < size ; i++) {
			Node currentNode = getNode(i);
			if (currentNode.block.equals(block)) {
				return i;

			}
		}
		
		
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (node == null) {
			return;
		}
			if (node.equals(first)) {
				first = first.next;
				if (first == null) {
					last = null;
				}
			}
			else {
				Node current = first;
				while (current != null && current.next != node) {
					current = current.next;      
			}
			if (current != null && current.next.equals(node)) {
				current.next = node.next;
				if (node.equals(last)) {
					last = current;
				}
		    }
		}
		size--;

	}
		
	

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		Node current = first;
		if (index == 0) {
			remove(current);	
		}
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		remove(current.next);	

		}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		if (block == null) {
			throw new IllegalArgumentException("Memory block must have a value");
		}
		Node current = first;
		if (current != null && current.block.equals(block)) {
			first = current.next;
			if (first == null) {
				last = null;
			}
			size--;
			return;
		}
		while (current != null && current.next != null) {
			if (current.next.block.equals(block)) {
				current.next = current.next.next;
			if (current.next == null) {
				last = current;
			}
			size--;
			return;
		}
		current = current.next;
	}	
	throw new IllegalArgumentException("Memory block is not in the list.");
}

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		//// Replace the following statement with your code
		return "";
	}
}