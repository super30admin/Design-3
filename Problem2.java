// Time Complexity: get O(1) put O(1)
// Space Complexity: O(N)
// Passed Leetcode

class Node {
    int value;
    int key;
    Node prev = null;
    Node next = null;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    int capacity;
    Node top = new Node(-1, -1);
    Node bottom = new Node(-1, -1);
    HashMap<Integer, Node> map = new HashMap<>();
        
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        
        if(!map.containsKey(key))
            return -1;
        
        Node node = map.get(key);
        if (node == top.next)
            return node.value;
        
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        Node temp = top.next;
        
        top.next = node;
        node.prev = top;
        node.next = temp;
        temp.prev = node;
        return node.value;
    }
    
    public void put(int key, int value) {
        
        if (map.containsKey(key)) {
            map.get(key).value = value;
            get(key);
            return;
        }
        
        Node node = new Node(key, value);
        map.put(key, node);
        if (top.next == null) {
            top.next = node;
            node.prev = top;
            node.next = bottom;
            bottom.prev = node;  
        } else {
            Node temp = top.next;
            top.next = node;
            node.prev = top;
            node.next = temp;
            temp.prev = node;
            
            if (map.size() > capacity) {
                map.remove(bottom.prev.key);
                temp = bottom.prev;
                temp.prev.next = bottom;
                bottom.prev = temp.prev;
            }
                
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */