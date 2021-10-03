package Design3;

import java.util.HashMap;

public class LRUCache {
    class DListNode{
        int key;
        int val;
        DListNode prev, next;
        DListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
        DListNode head, tail;
        int capacity;
        HashMap<Integer, DListNode> map;
        public LRUCache(int capacity) {
            head = new DListNode(-1, -1);
            tail = new DListNode(-1, -1);
            head.next = tail;
            tail.prev = head;
            this.capacity = capacity;
            map = new HashMap();
        }

        private void remove(DListNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }

        private void add(DListNode elem){
            elem.next = head.next;
            elem.prev = head;
            head.next = elem;
            elem.next.prev = elem;
        }

        public int get(int key) {
            if(map.containsKey(key)){
                DListNode node = map.get(key);
                remove(node);
                add(node);
                return node.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                DListNode node =  map.get(key);
                node.val = value;
                remove(node);
                add(node);
            } else {
                DListNode elem = new DListNode(key, value);
                map.put(key, elem);
                add(elem);
                if(map.size() > capacity)
                {
                    map.remove(tail.prev.key);
                    remove(tail.prev);

                }

            }
        }
}
