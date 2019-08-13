//tc:o(1)
//SC:o(1)
//idea is to use hashmap .key as key and value as double LinkedList. LEAST RECENTLY USED ELEMENT SHOULD BE AT THE END.
//AND RECENTLY USED MEAND ADD OR GET ELEMENT SHOULD BE AT HEAD
//when we get element from hashmap its complexity is 0(1) and put inside linkedList with 0(1)

import java.util.HashMap;

class Node {
int key;
int value;
Node pre;
Node next;

public Node(int key, int value) {
	this.key = key;
	this.value = value;
}
}

class LRUCache {
   HashMap<Integer, Node> map;
    int capicity, count;
    Node head, tail;
    //constructor lru
    public LRUCache(int capacity) {
        this.capicity = capacity;
	    map = new HashMap<>();
	    head = new Node(0, 0);
	    tail = new Node(0, 0);
	    head.next = tail;
	    tail.pre = head;
    	head.pre = null;
    	tail.next = null;
	    count = 0;        
    }
    
    //DELETE NODE
    public void deleteNode(Node node) {
	node.pre.next = node.next;
	node.next.pre = node.pre;
   }
    //ADD ELEMENT TO HEAD
    public void addToHead(Node node) {
	node.next = head.next;
	node.next.pre = node;
	node.pre = head;
	head.next = node;
    }

    //FETCH  VALUE BASED ON KEY AND DELETE NODE AND ADD AGAIN TO HEAD.
    public int get(int key) {
        if (map.get(key) != null) {
		Node node = map.get(key);
		int result = node.value;
		deleteNode(node);    
		addToHead(node);
		return result;
	}
	return -1;

    }
    
    //ADD ELEMENT TO HASHMAP IF KEY IS PRESENT IN THEN ASSIGN NEW VALUE AND REMOVE NODE NODE AND ADD GAIN TO HEAD.
    //IF NODE IS NEW AND EXCEED CAPACITY THEN REMOVE TAIL NODE AND ADD NEW NODE TO HEAD
    public void put(int key, int value) {
        if (map.get(key) != null) {
		Node node = map.get(key);
		node.value = value;
		deleteNode(node);
		addToHead(node);
	} else {
		Node node = new Node(key, value);
		map.put(key, node);
		if (count < capicity) {
			count++;
			addToHead(node);
		} else {
			map.remove(tail.pre.key);
			deleteNode(tail.pre);
			addToHead(node);
		}
	 }
   }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
