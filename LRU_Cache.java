class LRUCache {
    class Node {
        int key, value;
        Node next;
        Node prev;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node> map;
    public void addNodeInTheHead(Node node) {
         node.next = head.next;
         node.prev = head;
         head.next = node;
         node.next.prev = node;
 
    }
    public void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        
    }
     public LRUCache(int capacity) {
         this.capacity = capacity;
         this.head = new Node(-1,-1);
         this.tail = new Node(-1,-1);
         this.map = new HashMap<>();
         this.head.next = this.tail;
         this.tail.prev = this.head;
     }
 
     
     public int get(int key) {
         if(!map.containsKey(key)) {
             return -1;
         }
         Node node = map.get(key);
         removeNode(node);
         addNodeInTheHead(node);
         return node.value;
     }
     
     public void put(int key, int value) {
         if(map.containsKey(key)) {
             Node node = map.get(key);
             node.value = value;
             removeNode(node);
             addNodeInTheHead(node);
         } else {
             if(capacity == map.size()) {
                 Node tailprev = tail.prev;
                 removeNode(tailprev);
                 map.remove(tailprev.key);
             }
             Node newnode = new Node(key,value);
             addNodeInTheHead(newnode);
             map.put(key,newnode);
         }
 
     }
 }
 
 /**
  * Your LRUCache object will be instantiated and called as such:
  * LRUCache obj = new LRUCache(capacity);
  * int param_1 = obj.get(key);
  * obj.put(key,value);
  */