// Time Complexity = O(1) for get,put
// Space Complexity = O(capacity)
// Did this code successfully run on Leetcode : yes

class LRUCache {
    class CacheNode {
        int cacheKey, cacheValue;
        CacheNode previous, next;

        public CacheNode(int cacheKey, int cacheValue) {
            this.cacheKey = cacheKey;
            this.cacheValue = cacheValue;
        }

        CacheNode() {
            this(0, 0);
        }
    }

    CacheNode front, rear;
    Map<Integer, CacheNode> cacheMap;
    int maxCapacity, currentCount;

    public LRUCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCount = 0;
        cacheMap = new HashMap<>();
        front = new CacheNode();
        rear = new CacheNode();
        front.next = rear;
        rear.previous = front;
    }

    // Retrieve a value from the cache
    public int get(int cacheKey) {                              // O(1) time complexity
        CacheNode retrievedNode = cacheMap.get(cacheKey);
        if (retrievedNode == null) {
            return -1; // Key not found in the cache
        } else {
            rearrange(retrievedNode); // Move the retrieved node to the front
            return retrievedNode.cacheValue;
        }
    }

    // Add or update a value in the cache
    public void put(int cacheKey, int cacheValue) {             // O(1) time complexity
        CacheNode existingNode = cacheMap.get(cacheKey);
        if (existingNode == null) {
            CacheNode newNode = new CacheNode(cacheKey, cacheValue);
            cacheMap.put(cacheKey, newNode);
            addAtFront(newNode); // Add the new node to the front of the cache
            ++currentCount;

            // Check if the cache has exceeded its capacity
            if (currentCount > maxCapacity) {
                CacheNode toDelete = rear.previous;
                removeFromEnd(toDelete); // Remove the least recently used node
                cacheMap.remove(toDelete.cacheKey);
                --currentCount;
            }
        } else {
            existingNode.cacheValue = cacheValue;
            rearrange(existingNode); // Move the existing node to the front
        }
    }

    // Move a node to the front of the cache (recently used)
    public void rearrange(CacheNode nodeToRearrange) {
        removeFromEnd(nodeToRearrange);
        addAtFront(nodeToRearrange);
    }

    // Add a node to the front of the cache
    public void addAtFront(CacheNode newNode) {
        CacheNode succeedingNode = front.next;
        front.next = newNode;
        newNode.previous = front;
        newNode.next = succeedingNode;
        succeedingNode.previous = newNode;
    }

    // Remove a node from the end of the cache (least recently used)
    public void removeFromEnd(CacheNode nodeToRemove) {
        CacheNode priorNode = nodeToRemove.previous;
        CacheNode followingNode = nodeToRemove.next;

        priorNode.next = followingNode;
        followingNode.previous = priorNode;
    }
}

// Usage:
// LRUCache obj = new LRUCache(maxCapacity);
// int result = obj.get(cacheKey);
// obj.put(cacheKey, cacheValue);
