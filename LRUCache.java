//time complexity O(1)
//space complexity O(capacity)

class LRUCache {
    class Node (int key, int value){
        Node next; Node prev;
        this.key = key;
        this.val = value;
    }
    HashMap<Integer, Node> map;
    Node dummy = new Node(-1, -1);
    int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        map.size = capacity;
    }
    public void addtoHead(Node node){
        node.next = head;
        head.prev = node;
        dummy.next = node;
        node.prev = dummy;
        map.put(node.key, node);
    }
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        map.remove(node.key);
    }
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            addtoHead(node);
            removeNode(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(map.size() == capacity){
            Node lastNode = tail.prev;
            removeNode(map.get(lastNode.key));
            if(!map.containsKey(key)){

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
