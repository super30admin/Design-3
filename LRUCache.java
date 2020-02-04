package Q_146_LRU_Cache;

import java.util.HashMap;

//Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
//
//get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
//
//The cache is initialized with a positive capacity.
//
//Follow up:
//Could you do both operations in O(1) time complexity?
//
//Example:
//
//LRUCache cache = new LRUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.put(4, 4);    // evicts key 1
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4

//Time Complexity  : O(1)
//Space Complexity : O(1)
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */





public class LRUCache {
	
	int capacity;
	DeQueue deQueue;
	HashMap<Integer, Node> map;
	
	public class Node{
		public int key;
		public int value;
		public Node prev;
		public Node next;
		
		public Node(){
			
		}
		
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public class DeQueue{
		Node head;
		Node tail; 
		
		public DeQueue() {
			head = new Node();
			tail = new Node();
			head.next = tail;
			tail.prev = head;
		}
		
		
		public void insert(Node node)
		{
			node.next = head.next;
			node.prev = head;
			head.next.prev = node;
			head.next = node;
		}
		
		public void delete(Node node)
		{
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.next = null;
			node.prev = null;
		}
		
		public Node getLastNode()
		{
			return tail.prev;
		}
		
		public void putAthead(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			
			node.next = head.next;
			node.prev = head;
			head.next.prev = node;
			head.next = node;
			
		}
		
	}
	
	public LRUCache(int capacity) {
		deQueue = new DeQueue(); 
		map = new HashMap<>();
		this.capacity = capacity;
		
	}

	public int get(int key) {
		if(map.containsKey(key))
		{
			Node n = map.get(key);
			deQueue.putAthead(n);
			return n.value;
		}
		
		return -1;

	}

	public void put(int key, int value) {
		
		Node node = new Node(key, value);
		if(map.size() < capacity)
		{
			if(!map.containsKey(key))
			{
			map.put(key, node);
			deQueue.insert(node);
			}else
			{
				Node n = map.get(key);
				n.value = value;
				deQueue.putAthead(n);
			}
		}else
		{
			if(!map.containsKey(key))
			{
				Node n = deQueue.getLastNode();
				map.remove(n.key);
				deQueue.delete(n);
				
				map.put(key, node);
				deQueue.insert(node);
			}else
			{
				Node n = map.get(key);
				n.value = value;
				deQueue.putAthead(n);
			}
		}
		
		

	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));      // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4)); 
		

	}

}
