// Time Complexity : O(1), for put and get methods
// Space Complexity : O(N), N is size of the cache/ size of map
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/**To maintain the elements sequentially (as per time), we can use LinkedList
 * But to make access to each node as O(1), we use map. To make ease of access to each node in linked list, we can map key element to its created Node which stores key and value
 * To make and remove operations as O(1), we use doubly LinkedList
 * For adding the node (put method), we have to check is it exists or not, if it exists we have to replace its value and put it at the head of the queue(most recent). If it doesn't exist create new and add it to the head
 * While getting node value (get method), we have to move the node to the head */

import java.util.*;
public class LRU_Cache {
	class Node{
		int key;
		int value;
		Node next;
		Node prev;

		public Node(int key, int value){
			this.key = key;
			this.value = value;
		}
	}
	private void addtoHead(Node node){  //O(1)
		node.next = head.next;
		node.prev = head;
		head.next = node;
		node.next.prev = node;
	}
	private void removeNode(Node node){
		node.next.prev = node.prev;
		node.prev.next = node.next;
	}

	HashMap<Integer, Node> map;
	Node head;
	Node tail;
	int capacity;

	public LRU_Cache(int capacity) {
		map = new HashMap<>();
		head = new Node(-1, -1);
		tail = new Node(-1, -1);
		head.next = tail;
		tail.prev = head;
		this.capacity = capacity;
	}

	public int get(int key) {
		Node node = map.get(key);
		if(node != null){
			int result = node.value;
			removeNode(node);
			addtoHead(node);

			return result;
		}
		return -1;
	}

	public void put(int key, int value) {
		//if the node is already exisiting in my cache
		if(map.containsKey(key)){
			Node node = map.get(key);
			node.value = value;
			removeNode(node);
			addtoHead(node);
		}
		else{
			if(capacity == map.size()){
				//remove the least recently used node
				Node tailnode = tail.prev;
				//removed from list
				removeNode(tailnode);
				//remove it from map as well
				map.remove(tailnode.key);
			}
			Node node = new Node(key, value);
			map.put(key, node);
			addtoHead(node);
		}
	}
}