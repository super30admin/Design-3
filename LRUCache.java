//Approach - LinkedHashMap - with access order set to true
//Time Complexity - O(1)
//Space Complexity - O(capacity)

class LRUCache {

  LinkedHashMap<Integer, Integer> cache;
  int capacity = 0;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    cache = new LinkedHashMap<>(capacity, 0.75f, true);
  }

  public int get(int key) {
    return cache.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    cache.put(key, value);

    if(cache.size() > capacity){
      int node = cache.keySet().iterator().next();
      cache.remove(node);
    }
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


//////////////////////////////////////////////////////////////////////////////////////


//Approach - Doubly LinkedList to remove and insert them at head on recent access
//Map - to perform get operation in O(1)
//Time Complexity - O(1)
//Space Complexity - O(capacity)

class LRUCache {


  Map<Integer, Node> map;
  Node head, tail;
  int capacity;

  class Node{
    int key, val;
    Node prev;
    Node next;

    public Node(int key, int val){
      this.key = key;
      this.val = val;
    }
  }

  public void remove(Node node){
    map.remove(node.key);
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }


  public void insert(Node node){
    map.put(node.key, node);
    Node temp = head.next;
    head.next = node;
    node.prev = head;

    temp.prev = node;
    node.next = temp;

  }

  public LRUCache(int capacity) {
    map = new HashMap<>();
    this.capacity = capacity;
    head = new Node(0,0);
    tail = new Node(0,0);

    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    if(map.containsKey(key)){
      Node node = map.get(key);
      remove(node);
      insert(node);
      return node.val;
    }
    return -1;
  }

  public void put(int key, int value) {

    if(map.containsKey(key)){
      Node node = map.get(key);
      remove(node);
    }
    if(map.size() == capacity){
      remove(tail.prev);
    }

    insert(new Node(key, value));
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
