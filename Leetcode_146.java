//Doubly linkedlist - for easy placing and removing from list
//HashMap - easy searching
//Get - check if present in hashmap or not. If yes, remove it and place it at front as recently used
//Put - check if already key is present in map. If yes, update it. Else, check size of map. If size is not reached capacity, add node at first. Else, remove last element and add at first
//TC: get and put: O(1)
//SC: Given capacity
class LRUCache {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        private Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    Map<Integer,Node> map=new HashMap<>();
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);

        head.next=tail;
        tail.prev=head;
        
    }

    private void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    private void addFirst(Node node){
        node.next=head.next;
        head.next=node;
        node.next.prev=node;
        head.next.prev=head;


    }
    
    public int get(int key) {
        if(!(map.containsKey(key))){
            return -1;
        }

        Node node=map.get(key);
        removeNode(node);
        addFirst(node);

        return node.value;
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.value=value;
            removeNode(node);
            addFirst(node);
        }else{
            if(map.size()==capacity){
                Node node=tail.prev;
                removeNode(node);
                map.remove(node.key);
                Node fresh=new Node(key,value);
                addFirst(fresh);
                map.put(key,fresh);

            }else{
                Node fresh=new Node(key,value);
                addFirst(fresh);
                map.put(key,fresh);

            }
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
