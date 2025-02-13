/**
 * Time complexity: O(1)
 * Space complexity: O(capacity)
 */
class LRUCache {

    class Node {
        int key, val;
        Node prev, next;
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    Node head, tail;
    int capacity;
    HashMap<Integer, Node> map;

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeFromTail(){
        Node lastNode = tail.prev;
        map.remove(lastNode.key);
        removeNode(lastNode);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }
        else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            temp.val = value;
            removeNode(temp);
            addToHead(temp);
        }
        else{
            Node node = new Node(key, value);
            map.put(key, node);
            if(map.size() > capacity){
                removeFromTail();
                addToHead(node);
            }
            else{
                addToHead(node);
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