package com.exmaple.problems;

import java.util.HashMap;

public class LRUCache {

	class Node {
		public int key, value;
		Node next, prev;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	Node head, tail;
	int capacity;

	HashMap<Integer, Node> map;

	public LRUCache(int capacity) {
		this.map = new HashMap<>();
		this.head = new Node(-1, -1);
		this.tail = new Node(-1, -1);
		this.head.next = this.tail;
		this.tail.prev = this.head;
		this.capacity = capacity;
	}

	public void removeNode(Node node) {
		node.next.prev = node.prev;
		node.prev.next = node.next;
		
	}

	public void addToHead(Node node) {
		node.next = head.next;
		node.prev = head;
		head.next = node;
		head.next.prev = node;
	}
	
	public void printList() {
		System.out.println("Linked List");
		Node current = head;
		while(current != null) {
			System.out.print(current.value + " -->");
			current = current.next;
		}
	}

	public int get(int key) {
		if (!map.containsKey(key))
			return -1;
		Node node = map.get(key);
		removeNode(node);
		addToHead(node);
		return node.value;
	}

	public void put(int key, int value) {
		System.out.print(map);
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.value = value;
			removeNode(node);
			addToHead(node);
		} else {
			Node newNode = new Node(key, value);
			if (map.size() < this.capacity) {
				addToHead(newNode);
			} else if(map.size() == this.capacity){
				Node lruNode = tail.prev;
				map.remove(lruNode.key);
				removeNode(lruNode);
				addToHead(newNode);
			}
			map.put(key, newNode);
		}
	}

	public static void main(String args[]) {
		LRUCache cache = new LRUCache(2 /* capacity */ );

		cache.put(1, 1);
		cache.put(2, 2);
		cache.get(1); // returns 1
		cache.put(3, 3); // evicts key 2
		cache.get(2); // returns -1 (not found)
		cache.put(4, 4); // evicts key 1
		cache.get(1); // returns -1 (not found)
		cache.get(3); // returns 3
		cache.get(4); // returns 4
	}
}
