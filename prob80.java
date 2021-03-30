//Tc:O(1)
//Sc:O(capacity)


class LRUCache {
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.val = value;
            
        }
    }
    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        
    }
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    HashMap<Integer, Node> map;
    Node head; Node tail;
    int capacity;
    public LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
    map = new HashMap<>();
    head = new Node(-1,-1);
        tail= new Node(-1,-1);
        head.next = tail;
        tail.prev= head;
    }
    public int get(int key){
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    public void put(int key, int value){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }
    }