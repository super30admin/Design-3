//time - O(1)
//space - O(capacity)
class LRUCache {

    class Node{
        int key, value;
        Node next, prev;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> map;
    Node head, tail;
    int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    private void addToHead(Node node){
        Node temp = head.next;
        node.next = temp;
        node.prev = head;
        temp.prev = node;
        head.next = node;
    }

    private void remove(Node node){
        Node temp = node;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node temp = map.get(key);
        remove(temp);
        addToHead(temp);
        return temp.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            remove(temp);
            temp.value = value;
            addToHead(temp);
            return;
        }
        else{
            if(map.size()==capacity){
                Node temp = tail.prev;
                map.remove(temp.key);
                remove(temp);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}
