class MyHashSet {
    
    int bucket1;
    int bucket2;
    boolean [][] storage;
    /** Initialize your data structure here. */
    public MyHashSet() {
       bucket1 = 1000; 
       bucket2 = 1001;
       storage = new boolean[bucket1][];
        
    }
   
    public int hashKey(int key){
        return Integer.hashCode(key) % bucket1;
    }
    
    public int hashKey1(int key){
        return Integer.hashCode(key) / bucket2;
    }
    
    public void add(int key) {
           int index= hashKey(key);
           int index2 = hashKey1(key);
        if(storage[index] == null){
            storage[index] = new boolean[bucket2];
        }
        storage[index][index2] = true;
        
    }
    
    public void remove(int key) {
        int index= hashKey(key);
        int index2 = hashKey1(key); 
         if(storage[index] == null){
            return;  
         }
        storage[index][index2] = false;
        
        
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index= hashKey(key);
        int index2 = hashKey1(key); 
         if(storage[index] == null){
            return false;   
         }
        
        return storage[index][index2];
        
    }
}
​
/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
