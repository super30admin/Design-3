import java.util.HashMap;

//Time Complexity: O(1)
//Space Complexity: O(c); where c is capacity
/**Approach: HashMap + Doubly LinkedList**/
public class LRUCache {	
	//Definition for doubly LinkedList
	 class ListNode{
        int key;
        int val;
        ListNode prev;
        ListNode next;    
        public ListNode(int key, int val){
            this.key= key;
            this.val= val;
        }
    }
	//Insert node to the head of the list
    private void insertNode(ListNode node){
        node.prev=head;
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
    }
    //Remove node
    private void removeNode(ListNode node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    
    //LRUCache Implementation
    int capacity;
    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> map;    
    public LRUCache(int capacity) {
        map= new HashMap<>();
        this.capacity= capacity;        
        this.head= new ListNode(-1,-1);
        this.tail= new ListNode(-1,-1);
        this.head.next=tail;
        this.tail.prev=head;
    }           
    
    public int get(int key) { //O(1)
        if(!map.containsKey(key)) return -1;
        ListNode node= map.get(key);
        removeNode(node);
        insertNode(node);
        return node.val;
    }
    
    public void put(int key, int value) { //O(1)
         ListNode node;
        //Replace
        if(map.containsKey(key)){            
            node= map.get(key);
            node.val=value;
            removeNode(node);
            insertNode(node);            
        }
        //Add
        else{
            if(map.size() == capacity){
                ListNode last= tail.prev;
                removeNode(last);
                map.remove(last.key);
            }
            node= new ListNode(key,value);   
            insertNode(node);
            map.put(key, node);            
        }
    }

    //Driver code to test above
	public static void main (String[] args) {		
		//Input: 
		//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
		//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]				
		//Output: [null, null, null, 1, null, -1, null, -1, 3, 4]
		LRUCache lRUCache= new LRUCache(2);
		lRUCache.put(1, 1); 					// cache is {1=1}
		lRUCache.put(2, 2); 					// cache is {1=1, 2=2}
		System.out.println(lRUCache.get(1));    // return 1
		lRUCache.put(3, 3); 					// LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		System.out.println(lRUCache.get(2));    // returns -1 (not found)
		lRUCache.put(4, 4); 					// LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		System.out.println(lRUCache.get(1));    // return -1 (not found)
		System.out.println(lRUCache.get(3));    // return 3
		System.out.println(lRUCache.get(4));   	// return 4
	}
}
