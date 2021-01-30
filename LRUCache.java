// Time Complexity : The time complexity is O(1) for put and get operations
// Space Complexity : The space complexity is O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class LRUCache {

    Map<Integer,CustomNode> map;
    CustomNode head;
    CustomNode tail;
    int capacity;

    public LRUCache(int capacity) {

        map = new HashMap<>();
        this.capacity=capacity;

        head = new CustomNode();
        tail = new CustomNode();

        head.next = tail;
        tail.prev = head;

    }

    public int get(int key) {

        CustomNode node = map.get(key);

        // If key is present in the map, return its value or return -1
        if(node == null){
            return -1;
        }

        addToHead(node);
        return node.value;

    }

    public void put(int key, int value) {

        CustomNode node = map.get(key);

        // If key is already present update its value
        if(node != null){
            node.value = value;
            addToHead(node);
        }
        // If key is not present, add it in the map according to the capacity
        else{
            node = new CustomNode(key,value);

            map.put(key,node);
            add(node);

            if(map.size() >= capacity+1){
                CustomNode temp = removeFromTail();
                map.remove(temp.key);
            }
        }

    }

    // To add a node to the front
    public void add(CustomNode node){

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    // Remove
    public void remove(CustomNode node){

        CustomNode prev = node.prev;
        CustomNode next = node.next;

        prev.next = next;
        next.prev = prev;

    }

    // Remove and add to the front
    public void addToHead(CustomNode node){

        remove(node);
        add(node);
    }

    // remove from end
    public CustomNode removeFromTail(){

        CustomNode temp = tail.prev;
        remove(temp);
        return temp;
    }
}

class CustomNode{

    int key;
    int value;
    CustomNode prev;
    CustomNode next;

    public CustomNode(){
        this.key = -1;
        this.value = -1;
    }

    public CustomNode(int key,int value){
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