// Time Complexity : O(1) for both functions
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : NA


// Your code here along with comments explaining your approach

class LRUCache {
    
    class Node {
        
        int key, value;
        Node prev, next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
        int cap;
        Node head, tail;
        HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        cap = capacity;
        map = new HashMap<>();
    }
    
    private void remove(Node curr) {
        
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
    }
    
    private void addToBeginning(Node curr) {
        
        curr.next = head.next;
        head.next.prev = curr;
        curr.prev = head;
        head.next = curr;
    }
    
    public int get(int key) {
        
        if(map.containsKey(key)) {
            Node curr = map.get(key);
            remove(curr);
            addToBeginning(curr);
            return curr.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        
        Node curr = head;
        
        if(map.containsKey(key)) {
            curr = map.get(key);
            curr.value = value;
            remove(curr);
            addToBeginning(curr);
            return;
        }
        else if(cap == map.size()) {
            int key2 = tail.prev.key;
            remove(tail.prev);
            map.remove(key2);
        }
        curr = new Node(key, value);
        addToBeginning(curr);
        map.put(key, curr);
        return;
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */