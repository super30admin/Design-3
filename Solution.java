//Space Complexity: O(capacity)
//Time Complexity: O(1)
class LRUCache {
	class Node{
		int key;
		int val;
		Node prev;
		Node next;
		public Node(int key, int val){
			this.key = key;
			this.val = val;
		}
	}

	private void addToHead(Node node){
		node.next = head.next;
		node.prev = head;
		head.next = node;
		node.next.prev = node;
	}

	public void remove(Node node){
		node.next.prev = node.prev;
		node.prev.next = node.next;
	}

	Node head; Node tail;
	int capacity;
	HashMap<Integer,Node> hmap;

	public LRUCache(int capacity) {
		hmap = new HashMap<>();
		this.capacity = capacity;
		head =new Node(-1,-1);
		tail = new Node(-1,-1);
		head.next = tail;
		head.next.prev = tail.prev;
	}

	public int get(int key) {
		if(!hmap.containsKey(key))return -1;
		Node node = hmap.get(key);
		remove(node);
		addToHead(node);
		return node.val;
	}

	public void put(int key, int value) {
		if(hmap.containsKey(key)){
			Node node = hmap.get(key);
			node.val = value;
			remove(node);
			addToHead(node);
		}else{
			Node newNode = new Node(key,value);
			if(capacity == hmap.size()){
				Node tailprev = tail.prev;
				remove(tailprev);
				hmap.remove(tailprev.key);
			}
			addToHead(newNode);
			hmap.put(key, newNode);
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */