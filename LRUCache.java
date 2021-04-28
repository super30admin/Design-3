//Time complexity - get - O(1), put - O(1)
//Space complexity - O(capacity)

class LRUCache {

    class DL_list{
        public int key;
        public int value;
        public DL_list next;
        public DL_list prev;
    }
    public void addnode(DL_list node)
    {
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;
    }
    
    public void deletenode(DL_list node)
    {
        DL_list prevnode=node.prev;
        DL_list nextnode=node.next;
        prevnode.next=nextnode;
        nextnode.prev=prevnode;
    }
    
    public void movetohead(DL_list node)
    {
        deletenode(node);
        addnode(node);
    }
    
    public DL_list poptail()
    {
        DL_list todelete=tail.prev;
        deletenode(todelete);
        return todelete;
    }
    
    Map<Integer,DL_list> cache=new HashMap<>();
    private int size;
    private int capacity;
    private DL_list head,tail;
    public LRUCache(int capacity) {
        Map<Integer,DL_list> cache=new HashMap<>();
        this.size=0;
        this.capacity=capacity;
        head=new DL_list();
        tail=new DL_list();
        head.next=tail;
        tail.prev=head;
    }
    
    public int get(int key) {
        DL_list node=cache.get(key);
        if(node==null)
            return -1;
        movetohead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DL_list node=cache.get(key);
        if(node==null)
        {
            DL_list newnode=new DL_list();
            newnode.key=key;
            newnode.value=value;
            cache.put(key,newnode);
            addnode(newnode);
            ++size; 
            if(size>capacity)
            {
                DL_list deletenode=poptail();
                cache.remove(deletenode.key);
                --size;
            }
        }
        else
        {
            node.value=value;
            movetohead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
