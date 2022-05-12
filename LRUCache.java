class LRUCache {

    //Time Complexity : 0(1)
    //Space Complexity: 0(c) where c is the capacity
    //Did it successfully run on leetcode: Yes
    //Did you face any problem while coding: No

    //In brief explain your approach:

    class ListNode{ //Creating a double linked list to store the key value pair and for easy deletion and addition
        int key, val;
        ListNode next, prev;
        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    ListNode head, tail;    //declaring a dummy head and tail for accessing the 1st and last nodes
    HashMap<Integer, ListNode> map; //for constant time retrieval of data
    int capacity;   //making capacity a global variable

    public void removeNode(ListNode node){  //to remove a node from middle if it is recently accessed or remove it from last if it has not been accessed and when the capacity is full
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addNode(ListNode node){ //adding a node to the top of the linked list whenever a new put or get method is called
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public LRUCache(int capacity) { //Declaring dummy nodes and asigning the capacity
        map = new HashMap<>();
        this.capacity = capacity;
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)){  //if the key is not present in the cache return -1
            return -1;
        }
        ListNode node = map.get(key);   //if present, break the current link and move it to the top of the linked list as it is recently used
        removeNode(node);
        addNode(node);
        return node.val;        //return the value of the key
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){   //if the key is present in cache, update its value and bump it to the top
            ListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addNode(node);
            return;
        }
        if(map.size() == capacity){ //if it is not present then we 1st check if the cache capacity is full or not. I full,
            //then we remove the LRU and then make space for the new value
            ListNode lastNode = tail.prev;
            removeNode(lastNode);
            map.remove(lastNode.key);
        }
        ListNode newNode = new ListNode(key, value); //then add the key value to the memory
        map.put(key, newNode);
        addNode(newNode);
        return;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */