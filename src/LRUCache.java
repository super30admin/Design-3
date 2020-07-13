// Time Complexity : O(1) for get and put operations
// Space Complexity : O(2n) considering the hashmap and linkedList
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

/**
 * maintain doubly linkedList of nodes containing key and value, recently accessed node moved to tail, if capacity full
 * node from front (least recently used) is removed.
 * 
 * maintain hashmap of key and nodes for O(1) time for finding the node in the linkedList
 * https://leetcode.com/problems/lru-cache/
 * 
 *
 */
class LRUDataObject{
	int value;
	int useCnt;

	public LRUDataObject(int value, int useCnt)
	{
		this.value = value;
		this.useCnt = useCnt;
	}

	public int getUseCnt()
	{
		return this.useCnt;
	}

	public int getValue()
	{
		return this.value;
	}

	public void setUseCnt(int useCnt)
	{
		this.useCnt = useCnt;
	}

}

class LRUCache {

	int capacity;
	int currentCount;
	Map<Integer, LRUDataObject> cache;

	public LRUCache(int capacity) {
		this.cache = new HashMap<Integer,LRUDataObject>();
		this.capacity = capacity;
		this.currentCount = 0;
	}

	public int get(int key) {

	}

	public void put(int key, int value) {

		LRUDataObject object = new LRUDataObject(value,0);
		if(!cache.containsKey(key) && currentCount < capacity)
		{ 
			cache.put(key,object);  
		}
		else

	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */