// Time Complexity : O(1) 
// Space Complexity : O(n) , 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :no


/*
We need to have a doubly linked list, to store the ordering of LRU so that we can evict the LRU and add a new one to MRU.
We will also have a hashmap to store the references of the key with ref of the nodes for easy finding and deletion

*/

class LRUCache {

    class DoublyLinkedList{
        DoublyLinkedList prev;
        DoublyLinkedList next;
        int key;
        int val;

        public DoublyLinkedList(int k, int v){
            key = k;
            val = v;
        }
    }

    DoublyLinkedList head = new DoublyLinkedList(-1,-1);
    DoublyLinkedList tail = new DoublyLinkedList(-1,-1);

    int size;

    HashMap<Integer, DoublyLinkedList> map ;

    private void insert(DoublyLinkedList node){
        node.next = tail;
        node.prev = tail.prev;
        node.prev.next = node;
        node.next.prev = node;

        map.put(node.key , node);
    }

    private void delete(DoublyLinkedList node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        map.remove(node.key);
    }

    private DoublyLinkedList getNode(int key){
        if(!map.containsKey(key)){
            return null;
        }
        DoublyLinkedList node = map.get(key);
        delete(node); // remove from current
        insert(node); // put in MRU position
        return node;
    }

    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        size = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        DoublyLinkedList node = getNode(key);
        if(node == null){
            return -1;
        }
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).val = value; // update new value
            getNode(key); // since we used it , we may need to bring it to mru, so we will call get and it will do that for us
        }
        else if (map.size() >= size){
            // This means we need to evict LRU
            DoublyLinkedList nodeDel = head.next; // head..next pointing to LRU
            System.out.println("Delete : " + nodeDel.key);
            delete(nodeDel); // delete LRU
            DoublyLinkedList newNode = new DoublyLinkedList(key,value);
            insert(newNode);
            System.out.println("Insert:"+newNode.key);
        }
        else{  // this means we can simply insert the new node
          DoublyLinkedList newNode = new DoublyLinkedList(key,value);
          insert(newNode);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */