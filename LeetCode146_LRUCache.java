//approach
/*1. create a doubly linkedlist  create HashMap of key as key and node as values. 
*in doubly linked list the removal and adding to head and tail will cost only O(1)
* using hashmap will help to lookup for the key in O(1) time, 
*so tc - O(1), SC - O(n) for using linkedLIst and Map. 
*/

class LRUCache {
    
    class Node{
        int key,value;
        Node prev, next;
        
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    int capacity;
    Node head, tail;
    Map<Integer, Node> map;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head= new Node(-1, -1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    private void addToHead(Node node)
    {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }
    
    private void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev; // tail's prev = node's prev
        node.prev = null;
        node.next = null;
        
    }
    public int get(int key) {
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        else
            return -1;
    }
    
    public void put(int key, int value) {
       
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value;
            return;
        }
        if(map.size() == capacity)
        {
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key);
        }
        Node newNode = new Node(key,value);
        map.put(key, newNode);
        addToHead(newNode);
        
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */