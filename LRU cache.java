Lclass LRUCache {

    class DLList{

        class DLListNode{
            int key;
            int value;
            DLListNode next;
            DLListNode prev;
            public DLListNode(int key, int value){
                this.key = key;
                this.value = value;
            }
        }

        DLListNode head;
        DLListNode tail; 
        int size = 0;
        public DLList(){
            this.head = new DLListNode(-1, -1);
            this.tail = new DLListNode(-1, -1);
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public DLListNode insertToHead(int key, int value){
            DLListNode node = new DLListNode(key, value);
            node.prev = this.head;
            node.next = this.head.next;
            node.next.prev = node;
            head.next = node;
            size++;
            return node;
        }

        public void moveToHead(DLListNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public DLListNode removeFromTail(){
            if(size == 0) return null;
            DLListNode node = tail.prev;
            tail.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
            size--;
            return node;
        }
    }

    DLList cache;
    HashMap<Integer, DLList.DLListNode> map;
    int capacity;

    public LRUCache(int capacity) {
        this.cache = new DLList();
        this.map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        DLList.DLListNode node = map.get(key);
        //System.out.println("get");
        cache.moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            DLList.DLListNode node = map.get(key);
            node.value = value;
            //System.out.println("moveToHead");
            cache.moveToHead(node);
            return;
        }
        //System.out.println("insertToHead");
        DLList.DLListNode node = cache.insertToHead(key, value);
        map.put(key, node);
        if(cache.size > this.capacity){
            map.remove(cache.removeFromTail().key);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
