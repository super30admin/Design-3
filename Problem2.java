/**
 * Space Complexity - O(N) N = number of elements
 * Time Complexity - O(1)  - Get()
 *                  O(1) - put()
 */

class LRUCache {
    class Node{
        int key, value;
        Node prev, next;
        public Node(int _key, int _value){
            key = _key;
            value = _value;
        }
    }
    Map<Integer, Node> map;
    Node head, tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        map= new HashMap<>();
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private Node popTail(){
        Node node = tail.prev;
        removeNode(node);
        return node;
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {
        // if not exists then return -1;
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {

        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
            map.put(key,node);
        }else{
            Node node = new Node(key,value);
            if(map.size() == capacity){
                Node temp = popTail();
                map.remove(temp.key);
            }
            addToHead(node);
            map.put(key,node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */