// Time Complexity : O(1)
// Space Complexity : O(Capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class DLL{
    int key;
    int value;
    DLL next;
    DLL prev;
    public DLL(int key,int value){
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}
class LRUCache {

    int capacity;
    HashMap<Integer,DLL> cache;
    DLL head,tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new DLL(-1,-1);
        this.tail = new DLL(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        
    }
    private void addToHead(DLL node){
        node.next = this.head.next;
        this.head.next.prev = node;
        node.prev = this.head;
        this.head.next = node;
    }
    private void remove(DLL node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
    }
    
    
    public int get(int key) {
        if(this.cache.containsKey(key)){
            DLL node = this.cache.get(key);
            this.remove(node);
            this.addToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(this.get(key) != -1){
            this.cache.get(key).value = value;
        }else{
            DLL node = new DLL(key,value);
            this.cache.put(key, node);
            this.addToHead(node);
            if(this.cache.size()>this.capacity){
                DLL tailPrev = this.tail.prev;
                remove(tailPrev);
                this.cache.remove(tailPrev.key);
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
