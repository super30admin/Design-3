// Time Complexity: O(1)
// Space Complexity: O(2n)

class LRUCache {
     class Node {
         int key;
         int val;
         Node prev;
         Node next;
         public Node(int key, int val){
             this.key = key;
             this.val = val;
         }
     }
    Node head;
    Node tail;
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    HashMap<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }else { //totally new element 
             Node newNode = new Node(key, value);
            if(capacity == map.size()) {
                // then remove lru el from LRU cache
                Node tailPrev = tail.prev;
                //remove from Linked List
                removeNode(tailPrev);
                //remove from map
                map.remove(tailPrev.key);
            }
            //add the new Node to list
            addToHead(newNode);
            //add to map
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
