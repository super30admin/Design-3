//Time complexity is O(1)
//Space complexity is O(N) N is capacity
public class LRUCache {

  class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;
  }

  private void addNode(DLinkedNode node) {
      node.prev=head;
      node.next = head.next;
      head.next.prev=node;
      head.next = node;
  }

  private void removeNode(DLinkedNode node){
      DLinkedNode next = node.next;
      DLinkedNode prev= node.prev;
      prev.next = next;
      next.prev=prev;
  }

  private void moveToHead(DLinkedNode node){
    removeNode(node);
    addNode(node);
  }

  private DLinkedNode popTail() {
    DLinkedNode res = tail.prev;
    removeNode(res);
    return res;
  }

  private Map<Integer, DLinkedNode> hm = new HashMap<>();
  private int size;
  private int capacity;
  private DLinkedNode head, tail;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    head = new DLinkedNode();
    tail = new DLinkedNode();
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    DLinkedNode node = hm.get(key);
    if (node == null) return -1;
    moveToHead(node);
    return node.value;
  }

  public void put(int key, int value) {
    DLinkedNode node = hm.get(key);

    if(node == null) {
      DLinkedNode newNode = new DLinkedNode();
      newNode.key = key;
      newNode.value = value;

      hm.put(key, newNode);
      addNode(newNode);

      ++size;

      if(size > capacity) {
        DLinkedNode tail = popTail();
        hm.remove(tail.key);
        --size;
      }
    } else {
      node.value = value;
      moveToHead(node);
    }
  }
}