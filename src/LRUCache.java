import java.util.HashMap;

public class LRUCache {

	class Node {
		Node next;
		Node prev;
		int key;
		int val;

		Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}

	HashMap<Integer, Node> map = new HashMap<Integer, Node>();

	int capacity;
	Node head;
	Node tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		head = new Node(-1, -1); // dummy head node
		tail = new Node(-1, -1); // dummy tail node
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		if (!map.containsKey(key))
			return -1;

		Node node = map.get(key);
		removeNode(node);
		addToHead(node);
		return node.val;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.val = value;
			removeNode(node);
			addToHead(node);
		} else {
			Node newNode = new Node(key, value);
			if (capacity == map.size()) {
				Node tailPrev = tail.prev;
				removeNode(tailPrev);
				map.remove(tailPrev.key);

			}
			map.put(key, newNode);
			addToHead(newNode);

		}
	}

	public void addToHead(Node node) {
		node.next = head.next;
		head.next = node;
		node.prev = head;
		node.next.prev = node;
	}

	public void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;

	}
}
