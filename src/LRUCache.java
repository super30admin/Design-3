import java.util.HashMap;
import java.util.Map;

class LRUCache {
	Map<Integer, DLLNode> map = new HashMap<>();
	DLLNode head, tail;
	int capacity;

	/**
	 * Maintain a map with key to DLLNode to maintain the key, value pairs to track.
	 * Maintain head and tail nodes. All the nodes will be inserted between these
	 * two nodes. A new node will be added next to the head and the oldest node will
	 * be at the left of the tail. And map will have the integer to these nodes
	 * pairs.
	 */
	public LRUCache(int capacity) {
		this.capacity = capacity;
		head = new DLLNode();
		tail = new DLLNode();
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Check if the map has the key. If not, return null. Else get that node to
	 * return the value. Before that, we have to move it to the head since this
	 * would be the latest node.
	 * 
	 * TC: O(1) SC: O(N) assuming the hashmap has n pairs
	 */
	public int get(int key) {
		DLLNode node = map.get(key);
		if (node == null)
			return -1;
		moveToHead(node);
		return node.val;
	}

	/**
	 * CHeck if the key is already present, if so update the value of it and move it
	 * to the head. Else create a DLL node out of it and add the key, value to it.
	 * Then add it to the right of the head. And then check if the capacity is
	 * reached, then remove the tail node.
	 * 
	 * TC: O(1) SC: O(N) assuming the hashmap size as N
	 */
	public void put(int key, int value) {
		DLLNode node = map.get(key);
		if (node == null) {
			node = new DLLNode();
			node.key = key;
			node.val = value;
			addNode(node);
			map.put(key, node);
			if (map.size() > capacity) {
				DLLNode removed = removeTail();
				map.remove(removed.key);
			}
		} else {
			node.val = value;
			moveToHead(node);
		}
	}

	public void addNode(DLLNode node) {
		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;
	}

	public void removeNode(DLLNode node) {
		DLLNode next = node.next;
		DLLNode prev = node.prev;
		prev.next = next;
		next.prev = prev;
	}

	public void moveToHead(DLLNode node) {
		removeNode(node);
		addNode(node);
	}

	public DLLNode removeTail() {
		DLLNode last = tail.prev;
		removeNode(last);
		return last;
	}
}

class DLLNode {
	int key;
	int val;
	DLLNode prev;
	DLLNode next;
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */