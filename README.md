# Design-3

## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)

## Time Complexity : O(1)
## Space Complexity : O(N)

public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextEle; 
    Stack<Iterator<NestedInteger>> st;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>(); 
        st.push(nestedList.iterator()); 
    }

    @Override
    public Integer next() {
        return nextEle.getInteger(); 
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            
            if(!st.peek().hasNext()) st.pop(); 
            else if((nextEle = st.peek().next()).isInteger()) return true;
            else st.push(nextEle.getList().iterator());
        }
        return false;
    }
}


## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

## Time Complexity : O(1) for get and put 
## Space Complexity : O(N)

class LRUCache {
    
    class Node{
        int key;
        int value; 
        Node prev;
        Node next; 
        
        public Node(int key, int value){
            this.key = key;
            this.value = value; 
        }  
    }
    
    private void removeNode(Node node){
        node.next.prev = node.prev; 
        node.prev.next = node.next; 
    }
    
    private void addToHead(Node node){
        node.next = head.next; 
        node.prev = head; 
        head.next = node; 
        node.next.prev = node; 
    }

    HashMap<Integer, Node> map; 
    Node head; 
    Node tail; 
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity; 
        head = new Node(-1,-1);
        tail = new Node(-1,-1); 
        head.next = tail; 
        tail.prev = head;
        map = new HashMap<>(); 
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
            node.value = value; 
            removeNode(node);
            addToHead(node);
        }else{
            if(capacity == map.size()){
                Node tailPrev = tail.prev; 
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
