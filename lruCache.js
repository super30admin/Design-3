// Time Complexity : Get: O(1), Put: O(1)
// Space Complexity : O(1) since we are only creating a cache which is the map in our case. Hence, no extra space is used.
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class LruCache {
  constructor(capacity) {
    this.map = new Map();
    this.capacity = capacity;
  }

  put(key, value) {
    // check capacity before add
    if (this.capacity === this.map.size && !this.map.has(key)) {
      // the least recently used keyVal will be at the beginning(left).
      for (let keyVal of this.map) {
        this.map.delete(keyVal[0]);
        break;
      }
      return this.map.set(key, value);
    }
    if (this.map.has(key)) {
      // delete key if it exists so that the map's order can change.
      this.map.delete(key);
    }
    // add key value
    return this.map.set(key, value);
  }

  get(key) {
    if (this.map.has(key)) {
      const value = this.map.get(key);
      // delete the key val pair
      this.map.delete(key);
      // set it for it to be at the end of the list
      this.map.set(key, value);
      return this.map.get(key);
    }
    return -1;
  }
}

const lruCache = new LruCache(2);
console.log("lruCache:", lruCache);
lruCache.put(1, 1);
lruCache.put(2, 2);
lruCache.get(1);
lruCache.get(3);
lruCache.put(3, 3);
lruCache.get(1);
console.log("lruCache:", lruCache);
