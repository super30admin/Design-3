/* Time complexity: O(1)
space complexity: O(1)
*/
class LRUcache{
    class Node{
        int key; int val;
        Node prev; Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    HashMap<Integer, Node> map;
    Node head; Node tail;
    int capacity;
    public LRUcache(int capacity){
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail; tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key){
        Node node = map.get(key);
        if(node != null){
            int result = node.val;
            removeNode(node);
            addToHead(node);
            return result;
        }
        return -1;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }else{
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tail.prev);
                map.remove(tailPrev.key);
            }

            Node newNode = new Node(key, value){
                addToHead(newNode);
                map.put(key, newNode);
            }
        }
    }
}