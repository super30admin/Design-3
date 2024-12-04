import java.util.HashMap;
//Time Complexity: O(1) for both get and put operations.
//Space Complexity: O(capacity).
public class problem2 {

    class LRUCache {

        HashMap<Integer, Node> map;
        int capacity;
        Node head, tail;
        class Node {
            int key;
            int value;
            Node prev, next;
            public Node(int key, int value)
            {
                this.key = key;
                this.value = value;
            }

        }


        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map= new HashMap<>();
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }
        private void removeNode(Node curr){
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.prev = null;
            curr.next = null;
        }
        private void addToHead(Node curr) {
            curr.next = head.next;
            curr.prev = head;
            head.next.prev = curr;
            head.next = curr;
        }
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;

            }
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                removeNode(node);
                addToHead(node);
            }
            else {
                if (map.size() == capacity) {
                    Node lruNode= tail.prev;
                    removeNode(lruNode);
                    map.remove(lruNode.key);
                }
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);
            }
        }
    }

}

