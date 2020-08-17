/*
// Time Complexity : O(1) put, O(1) get, O(1)remove.
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :
// nope

// Your code here along with comments explaining your approach
*/
class LRUCache {
    class Node{
        int key;
        int val;

        Node next;
        Node prev;
        Node(int key, int val){
            this.key = key;
            this.val = val;

            this.next = null;
            this.prev = null;
        }
    }

    //node head and tail is used to maintain leat recent used list.
    Node head;
    Node tail;

    int cap;
    //create map of int , nodes.
    HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.cap = capacity;
        map = new HashMap<>();
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void addNode(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    public int get(int key) {
        Node node = map.getOrDefault(key, null);
        if(node != null){
            removeNode(node);
            addNode(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = map.getOrDefault(key, null);
        if(node != null){
            removeNode(node);
            addNode(node);
            node.val = value;
        }
        else{
            if(map.size() >= cap){
                Node last = tail.prev;
                removeNode(last);
                map.remove(last.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
