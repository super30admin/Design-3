//Time Complexity : put -> O(1) get -> O(1)
// Space Complexity: O(N) where N is the size of the LRU Cache

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class DLLNode{

        int key;
        int value;
        DLLNode prev;
        DLLNode next;

        public DLLNode(int key, int value){

            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }

    }

    Map<Integer, DLLNode> map;
    DLLNode head;
    DLLNode tail;
    int limit;

    public LRUCache(int capacity) {

        map = new HashMap<>();
        this.limit = capacity;
        head = new DLLNode(-1,-1);
        tail = new DLLNode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key)){

            DLLNode node = map.get(key);
            remove(node);
            insert(node);

            return node.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {

        if(map.containsKey(key)){ // Update the value

            DLLNode existingNode = map.get(key);

            remove(existingNode);

            insert(existingNode);

            existingNode.value = value;


        }else{ //Create a node and Insert into LRU

            DLLNode newNode = new DLLNode(key,value);

            if(map.size() == limit){ // Remove least recently used element and insert the newNode

                map.remove(tail.prev.key);
                remove(tail.prev);

                map.put(key,newNode);
                insert(newNode);

            }else{

                map.put(key, newNode);
                insert(newNode);
            }
        }
    }

    private void insert(DLLNode node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    private void remove(DLLNode node){

        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }


}
