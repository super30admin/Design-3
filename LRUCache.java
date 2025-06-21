// Time Complexity O(1)
// Space Complexity O(n) n=capacity
// Runs on LeetCode YES

class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.value = val;
        }
    }

    Map<Integer, Node> map = new HashMap<Integer, Node>();
    int capacity;
    Node head = new Node(-1,-1);
    Node tail = new Node(-1,-1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {

        if(map.containsKey(key)){
            Node node = map.get(key);
            int result = node.value;
            delete(node);
            add(node);
            return result;
        }else{
            return -1;
        }

    }

    public void put(int key, int value) {

        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            delete(node);
            add(node);
        }else{
            if(map.size() == capacity){
                Node lruNode = tail.prev;
                map.remove(lruNode.key);
                delete(lruNode);

                Node newNode = new Node(key, value);
                map.put(key, newNode);
                add(newNode);
            }else{
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                add(newNode);
            }

        }
    }

    private void delete(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void add(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */