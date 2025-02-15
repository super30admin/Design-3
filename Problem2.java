public class Problem2 {
    class LRUCache {
        class Node{
            int key;
            int val;
            Node prev;
            Node next;
            Node(int key, int val){
                this.key = key;
                this.val = val;
            }
        }
        Node head, tail;
        HashMap<Integer,Node> map;

        int cap;

        private void addToHead(Node node){
            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;
        }

        private void removeNode(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public LRUCache(int capacity) {
            map = new HashMap<>();
            head = new Node(-1,-1);
            tail =  new Node(-1,-1);
            head.next = tail;
            tail.prev = head;
            this.cap = capacity;
        }

        public int get(int key) {
            if(!map.containsKey(key)){
                return -1;
            }
            Node curr =  map.get(key);
            removeNode(curr);
            addToHead(curr);
            return curr.val;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                Node curr =  map.get(key);
                removeNode(curr);
                curr.val = value;
                addToHead(curr);
            }
            else{
                if(map.size()>=cap){
                    Node nodeToBeRemoved = tail.prev;
                    removeNode(nodeToBeRemoved);
                    map.remove(nodeToBeRemoved.key);
                }
                Node curr = new Node(key,value);
                map.put(key,curr);
                addToHead(curr);
            }

        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
