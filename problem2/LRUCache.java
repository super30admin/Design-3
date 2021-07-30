//Time Complexity : PUT: O(1), GET: O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	Map<Integer, Node> cache;
	int capacity;
	int size;
	Node head;
	Node tail;

	public LRUCache(int capacity) {
		head = new Node();
		tail = new Node();
		this.capacity = capacity;
		this.size = 0;
		this.cache = new HashMap<Integer, Node>();
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		Node node = cache.get(key);
		if (node == null) {
			return -1;
		}
		moveToHead(node);
		return node.val;
	}

	public void put(int key, int value) {
		Node node = cache.get(key);
		if (node == null) {
			Node newNode = new Node();
			newNode.key = key;
			newNode.val = value;
			cache.put(key, newNode);
			addNode(newNode);

			if ((++size) > capacity) {
				Node tail = popLast();
				cache.remove(tail.key);
				--size;
			}
		} else {
			node.val = value;
			moveToHead(node);
		}
	}

	private void addNode(Node node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(Node node) {
		Node prev = node.prev;
		Node next = node.next;

		prev.next = next;
		next.prev = prev;
	}

	private void moveToHead(Node node) {
		removeNode(node);
		addNode(node);
	}

	private Node popLast() {
		Node curNode = tail.prev;
		removeNode(curNode);
		return curNode;
	}

	public static void main(String[] args) {
		LRUCache obj = new LRUCache(2);
		obj.put(1, 1);
		obj.put(2, 2);
		System.out.println(obj.get(1));
		obj.put(3, 3);
		System.out.println(obj.get(2));
		obj.put(4, 4);
		System.out.println(obj.get(1));
		System.out.println(obj.get(3));
		System.out.println(obj.get(4));
	}

}
