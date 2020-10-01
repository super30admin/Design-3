// TC: O(1) since we are checking if the key is already or not, we are not traversing the complete hashMap
// SC : O(N) we are hashmap to store N elements passed
// LInekdHashMap stores the order in which the elements are passed, So, I am trying to create a linkedHashMap and to get the value,
// i am returning the value that exists for the key, Once I am using that key, value pair, I need to make sure it is recently used and 
// I need to update my hashmap accordingly sice LinkedHashamp stores the order, so i will be removing the previous key, value pair and add the same
// key, valule pair again which gives us the correct order. Similarly for put as well, If the map is full, we remove the starting/lease recently used element from the map

import java.util.*;

public class LRUCache {

	LinkedHashMap<Integer, Integer> map;
	@SuppressWarnings("unused")
	private int capacity;
	public LRUCache(int capacity) {
		
		this.capacity = capacity;
		map = new LinkedHashMap<>();
	}
	
	public int get(int key) {
		
		if(map.containsKey(key)) {
			int val = map.remove(key);
			map.put(key, val);
			return val;
		}
		return -1;
	}
	
	public void put(int key, int value) {
		
		if(map.containsKey(key)) {
			map.remove(key);
		}else if(map.size()+1 > this.capacity) {
			map.remove(map.keySet().iterator().next());
		}
		map.put(key,  value);
	}
	
}
