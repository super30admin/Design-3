//Time Complexity - O(1)
//Space complexity - O(n)

class LRUCache {
    class Node {
      int key;
      int val;
      Node next;
      Node prev;
      public Node(int key, int val) {
        this.key = key;
        this.val = val;
      }
    }
    private void addToHead(Node node) {
      //add node to head and remove the connection from head.next
      node.next = head.next;
      node.prev = head;
      head.next = node;
      node.next.prev = node;
      
    }
    private void removeNode(Node node) {
    //connect node's prev node to node's next node
      node.next.prev = node.prev;
      node.prev.next = node.next;
      
    }
    //we need hashmap to store key and listnode to access the nodes in O(1)
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
      
    public LRUCache(int capacity) {
      this.map = new HashMap<>();
      this.head = new Node(-1,-1);
      this.tail = new Node(-1,-1);
      this.capacity = capacity;
      head.next = tail;
      tail.prev = head;
    }
    
    public int get(int key) {
        /** 1. check the node exist in map
            2. get the node from map using key
            3. remove the node from its place
            4. add the node to head **/
      // System.out.println("Items in Map for GET call:");
      //  map.entrySet().forEach(entry -> {
      //   System.out.println(entry.getKey());
      //  });
       if(!map.containsKey(key)) {
         return -1;
       }
       Node node = map.get(key);
       removeNode(node);
       // System.out.println("*****");
       addToHead(node);
       return node.val;
    }
    
    public void put(int key, int value) {
       // System.out.println("put " + key);
        /* 1. if the node already exist update the key
           2. if its new node check the capacity is full
           3. if its not full add node to the map and head
           4. if the capacity is full remove the node from tail(least used)
           5. then add the new node to the head */
       if(map.containsKey(key)) {
         Node node = map.get(key);
         node.val = value;
         removeNode(node);
         addToHead(node);
       } else {
         
         if(capacity == map.size()) {
           // System.out.println("reached here");
           Node tailPrev = tail.prev;
           removeNode(tailPrev);
           System.out.println("Tail key:"+tailPrev.key+ "---Tail value:"+tailPrev.val);
           map.remove(tailPrev.key);
          
          //  System.out.println("cap =" + capacity + "map size = " + map.size());
          //  map.entrySet().forEach(entry -> {
          // System.out.println(entry.getKey());
          // });
         }
         Node newNode = new Node(key,value);
         System.out.println("Adding:"+newNode.key);
         addToHead(newNode);
         map.put(key, newNode);
         // System.out.println("cap =" + capacity + "map size = " + map.size());
       } 
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */