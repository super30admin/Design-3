
//Time Complexity: O(1)
//Space Complexity: O(capacity)
import java.util.*;

class LRUCache {
	class Node {
		int key;
		int val;
		Node next;
		Node prev;

		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}

	private void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	private void addTohead(Node node) {
		node.next = head.next;
		node.prev = head;
		head.next = node;
		node.next.prev = node;
	}

	HashMap<Integer, Node> map = new HashMap<>();
	int capacity;
	Node head;
	Node tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
		head = new Node(-1, -1);
		tail = new Node(-1, -1);
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		if (!map.containsKey(key))
			return -1;
		Node node = map.get(key);
		removeNode(node);
		addTohead(node);
		return node.val;
	}

	public void put(int key, int value) {
		// case 1
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.val = value;
			removeNode(node);
			addTohead(node);
		}
		// case 2
		else {
			if (capacity == map.size()) {
				Node tailPrev = tail.prev;
				removeNode(tailPrev);
				map.remove(tailPrev.key);
			}
			Node newNode = new Node(key, value);
			map.put(key, newNode);
			addTohead(newNode);
		}
	}
}
