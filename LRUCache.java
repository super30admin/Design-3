import java.util.*;

class LRUCache {
    class Node{
        int key,val;
        Node next,prev;

        public Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }

    HashMap<Integer,Node> map;
    Node root;
    int capacity;
    Node head,tail;
    int size;

    public LRUCache(int capacity) {
        map=new HashMap<>();
        this.capacity=capacity;
        tail=new Node(-1,-1);
        head=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }

    private void remove(Node node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }

    private void addToHead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;
    }

    private Node removeFromTail(){
        Node oldNode=tail.prev;
        remove(oldNode);
        return oldNode;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node oldNode = map.get(key);
        remove(oldNode);
        addToHead(oldNode);
        return oldNode.val;
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)){
            if(size==capacity){
                Node oldNode = removeFromTail();
                map.remove(oldNode.key);
                size--;
            }
            Node newNode=new Node(key,value);
            map.put(key,newNode);
            addToHead(newNode);
            size++;
        }
        else{
            Node node=map.get(key);
            node.val=value;
            remove(node);
            addToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */