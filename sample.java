//o(1) time and o(n) space

class LRUCache {


    class Node{
        int key; int val;
        Node prev; Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    private void removeNode(Node node){
        node.next.prev= node.prev;
        node.prev.next= node.next;
    }
    private void addToHead(Node node){
        node.next= head.next;
        node.prev = head;
        head.next = node;
        node.next.prev= node;
    }
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        tail = new Node(-1,-1);
        head = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            if (capacity == map.size()){
                //remove lru node;
                Node tailPrev = (tail.prev);
                removeNode(tailPrev);
                map.remove(tailPrev.key);


            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}

//o(height of nesting) space and o(n) time


public class NestedIterator implements Iterator<Integer> {


    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextEl = st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(nextEl.getList().iterator());
            }
        }
        return false;

    }
}
