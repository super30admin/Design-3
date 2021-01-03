// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : yes 
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach

class LRUCache {

    class DLinkedNode {
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode next;
    }
    
    DLinkedNode head;
    DLinkedNode tail;
    int curr;
    int capacity;
    Map<Integer, DLinkedNode> map;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        curr = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
        map = new HashMap();
    }
    
    public int get(int key) {
        DLinkedNode node = map.get(key);
        if(node == null)
            return -1;
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if(node == null) {
            DLinkedNode newnode = new DLinkedNode();
            newnode.key = key;
            newnode.val = value;
            addNode(newnode);
            map.put(key, newnode);
            curr++;
            if(curr > capacity) {
                DLinkedNode res = removeLast();
                map.remove(res.key);
                curr--;
            }
        }
        else {
            node.val = value;
            moveToHead(node);
        }
    }
    
    public void addNode(DLinkedNode node) {
        DLinkedNode temp = head.next;
        head.next = node;
        temp.prev = node;
        node.next = temp;
        node.prev = head;
    }
    
    public void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }
    
    public void removeNode(DLinkedNode node) {
         DLinkedNode prevNode = node.prev;
         DLinkedNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;       
    }
    
    public DLinkedNode removeLast() {
        
        DLinkedNode temp = tail.prev;
        removeNode(temp);
        
        return temp;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */