class LRUCache {

    Map<Integer, DLLNode> map;
    int capacity;
    DLL list;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DLL();
    }
    
    public int get(int key) {
        DLLNode node = this.map.get(key);
        if(node==null){
            return -1;
        }
        
        this.list.remove(node);
        node = this.list.addLast(node.key, node.value);
        this.map.put(key, node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DLLNode node = map.get(key);
        if(node==null){
            if(this.map.size() == this.capacity){
                this.map.remove(this.list.head.key);
                this.list.remove(this.list.head);
            }
            node = this.list.addLast(key, value);
            this.map.put(key, node);
        }else{
            this.list.remove(node);
            node.value = value;
            node = this.list.addLast(node.key, node.value);
            this.map.put(node.key, node);
        }
        
    }
}

class DLLNode{
    int key;
    int value;
    DLLNode prev;
    DLLNode next;
    
    DLLNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class DLL{
    DLLNode head;
    DLLNode tail;
    
    DLL(){
        this.head = null;
        this.tail = null;
    }
    
    public DLLNode addLast(int key, int value){
        DLLNode node = new DLLNode(key, value);
        if(head==null && tail==null){
            head = node;
            tail = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        return node;
    }
    
    public void remove(DLLNode node){

        if(head.equals(node) && tail.equals(node)){
            head = tail = null;
        }else if(head.equals(node)){
            head = head.next;
            head.prev = null;
            node.next = null;
        }else if(tail.equals(node)){
            tail = tail.prev;
            tail.next = null;
            node.prev=null;
        }else{
            System.out.println(node.key + " " + node.value);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
