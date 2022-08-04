//Microsoft
////Time complexity = O(1) for get and put
//SC =O(capacity)
//Using Doubly LL could be suitable because it has two way directions to node ,So head pointing to first node of the pointer and then ass a second element ex.PU(2,2) then first element should be pushed to the next node and add recent element after the head.Double LL used to maintain order to remove or add an element .In add method the node.next = head.next,node.prev = head ,node.next = node,node.next.prev = node and in remove method remove the element and add the element because size capacity = 4 is considered in DLL.
class LRUCache {
    class Node{
        int key,value;
        Node prev,next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head,tail;
    HashMap<Integer,Node> map;
    int capacity;
    
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    //Time complexity = O(1)
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    //Time complexity = O(1)
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
            return;
        }
        if(map.size() == capacity){
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key);
        }
        Node node = new Node(key,value);
        addToHead(node);
        map.put(key,node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */