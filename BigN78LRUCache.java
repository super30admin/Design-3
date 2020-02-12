// Time complexity is O(1)
// Space complexity isO(N) O(N) for hashmap and O(N) for doubly linkedlist --> Please let me know if this is correct
// This solution is submitted on leetcode

import java.util.HashMap;

public class BigN78LRUCache {
	class LRUCache {
		class Node {
			Node next;
			Node prev;
			int val;
			int key;

			public Node(int key, int val) {
				this.key = key;
				this.val = val;
			}
		}

		HashMap<Integer, Node> map;
		Node head;
		Node tail;
		int capacity;

		public void remove(Node node) {
			node.next.prev = node.prev;
			node.prev.next = node.next;

		}

		public void insert(Node node) {
			node.next = head.next;
			node.prev = head;
			head.next = node;
			node.next.prev = node;
		}

		public LRUCache(int capacity) {
			this.map = new HashMap<>();
			this.head = new Node(-1, -1);
			this.tail = new Node(-1, -1);
			this.head.next = tail;
			this.tail.prev = head;
			this.capacity = capacity;
		}

		public int get(int key) {
			if (!map.containsKey(key))
				return -1;
			Node temp = map.get(key);
			remove(temp);
			insert(temp);
			return temp.val;
		}

		public void put(int key, int value) {
			if (map.containsKey(key)) {
				Node tempo = map.get(key);
				tempo.val = value;
				remove(tempo);
				insert(tempo);
			} else {
				Node newNode = new Node(key, value);
				if (map.size() >= capacity) {
					Node least = tail.prev;
					remove(least);
					map.remove(least.key);
					insert(newNode);
					map.put(key, newNode);
				} else {
					insert(newNode);
					map.put(key, newNode);
				}
			}
		}
	}
}