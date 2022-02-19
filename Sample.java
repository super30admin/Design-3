//Flatten Nested List Iterator 
//Time Complexity - O(1)
//Space Complexity - O(1)
  
  public class NestedIterator implements Iterator<Integer> {
  Stack<Iterator<NestedInteger>> stack;
  NestedInteger nextElement;
  public NestedIterator(List<NestedInteger> nestedList) {
  stack = new Stack();
  stack.push(nestedList.iterator());
  }

      @Override
      public Integer next() {
          return nextElement.getInteger();
      }

      @Override
      public boolean hasNext() {

          while(!stack.isEmpty()) {
              //iterator out of bounds
              if(!stack.peek().hasNext()) {
                  stack.pop();
              } else if((nextElement = stack.peek().next()).isInteger()) {
              //is Integer
                  return true;
              } else {
              //is List
                  stack.push(nextElement.getList().iterator());
              }
          }
          return false;
      }

  }






//LRU Cache

//Time Complexity - O(1)
//Space Complexity - O(Capacity)

class LRUCache {
class ListNode{
int key;
int val;
ListNode prev;
ListNode next;

        public ListNode(int key,int val) {
            this.key = key;
            this.val = val;
        }
    }
    HashMap<Integer, ListNode> map;
    int capacity;
    ListNode head;
    ListNode tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void addToHead(ListNode node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        } else {
            ListNode node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            if(capacity == map.size()) {
                ListNode rmNode = tail.prev;
                removeNode(rmNode);
                map.remove(rmNode.key);
            }
            ListNode newNode = new ListNode(key,value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }

}

