//Time Complexity: O(1)
//Space Complexity: O(capacity)
class LRUCache {
    class Node{
        int val;
        int key;
        Node next;
        Node prev;
        Node(int value){
            this.val = value;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);

            node.prev.next = node.next;
            node.next.prev = node.prev;

            tail.prev.next = node; //Add to tail which is most recently used
            node.next = tail;
            node.prev = tail.prev;
            tail.prev = node;
            return node.val;
        }
        else
            return -1;
    }

    public void put(int key, int value) {

        Node node;
        if(map.containsKey(key)){
            node = map.get(key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        else{
            node = new Node(value);

            if(map.size()>=capacity){
                map.remove(head.next.key);
                head.next.next.prev = head;
                head.next = head.next.next;
            }
        }
        node.val = value;
        map.put(key, node);
        node.key = key;
        if(head.next == tail){
            head.next = node;
            node.prev = head;

            tail.prev = node;
            node.next = tail;
            return;
        }
        tail.prev.next = node;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
    }


}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */