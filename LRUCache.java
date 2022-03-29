import java.util.HashMap;
/*
Time Complexity: O(1) for getValue and O(1) for putValue as we are using Doubling linked list
Space Complexity: O(CACHESIZE)
Run on Leetcode: yes
Approach:
1. I am using doubling linked list and HashMap to design LRU cache,
2. My HashMap would store key as the number we are looking for the search and value as the doubly linked list which would
help us for every we want to evict the least recently used value
 */
public class LRUCache {
    public class DL {
        DL left;
        DL right;
        int value;
        int key;

    }

    private HashMap<Integer, DL> map;
    private DL start;
    private DL end;

    private final int CACHESIZE = 4;

    LRUCache() {
        map = new HashMap<>();
        start = null;
        end = null;
    }

    public int getValue(int key) {
        int value = -1;
        if (map.containsKey(key)) {
            DL curr = map.get(key);
            value = curr.value;
            removeValue(curr);
            addToFront(curr);
        }
        return value;
    }

    public void putValue(int key, int value) {
        if (map.containsKey(key)) {
            DL curr = map.get(key);
            curr.value = value;
            addToFront(curr);
        }

        DL element = new DL();
        element.key = key;
        element.value = value;
        element.left = null;
        element.right = null;

        if (map.size() > CACHESIZE) {
            map.remove(end.key);
            removeValue(end);
            map.put(key, element);
            addToFront(element);
        } else {
            map.put(key, element);
            addToFront(element);
        }
    }

    // Everytime a key is asked, it will gonna add to the Front of the Doubly LinkedList
    public void addToFront(DL element) {
        element.right = start;
        if (start != null) {
            element.left = null;
            start.left = element;
        }

        start = element;
        if (end == null) {
            end = start;
        }
    }

    // Would remove it from the end
    public void removeValue(DL element) {
        if (element.left != null) {
            element.left.right = element.right;
        } else {
            start = element.right;
        }

        if (element.right != null) {
            element.right.left = element.left;
        } else {
            end = element.left;
        }

    }
}