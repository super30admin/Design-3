class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true); //calling constructor of LinkedHashMap
        this.capacity = capacity;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
    public void put(int key, int value) {
        super.put(key, value);
    }
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
}
    