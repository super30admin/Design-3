import java.util.HashMap;
import java.util.Map;


// ***************************** without dummy nodes *****************************
public class LRUcache {                                                // CLASS LRUCACHE

    int capacity;
    Map<Integer, DLLNode> map;
    DLL list;

    public LRUcache(int capacity) {                                    // constructor
        this.capacity = capacity;
        map = new HashMap<>();
        list = new DLL();
    }

    public int get(int key) {                                          // get
        DLLNode node = map.get(key);
        if(node == null) {
            return -1;
        }
        list.remove(node);
        list.addLast(node);
        return node.value;
    }

    public void put(int key, int value) {                              // put

        DLLNode node = map.get(key);

        // if node not present
        if(node == null) {
            if(map.size() == capacity) {
                // remove LRU node
                map.remove(list.head.key);
                list.remove(list.head);
            }
            node = list.addLast(key, value);
            map.put(key, node);
        }

        // if node present
        else {
            list.remove(node);
            node.value = value;
            list.addLast(node);
        }

    }
}



class DLL {                                                           // CLASS DLL
    DLLNode head, tail;

    DLL() {                                                           // constructor
        head = tail = null;
    }

    public void addLast(DLLNode node) {                               // add last existing node

        if(head == null && tail == null) {
            head = tail = node;
        }

        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public DLLNode addLast(int key, int value) {                      // add last new node
        DLLNode node = new DLLNode(key, value);
        addLast(node);
        return node;
    }

    public void remove(DLLNode node) {                               // remove node

        if(node == head && node == tail) {
            head = tail = null;
        }

        else if(node == head) {
            head = head.next;
            head.prev = null;
            node.next = null;
        }

        else if(node == tail) {
            tail = tail.prev;
            tail.next = null;
            node.prev = null;
        }

        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }
}


class DLLNode {                                                      // CLASS DLLNODE
    int key, value;
    DLLNode prev, next;
    DLLNode(int key, int value) {                                    // constructor
        this.key = key;
        this.value = value;
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */