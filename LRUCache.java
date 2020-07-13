// Time Complexity : O(1) both get and put
// Space Complexity :O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache {
    
    class Node
    {
        Node next;
        Node prev;
        int key;
        int val;
        Node(int key, int val){
            this.key=key;
            this.val=val;
        }
    }
    
    Node head = new Node(-1,-1);
    Node tail = new Node(-1,-1);
    Map<Integer,Node> map;
    int capacity;
    public LRUCache(int capacity) {
      head.next=tail;
      tail.prev=head;
      this.capacity=capacity;
      map = new HashMap<>();
    }
    
    private int removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
        map.remove(node.key);
        return node.key;
    }
    
    private void moveToHead(Node node){
        //removeNode(node);
        node.prev=head;
        node.next=head.next;
        head.next=node;
        node.next.prev = node;
        map.put(node.key,node);
       // System.out.println(node.key+","+node.val+","+map.size());
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            moveToHead(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
       
        
        //check if node exists
        
            if(map.containsKey(key))
            {
                
                Node node = map.get(key); 
                removeNode(node);
                node.val = value;
                moveToHead(node);
            }
            else
            {
                       //exceed capacity? Remove least recently used
 
                 if(map.size()==capacity){
                    Node last = tail.prev;
                    removeNode(last);             
        }
                
                Node node = new Node(key,value);
                moveToHead(node);
            }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */