// Time Complexity : get operation -> O(1) , put operation -> O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach

class LRUCache {
    
  //we will have a doubly linkedlist here
    class Node
    {
      int key,val;
      Node prev ,next;
      
      public Node(int key,int val){
        this.key = key;
        this.val = val;
        prev = null;
        next=null;
      }
    }
  
    HashMap<Integer,Node> map ;
    Node head,tail;
    int size,capacity;
    public LRUCache(int capacity) {
      
      map =  new HashMap<>();
      this.capacity = capacity;
      size = 0;
      //create a dummy head and a tail
      head = new Node(0,0);
      tail = new Node(0,0);
      
      head.next = tail;
      tail.prev = head;
        
    }
    
    //add , remove

    //we will always add the new node just before the tail
  private void add(Node node)
  {
    Node temp = tail.prev;
    temp.next = node;
    node.next = tail;
    tail.prev = node;
    node.prev = temp;
  }
  
  //just break the link and connect the next and prev with each other
  private void remove(Node node)
  {
    Node before = node.prev;
    Node after = node.next;
    
    before.next = after;
    after.prev = before;
  }
  
  //remove the node from the current postion and add it before the tail , this will be used whenever we just use get method or put method
  private void update(Node node)
  {
    remove(node);
    add(node);
  }
    //get the node and update it to be at before the tail
    public int get(int key) {
      
      Node curr = map.get(key);
      
      if(curr == null)
        return -1;
      
      update(curr);
      return curr.val;
      
    }
    //add node if not alreadythere just before the tail
    public void put(int key, int value) {
      
      Node curr = map.get(key);
      
      if(curr == null)
      {
        curr = new Node(key,value);
        map.put(key,curr);
        //increment the size to be checked against input capacity
        size++;
        add(curr);
      }
      else
      {
        //if node is already there then remove from current and put it before tail
        update(curr);
        curr.val = value;
      }
      //if the size exceeded capacity then we need to removethe least recently used which would be the next of head
      if(size>capacity)
      {
        map.remove(head.next.key);
        remove(head.next);
        size--;
      }
      
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */