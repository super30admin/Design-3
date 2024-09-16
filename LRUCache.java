/* Approach: Using a doubly linked list , we can get a hold of both head and tail  so that we can access both in O(1) time.
Time complexity : O(1) both for put and get.
Space Complexity: O(C) where C is capacity
*/

class LRUCache {

    class Node{
        int key;
        int value;
        Node prev;
        Node next;
    }

    private void addNode(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }


    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveTohead(Node node){
        removeNode(node);
        addNode(node);
    }

    private Node popTail(){
        Node node = tail.prev;
        node.prev.next = tail;
        removeNode(node);
        return node;
    }

    private Map<Integer, Node> map = new HashMap<>();
    private int size, capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            moveTohead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {

        if(map.containsKey(key)){
            Node node1 = map.get(key);
            node1.value = value;
            moveTohead(node1);
        }else{
            Node temp = new Node();
            temp.key = key;
            temp.value = value;
            addNode(temp);

            map.put(key, temp);
            size++;
            if(size > capacity){
                Node temp1 = popTail();
                map.remove(temp1.key);
                size--;
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