// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : I did not understand why we might need a dummy tail and haed pointer <headDummy> <-> node <-> node <-> node <-> <tailDummy>
//TC: O(1)
//SC: O(1)
var Node = function(data) {
    this.data = data;
    this.next = null;
    this.prev = null;
};

var LRUCache = function(capacity) {
    this.capacity = capacity;
    this.head = new Node([-1, -1]);
    this.tail = new Node([-1, -1]);
    this.head.next = this.tail;
    this.tail.prev = this.head;
    this.hashMap = new Map();
};

LRUCache.prototype.get = function(key) {
    if (!this.hashMap.has(key)) return -1;
    const node = this.hashMap.get(key);
    this.removeNode(node);
    this.moveToFront(node);
    return node.data[1];
};

LRUCache.prototype.put = function(key, value) {
    if (this.hashMap.has(key)) {
        const node = this.hashMap.get(key);
        node.data[1] = value;
        this.removeNode(node);
        this.moveToFront(node);
    } else {
        if (this.hashMap.size >= this.capacity) {
            this._removeLeastRecentlyUsedNode();
        }
        const newNode = new Node([key, value]);
        this.hashMap.set(key, newNode);
        this.moveToFront(newNode);
    }
};

LRUCache.prototype.removeNode = function(node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
};

LRUCache.prototype.moveToFront = function(node) {
    // node.prev = this.head;
    // node.next = this.head.next;
    // this.head.next.prev = node;
    // this.head.next = node;

    node.prev = this.head
    node.next = this.head.next
    this.head.next = node
    node.next.prev = node

};

LRUCache.prototype._removeLeastRecentlyUsedNode = function() {
    const node = this.tail.prev;
    this.removeNode(node);
    this.hashMap.delete(node.data[0]);
};
