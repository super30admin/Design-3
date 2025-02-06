// i was getting stuck at how to manage weights, i needed to check for insertion order

//soln 1: simply with a single linked list
// to remove a node from the linked list i need to iterate through the entire list
// if i store references of the nodes in the hashmap
// but to remove or shifting the nodes -> we need prev, curr pointer and one iteration
// to improve above

//double linked list will look like
// most-recently-used node <-> node <-> node <-> least recently used node

//soln 2: doubly linked list and hashtable
// doubly linked list -> maintain the insertion order
// hashtable -> check if the value exists or not?
// whenever the key is added or updated, move towards the beginning of the list

class LRUCache {

  class Node {

    int key, value;
    Node prev, next;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }

    Node() {
      this(0, 0);
    }
  }

  Node head, tail;
  Map<Integer, Node> map;
  int capacity, count;

  //Initialization of the LRU cache
  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.count = 0;

    map = new HashMap<>();
    head = new Node();
    tail = new Node();

    head.next = tail;
    tail.prev = head;
  }

  //Returns an element if present in the cache
  public int get(int key) {
    Node n = map.get(key);
    if (n == null) {
      return -1;
    } else {
      update(n); //update the cache such that the most recently used element is at the top
      return n.value;
    }
  }

  //adds an element to the cache. If absent, adds. If present, updates the cache to hold the most recently used at the top. If the capacity is full, removes the least recently used element from the cache.

  public void put(int key, int value) {
    Node n = map.get(key);
    if (n == null) {
      n = new Node(key, value);
      map.put(key, n);
      add(n);
      ++count;
    } else {
      n.value = value;
      update(n);
    }

    if (count > capacity) {
      Node del = tail.prev;
      remove(del);
      map.remove(del.key);
      --count;
    }
  }

  //function that updates the MRU element of the cache.
  public void update(Node n) {
    remove(n);
    add(n);
  }

  //adds an element to the top of the cache(MRU element).
  public void add(Node node) {
    Node after = head.next;
    head.next = node;
    node.prev = head;
    node.next = after;
    after.prev = node;
  }

  //removes the LRU element from the cache.
  public void remove(Node node) {
    Node before = node.prev;
    Node after = node.next;
    before.next = after;
    after.prev = before;
  }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
