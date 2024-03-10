package week6.day5;

import java.util.HashMap;

public class LRUCache {
	class Node{
        int key;
        int val;
        Node next;
        Node prev;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            removeNode(curr);
            addToHead(curr);
            return curr.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            removeNode(curr);
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
        else if(capacity==map.size()){
            Node prevNode = tail.prev;
            removeNode(prevNode);
            map.remove(prevNode.key);
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
        else{
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    private void removeNode(Node currNode){
        currNode.next.prev = currNode.prev;
        currNode.prev.next = currNode.next;
        currNode.prev = null;
        currNode.next = null;
    }
    private void addToHead(Node currNode){
        currNode.next = head.next;
        currNode.prev = head;
        head.next = currNode;
        currNode.next.prev = currNode; 
    }

	/**
	 * Your LRUCache object will be instantiated and called as such:
	 * LRUCache obj = new LRUCache(capacity);
	 * int param_1 = obj.get(key);
	 * obj.put(key,value);
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//[[2],[1,0],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
		LRUCache cache = new LRUCache(2);
		cache.put(1,0);
		cache.put(2,2);
		int val = cache.get(1);
		System.out.println(val);
		cache.put(3,3);
		val = cache.get(2);
		System.out.println(val);
		cache.put(4,4); //--
		val = cache.get(1);
		System.out.println(val);
		val = cache.get(3);
		System.out.println(val);
		val = cache.get(4);
		System.out.println(val);
		
	}

}
