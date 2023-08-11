// Time Complexity :O(1)
// Space Complexity :2O(n) ~ O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :NO


// Your code here along with comments explaining your approach

//2nd Approach using 2 stacks
/*class MinStack {
    Stack<Integer> st;
    Stack<Integer> minSt; //for first approach
    int min;
    public MinStack() {
        this.st = new Stack<Integer>();
        this.minSt = new Stack<Integer>();
        this.min = Integer.MAX_VALUE;
        minSt.push(min);

    }

    public void push(int val) {
       this.min = Math.min(min, val); // First approach
        minSt.push(min);
        st.push(val);


    }

    public void pop() {
        if(st.peek() != null){
            st.pop();
            minSt.pop(); //after poping , min will be whatever is at the top of the minstack, done below
            min = minSt.peek();// v imp : you have to re assign again ;
        }

    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
       return  minSt.peek();
    }
}*/
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
// Time Complexity :O(1)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :NO

class LRUCache {
    //using LL and as we need to optimize it we use hashmap in it for optimizing the search : O(n)
    //now after storing k,v in hashmap we can do get in o(n), but if we need to remove it and put somehwere else , again we need to iterate to find its place, which will again become o(n), so to avoid this we can keep its address so we can quickly find its position and dont have to iterate again

    class Node{//imp
        int key; int val;
        Node next;
        Node prev;
        public Node(int key, int value){
            this.key = key;
            this.val = value;
        }
    }

    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {// so HM required
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;//imp
        tail.prev = head;



    }
    private void addToHead(Node curr){//handle curr/orphan pointers first
        curr.next = head.next;
        curr.prev = head;
        head.next = curr;
        curr.next.prev = curr;

    }

    private void removeNode(Node curr){
        curr.prev.next = curr.next;// removing prev connection and adding to curr's next
        curr.next.prev = curr.prev;


    }

    public int get(int key) {// it gets that node , removes it and add to the head, making it recent
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);//gettign node first
        removeNode(node);
        addToHead(node);
        return node.val;

    }

    public void put(int key, int value) {
        //if node exists or not
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            node.val = value;
            addToHead(node);

        }else{
            //fresh
            //check capacity
            if(capacity == map.size()){
                //remove the LRU node
                Node leastRec = tail.prev;
                removeNode(leastRec);
                //need to remove from map as well: as for size and itshound reflect in future searches so,
                //get that prev elemet key
                map.remove(leastRec.key); //last nodes key
            }
            Node newNode = new Node(key,value);//creating new node a fresh one
            map.put(key,newNode);
            addToHead(newNode);

        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */