// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class LRUCache {
    
    class Node{
        int key;
        int value;
        Node next;Node prev;
        Node(int key, int value)
        {
            this.key = key;
            this.value=value;
        }
    }
    
    HashMap<Integer,Node> map;
    int capacity;
    Node head;Node tail;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity= capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next= tail;
        tail.prev = head;
        
    }
    private void remove(Node node)
    {
        node.next.prev= node.prev;
        node.prev.next = node.next;
    }
    private void addTohead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        
    }
    public int get(int key) {
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            remove(node);
            addTohead(node);
            return node.value;
        }
        else
        {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            addTohead(node);
        }
        else
        {
            if(map.size() == this.capacity)
            {
                Node node = tail.prev;
                remove(node);
                map.remove(node.key);
            }
            Node node = new Node(key,value);
            map.put(key,node);
            addTohead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */