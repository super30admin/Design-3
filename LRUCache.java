// Time Complexity : O(1)
// Space Complexity :O(C) // hash map of size capacity
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :no

/*
 * 1. LRU cache should have last accessed element at the starting. when capacity is full remove last element and add first element.
 * 2. we can do it using linked list but it will be O(N) complexity.
 * 3. Best possible to solve using doubly linked list and hash map.
 */

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class Node{
		int key;
		int value;
		Node next;
		Node prev;
		public Node(int key, int value) {
			this.key=key;
			this.value=value;
		}
	}
	
	private int capacity;
	private Map<Integer, Node> map;
	private Node head;
	private Node tail;
	
	public LRUCache(int capacity) {
		this.capacity=capacity;
		map= new HashMap<>(capacity);
		
		//create head and tail with -1 key
		head= new Node(-1, -1);
		tail= new Node(-1, -1);
        head.next=tail;
        tail.prev=head;
	}
	private void removeNode(Node node) {
		node.prev.next=node.next;
		node.next.prev=node.prev;
		map.remove(node.key);
	}
	
	private void addNodeTohead(Node node) {
		node.next=head.next;
		node.next.prev=node;
		head.next=node;
		node.prev=head;
		map.put(node.key, node);
	}

	public int get(int key) {
		if(map.containsKey(key)) {
			Node node= map.get(key);
			removeNode(node);
			addNodeTohead(node);
			return node.value;
		}else {
			return -1;
		}
	}

	public void put(int key, int value) {
		if(map.containsKey(key)) {
            Node node=map.get(key);
			node.value=value;
            removeNode(node);
            addNodeTohead(node);
		}else {
			if(map.size()==capacity) {
				removeNode(tail.prev);
			}
			Node node= new Node(key, value);
			addNodeTohead(node);
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */