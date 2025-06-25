//TC: O(1) for all operations
//SC: O(capacity)
class LRUCache {

    class Node{
        int key;
        int value;

        Node next;
        Node prev;

        Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }

    int capacity;
    HashMap<Integer, Node> map;
    Node dummyHead;
    Node dummyTail;

    public LRUCache(int capacity) {

        this.capacity=capacity;
        this.map=new HashMap<>();

        dummyHead=new Node(-1,-1);
        dummyTail=new Node(-1,-1);

        dummyHead.next=dummyTail;
        dummyTail.prev=dummyHead;


    }
    public void removeNode(Node node){// tail maintains the least recently accessed node

        node.prev.next=node.next;
        node.next.prev=node.prev;

        node.prev=null;
        node.next=null;


    }

    public void moveToHead(Node node){// head maintains the most recently accessed node

        node.next=dummyHead.next;
        node.prev=dummyHead;

        dummyHead.next.prev=node;
        dummyHead.next=node;
    }
    public int get(int key) {

        if(map.containsKey(key)){

            Node node=map.get(key);

            removeNode(node);

            moveToHead(node);

            return node.value;

        }
        return -1;
    }

    public void put(int key, int value) {

        if(map.containsKey(key)){//existing node

            Node node=map.get(key);
            node.value=value;

            removeNode(node);
            moveToHead(node);

        }
        else { //non existing node

            if(map.size()==capacity){  //capapcity reached, remove LRU node from tail to make space

                Node lastNode=dummyTail.prev;
                removeNode(lastNode);
                map.remove(lastNode.key); //remove deleted node from hashmap
            }
            Node newNode= new Node(key,value);
            map.put(key,newNode);
            moveToHead(newNode); //add the new node to head

        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */