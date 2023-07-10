import java.util.HashMap;

public class cacheLRU {

        // Doubly Linked List, Hashmap
        public static class NodeDLL {

            int key; int value;
            NodeDLL prev; NodeDLL next;

            public NodeDLL(int key, int value) {

                this.key = key;
                this.value = value;
            }
        }

        public void removeNode(NodeDLL node) {

            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        public void addToHead(NodeDLL node) {

            node.prev = head;
            node.next = head.next;

            head.next = node;
            node.next.prev = node;
        }

        private int capacity;
        private HashMap<Integer, NodeDLL> mapKeyToNode;
        private NodeDLL head;
        private NodeDLL tail;

        public void LRUCache(int capacity) {

            this.capacity = capacity;
            this.mapKeyToNode = new HashMap<>();
            this.head = new NodeDLL(-1, -1);
            this.tail = new NodeDLL(-1, -1);
            this.head.next = this.tail;
            this.tail.next = this.head;
        }

        public int get(int key) {

            if(mapKeyToNode.containsKey(key)) {

                //get node from hashmap, make it MRU and return value
                NodeDLL nodeMRU = mapKeyToNode.get(key);

                removeNode(nodeMRU);

                addToHead(nodeMRU);

                return nodeMRU.value;
            }
            // if given key is not there in cache
            return -1;
        }

        public void put(int key, int value) {

            // If node with given key already exists, update its value, make it MRU
            if (mapKeyToNode.containsKey(key)) {

                NodeDLL nodeUpdate = mapKeyToNode.get(key);

                nodeUpdate.value = value;

                removeNode(nodeUpdate);

                addToHead(nodeUpdate);
            }

            //if new node is given
            else {

                // if capacity is at full, remove an LRU node
                if (capacity == mapKeyToNode.size()) {

                    NodeDLL nodeLRU = tail.prev;

                    removeNode(nodeLRU);

                    mapKeyToNode.remove(nodeLRU.key);
                }

                NodeDLL nodeNew = new NodeDLL(key, value);

                //add new node to head in doubly linked list and hashmap
                addToHead(nodeNew);

                mapKeyToNode.put(key, nodeNew);


            }


        }

}

/*
TIME COMPLEXITY = O(1) - both get, put and all operations done in O(1)
SPACE COMPLEXITY = O(N)
N = capacity given, max space for hashmap and doubly linked list
*/

/*
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */