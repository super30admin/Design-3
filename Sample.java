// Problem-1: Flatten Nested List Iterator
//TC= O(n) n = total number of elements in all the lists
//SC= O(n)

/**
Solution using stack of "list iterators". Everytime if an element that we are currently pointing at is not an integer(it means it is a nestedList), it is pushed to the stack. Kepp doing that until the element we are pointing at is not an integer. 
*/
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> s;
    NestedInteger curInt;
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return curInt.getInteger();
    }

    @Override
    public boolean hasNext() {
        
        while(!s.isEmpty()){
            if(!s.peek().hasNext()){
                s.pop();
            }else{
                curInt = s.peek().next();
                if(curInt.isInteger()){
                    return true;
                }else{
                    s.push(curInt.getList().iterator());
                }
            }
        }
        return false;
    }
}

// Problem-2: LRU Cache
//TC= O(1)
//SC= O(k) where k is the capacity of cache

class LRUCache {
    class Node{
        int key; int val;
        Node next; Node prev;
        
        public Node(int key, int value){
            this.key = key;
            this.val = value;
        }
           
    }
    
    public void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    // Always adding to the head of the existing linked list
    public void addNode(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    int cap;
    Node head, tail;
    HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
         head = new Node(-1,-1);
         tail = new Node(-1,-1);
        
        head.next = tail;
        tail.prev = head;
        
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node cur = map.get(key); // get the existing value of the key
        removeNode(cur); // remove it from the linked list to change its position to be the most recent
        addNode(cur); // add it to the head of the linked list
        
        return cur.val; 
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)){
            Node cur = map.get(key); // get the existing value of the key
            cur.val = value; // updating its existing value to the new value
            removeNode(cur); // remove it from the linked list to change its position to be the most recent
            addNode(cur); // add it to the head of the linked list
        }else{
            Node newNode = new Node(key, value);
            // to add new node, first condition is to check if the cache capacity is already full
            if(map.size() == cap){
                // remove the least recently used node
                Node lastVal = tail.prev; //least used value would be prev to the tail node
                removeNode(lastVal);
                map.remove(lastVal.key);
            }
            addNode(newNode);
            map.put(key, newNode);
        }
    }
}

