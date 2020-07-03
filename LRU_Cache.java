/**
 * time: O(1)
 * space: O(n) n-capacity
 */
class LRUCache {
    class Node{
        int key, val;
        Node prev,next;
        Node(int k, int v){
            key = k;
            val = v;
        }
    }
    public void addtohead(Node node){
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    HashMap<Integer,Node> hmap;
    Node head, tail;
    int capacity;
    public LRUCache(int cap) {
        hmap = new HashMap<>();
        capacity = cap;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int val = -1;
        if(hmap.containsKey(key)){
            Node curr = hmap.get(key);
            val = curr.val;
            remove(curr);
            addtohead(curr);
        }
        return val;
    }

    public void put(int key, int value) {
        if(hmap.containsKey(key)){
            Node curr = hmap.get(key);
            curr.val = value;
            remove(curr);
            addtohead(curr);
        }else{
            Node newnode = new Node(key,value);
            if(hmap.size() == capacity){
                hmap.remove(tail.prev.key);
                remove(tail.prev);
            }
            addtohead(newnode);
            hmap.put(key,newnode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 *["LRUCache","put","put","get","put","get","put","get","get","get"]
 *[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
 */
