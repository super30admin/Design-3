/*

## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

Time Complexity : O(1)
Space Complexity : O(n)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

*/
class LRUCache {

    class Node{
        int key, val;
        Node next, prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    Node tail, head;
    int capacity;
    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        
        map = new HashMap<>();
        tail = new Node(-1,-1);
        head = new Node(-1,-1);
        tail.prev = head;
        head.next = tail;
        this.capacity = capacity;

    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node curr = map.get(key);

        removeNode(curr);
        addToHead(curr);
        return curr.val;
    }
    
    public void put(int key, int value) {
        Node curr = new Node(key, value);
        if(!map.containsKey(key)){
            if(map.size() == this.capacity){
                int temp = tail.prev.key;
                removeNode(tail.prev);
                map.remove(temp);
            }
            addToHead(curr);
            map.put(key, curr);
        }
        else{
            Node temp = map.get(key);
            removeNode(temp);
            addToHead(temp);
            temp.val = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */