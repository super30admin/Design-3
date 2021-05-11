// Time Complexity : O(1)
// Space Complexity : O(capacity) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach:

class Node {
  Node next, prev;
  int key, value;

  public Node(int key, int value) {
    this.key = key;
    this.value = value;
  }
}

class LRUCache {
  Node head, tail;
  HashMap<Integer, Node> map;
  int cap;

  public LRUCache(int capacity) {
    map = new HashMap<>();
    head = new Node(-1, -1);
    tail = new Node(-1, -1);
    head.next = tail;
    tail.prev = head;
    cap = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node cur = map.get(key);
      remove(cur);
      addToBeg(cur);
      return cur.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    Node cur = head;
    if (map.containsKey(key)) {
      cur = map.get(key);
      cur.value = value;
      remove(cur);
      addToBeg(cur);
      return;
    } else if (map.size() == cap) {
      Node temp = tail.prev;
      map.remove(temp.key);
      remove(temp);
    }
    cur = new Node(key, value);
    map.put(key, cur);
    addToBeg(cur);

  }

  private void addToBeg(Node node) {
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
    node.prev = head;
  }

  private void remove(Node node) {
    node.next.prev = node.prev;
    node.prev.next = node.next;
  }
}