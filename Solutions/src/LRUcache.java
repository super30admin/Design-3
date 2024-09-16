class Node{
    int data;
    int key;
    Node previous;
    Node next;
    Node (int key, int data){
        this.data = data;
        this.key =key;
        previous = null;
        next = null;
    }
}
class LRUCache {

    Node head;
    Node tail;
    int capacity;
    Map<Integer,Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;


        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next =tail;
        tail.previous = head;
    }

    public int get(int key) {
        // not exist
        Node current = map.get(key);
        if(current==null){
            return -1;
        }

        //exist

        remove(current);
        addFirst(current);
        return current.data;

    }

    public void put(int key, int value) {
        // not exist
        Node current = map.get(key);
        if(current==null){
            Node tmp = new Node(key,value);
            map.put(key,tmp);
            addFirst(tmp);
            // if capacity exceed delete last

            if(map.size()>capacity){
                Node removeNode =  tail.previous;
                map.remove(removeNode.key);
                remove(removeNode);
            }

        }

        // exist
        else{
            // Node current = map.get(key);
            current.data = value;
            remove(current);
            addFirst(current);
        }
    }
    public void remove(Node node){
        Node previousNode = node.previous;
        Node nextNode = node.next;
        nextNode.previous = previousNode;
        previousNode.next = nextNode;

    }
    public void addFirst(Node node){
        Node nextNode =  head.next;
        nextNode.previous = node;
        node.next = nextNode;
        node.previous = head;
        head.next = node;
    }
}






/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */