// Time Complexity : O(1)
// Space Complexity : O(c), capacity of the cache
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


class LRUCache
{
    int capacity;
    Queue<Integer> q=new ArrayDeque<>();
    Map<Integer,Integer> map=new LinkedHashMap<>();
    
    public LRUCache(int capacity) 
    {
        this.capacity = capacity;
    }
    
    public int get(int key)
    {
        if (map.containsKey(key)) {
            q.remove(key);
            q.offer(key);
            return map.get(key);
        } 
        else 
            return -1;
    }
    
    public void put(int key, int value) 
    {
        if(map.containsKey(key)) {
            q.remove(key);
            q.offer(key);
            map.put(key,value);
        }
        else 
        {
            if(q.size() < capacity)
                q.offer(key);
            else {
                map.remove(q.poll());/
                q.offer(key);
            }
            map.put(key,value);
        }
    }
}
