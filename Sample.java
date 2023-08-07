//Problem 1: Flatten nested iterator
// Time Complexity : O(1) amortized
// Space Complexity : O(n) (Maximum Depth)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Your code here along with comments explaining your approach
//Can do it via eager approach and lazy one-> have a stack of iterators which contain all nested iterators and their nested iterators

//Eager approach
// public class NestedIterator implements Iterator<Integer> {
//     List<Integer> li;
//     int i;
//     public NestedIterator(List<NestedInteger> nestedList) {
//         this.li=new ArrayList<>();
//         dfs(nestedList);
//     }
//     private void dfs(List<NestedInteger> list){
//         for(NestedInteger ni: list){
//             if(ni.isInteger()) li.add(ni.getInteger());
//             else{
//                 dfs(ni.getList());
//             }
//         }
//     }

//     @Override
//     public Integer next() {
//        return li.get(i++);
//     }

//     @Override
//     public boolean hasNext() {
//         if(i==li.size()) return false;
//         return true;
//     }
// }

//space: max depth time: O(1) amortized
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextElement;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack=new Stack<>();
        stack.push(nestedList.iterator());
    }
    
    @Override
    public Integer next() {
       return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
       while(!stack.isEmpty()){
           if(!stack.peek().hasNext()){ //doesnt have any element remaining at top of stack
               stack.pop();
           }
           else if((nextElement=stack.peek().next()).isInteger()){ //store top in nexElement(for next call) and check if isInteger
               return true;
           }
           else{
               stack.push(nextElement.getList().iterator()); //else push nextElement iterator to stack
           }
       }
       return false;
    }
}



//Problem 2: LRU cache
// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//Approach-> maintain a hashmap of key and linkedlist so anytime you insert an element, check if its there, if yes change value, remove from linkedlist and move to head(most recently used)
// get function-> check if it is present in map, if yes remove linkedlist and move to head
class LRUCache {

    class Node{
        int key, value;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key=key;
            this.value=val;
        }
    }
    int capacity;
    HashMap<Integer, Node> map;
    Node head,tail;

    private void addToHead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    private void removeNode(Node node){
        node.prev.next=node.next; //prev pointer to next of 'node'
        node.next.prev=node.prev; //next of node prev link to node.prev
    }

    public LRUCache(int capacity) {
       this.map=new HashMap<>(); 
       this.capacity=capacity;
       this.head=new Node(-1,-1);
       this.tail=new Node(-1,-1);
       head.next=tail;
       tail.prev=head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))return -1;
        Node node=map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){ //node contains
            Node node= map.get(key);
            node.value=value;
            removeNode(node);
            addToHead(node);
        }
        else{
            //fresh node
            if(capacity==map.size()){
                Node tailPrev= tail.prev;
                //remove from linkedlist
                removeNode(tailPrev);
                //get key of tailPrev for removing form map
                map.remove(tailPrev.key);
            }
            Node newNode= new Node(key, value);
            addToHead(newNode);
            map.put(key,newNode);
            
        }
    }
}