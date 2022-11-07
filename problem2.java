package Design-3;

public class problem2 {
    //TC:- O(1)
    //SC:- O(1)
    class LRUCache {
        class Node{
            int key, value;
            Node next, prev;
            public Node(int key, int value){
                this.key = key;
                this.value = value;
            }
        }
        
        HashMap<Integer, Node> map;
        Node head, tail;
        int capacity;
        
        private void removeNode(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        private void addToHead(Node node){
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }
    
        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head = new Node(-1,-1);
            tail = new Node(-1,-1);
            head.next = tail;
            tail.prev = head;
        }
        
        public int get(int key) {
            if(!map.containsKey(key)) return -1;
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        
        public void put(int key, int value) {
            if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value; 
            return;
                
            }
            
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            map.put(key, newNode);
            addToHead(newNode);
          
        }
    }
}
