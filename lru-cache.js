// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Doubly Linked List
function Node(key = null, val = null) {
    this.key = key;
    this.val = val;
    this.next = null;
    this.prev = null;
}
function removeNode(node) {
    node.next.prev = node.prev;
    node.prev.next = node.next;
}
function addToHead(node, head) {
    node.next = head.next;
    node.prev = head;
    head.next = node;
    node.next.prev = node;
}
/**
 * @param {number} capacity
 */
var LRUCache = function(capacity) {
    this.capacity = capacity;
    this.map = new Map();
    this.head = new Node(-1, -1);
    this.tail = new Node(-1, -1);
    this.head.next = this.tail;
    this.tail.prev = this.head;
};

/** 
 * @param {number} key
 * @return {number}
 */
LRUCache.prototype.get = function(key) {
    if (!this.map.has(key)) return -1;
    
    let node = this.map.get(key);
    removeNode(node);
    addToHead(node, this.head);
    return node.val;
};

/** 
 * @param {number} key 
 * @param {number} value
 * @return {void}
 */
LRUCache.prototype.put = function(key, value) {
    // When key is already in my cache
    if (this.map.has(key)) {
        let node = this.map.get(key);
        node.val = value;
        removeNode(node);
        addToHead(node, this.head);
    } else {
        if (this.map.size == this.capacity) {
            let tailPrev = this.tail.prev;
            removeNode(tailPrev);
            this.map.delete(tailPrev.key);
        }
        let newNode = new Node(key, value);
        this.map.set(key, newNode);
        addToHead(newNode, this.head);
    }
};

/** 
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
