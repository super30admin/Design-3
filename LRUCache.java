
// TC : get- O(1), put-O(1)
// SC : O(c)  //c=capacity

package S30_Codes.Design_3;
import java.util.HashMap;
import java.util.Map;

class DLLNode{
    public int key;
    public int value;
    public DLLNode prev;
    public DLLNode next;

    DLLNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class DLL{
    DLLNode head, tail;

    DLL(){
        head = new DLLNode(-1, -1);
        tail =  new DLLNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public void insertNode(DLLNode node){
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
    }

    public void removeNode(DLLNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    public DLLNode getHead(){
        return head.next;
    }
}

class LRUCache {
    int capacity;
    Map<Integer, DLLNode> nodeMap;
    DLL dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        dll = new DLL();
    }

    public int get(int key) {
        DLLNode node = nodeMap.get(key);
        if(node == null){
            return -1;
        }
        dll.removeNode(node);
        dll.insertNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLLNode node = nodeMap.get(key);
        if(node == null){
            if(nodeMap.size() == capacity){
                DLLNode head = dll.getHead();
                dll.removeNode(head);
                nodeMap.remove(head.key);
            }
            node = new DLLNode(key, value);
        }
        else{
            dll.removeNode(node);
            node.value = value;
        }
        dll.insertNode(node);
        nodeMap.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */