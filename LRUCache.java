// Time Complexity : O(1)
// Space Complexity :O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class LRUCache {

  class Node {
    int key, value;
    Node next, prev;
    Node(int key,int value) {
      this.key = key;
      this.value = value;
    }
  }

  Node head, tail;

  void addToHead(Node node) {
    node.next = head.next;
    node.prev = head;
    node.next.prev = node;
    head.next = node;
  }

  void removeNode(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  Map<Integer, Node> hashmap;
  int capacity;

  public LRUCache(int capacity) {
    hashmap = new HashMap<>();
    head = new Node(-1,-1);
    tail = new Node(-1,-1);
    head.next = tail;
    tail.prev = head;
    this.capacity = capacity;
  }

  public int get(int key) {
    if(!hashmap.containsKey(key)) {
      return -1;
    }
    Node node = hashmap.get(key);
    removeNode(node);
    addToHead(node);
    return node.value;
  }

  public void put(int key, int value) {
    if (hashmap.containsKey(key)) {
      Node node = hashmap.get(key);
      removeNode(node);
      addToHead(node);
      node.value = value;
    } else {
      if (hashmap.size() == capacity) {
        hashmap.remove(tail.prev.key);
        removeNode(tail.prev);
      }
      Node newNode = new Node(key, value);
      addToHead(newNode);
      hashmap.put(key, newNode);
    }
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */