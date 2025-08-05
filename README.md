# Design-3

## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)
TC: O(1)
SC: O(n)

//Approach:-
1. we will use stack to store the elements of the list
2. if the top element in stack is integer we will remove it from the stack and return it
3. if its a list then we will put the elements of that list in the stack from last element to first element
4. We will continue this process till stack is empty

public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        // Push in reverse order
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        // hasNext() should have already guaranteed there's an integer on top
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger curr = stack.peek();
            if (curr.isInteger()) {
                return true;
            }
            stack.pop();
            List<NestedInteger> list = curr.getList();
            // Push this list’s elements in reverse order
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return false;
    }
}



## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)
TC: O(1)
SC: O(n)

Approach:
1. We will use hashmap to store the node and its value and the new node will always be added in the front and if the element already exists then we will update its value and move it to the front
2. if the size of the hashmap exceeds the capacity we will remove the last node
class LRUCache {
    class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node head, tail; // Dummy nodes for easy list manipulation

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(-1, -1); // dummy head
        tail = new Node(-1, -1); // dummy tail

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        moveToHead(node); // Mark node as most recently used
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Key exists, update value and move to front
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // Key doesn't exist, create new node
            if (map.size() == capacity) {
                // Remove least recently used node
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    // Removes node from its current position
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Adds node right after the dummy head (most recently used position)
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    // Moves an existing node to the head (marking it most recently used)
    private void moveToHead(Node node) {
        removeNode(node);   // unlink it
        addToHead(node);    // link at front
    }
}
