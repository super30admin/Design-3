/**
 * Design a LRU cache.
 * Use a doubly linked list to store the value . Maintain a reference of the node in a hashmap
 *
 * Couple of mistakes that i made while implementing , forgot to remove key on line 80.
 * Switching from python to java, forgot to add semicolons to the statements
 *
 * get and put are both O(1)
 */

import java.util.HashMap;

public class LRUCache {

  static class Node {

    private Node next;
    private Node prev;
    private Integer key;
    private Integer value;

    Node(int key, int value) {
      this.value = value;
      this.key = key;
    }

    void remove() {
      Node a = prev;
      Node b = next;
      if ((a != null) && (b != null)) {
        a.next = b;
        b.prev = a;
      } else {
        throw new RuntimeException("One of prev or next nodes is null");
      }
    }

    void addAfter(Node h) {
      Node b = h.next;
      if ((h != null) && (b != null)) {
        h.next = this;
        this.next = b;
        this.prev = h;
        b.prev = this;
      } else {
        throw new RuntimeException("One of prev or next nodes is null");
      }
    }

  }

  private Node head;
  private Node tail;
  private HashMap<Integer, Node> lookup;
  private int capacity;

  public LRUCache(int capacity) {

    this.capacity = capacity;
    head = new Node(-1,-1);
    tail = new Node(-1,-1);
    head.next = tail;
    tail.prev = head;
    this.lookup = new HashMap<>();

  }


  public void put(int k, int v) {

    // check if k in look up
    if (lookup.containsKey(k)) {
      // remove node k;
      Node n = lookup.get(k);
      n.remove();
      lookup.remove(k);
      ++capacity;
    } else {
      if (this.capacity == 0) {
        // remove last node
        Node n = tail.prev;
        n.remove();
        lookup.remove(n.key);
        ++capacity;
      }
    }

    Node node = new Node(k,v);
    node.addAfter(head);
    this.lookup.put(k, node);
    --capacity;


  }


  public Integer get(int k) {
    Node n = lookup.get(k);
    if (n != null) {
      this.put(k, n.value);
      return n.value;
    }
    return -1;

  }
}