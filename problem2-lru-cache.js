//https://leetcode.com/problems/lru-cache/
//// Time Complexity : put, get O(1)
//// Space Complexity : O(capacity) we create a linkedlist and a hashmap of max capacity
//// Did this code successfully run on Leetcode : yes
//// Any problem you faced while coding this :

// store both key and value into listnode not just value
// so you can look up later what map entry to delete

//// Your code here along with comments explaining your approach

// have a doubly linked list with a head and tail
// have a hashmap
// adding to the cache adds an entry to the hashmap and a node in the linkedlist
// hashmap maps directly to the linkedlist node
// updating recently used is just a matter of updating head / tail / current node pointers
var LRUCache, List, ListNode, ml, ml2;

ListNode = function(key = -1, value = -1, prev = null, next = null) {
  return Object.assign(Object.create(ListNode.prototype), {
    prev: prev,
    next: next,
    key: key,
    value: value
  });
};

List = function() {
  var head, tail;
  head = ListNode();
  tail = ListNode();
  head.next = tail;
  tail.prev = head;
  return Object.assign(Object.create(List.prototype), {
    head: head,
    tail: tail
  });
};

List.prototype.remove = function(node) {
  var after, before;
  before = node.prev;
  after = node.next;
  before.next = after;
  return after.prev = before;
};

List.prototype.insertAtTail = function(node) {
  var after, before;
  after = this.tail;
  before = this.tail.prev;
  after.prev = node;
  before.next = node;
  node.next = after;
  return node.prev = before;
};

List.prototype.removeFromHead = function() {
  var after, before;
  before = this.head;
  after = this.head.next.next;
  before.next = after;
  return after.prev = before;
};

List.prototype.moveToHead = function(node) {
  this.remove(node);
  return this.insertAtTail(node);
};

LRUCache = function(capacity) {
  return Object.assign(Object.create(LRUCache.prototype), {
    capacity: capacity,
    size: 0,
    map: new Map(),
    list: List()
  });
};

LRUCache.prototype.get = function(key) {
  var node;
  if (!this.map.has(key)) {
    return -1;
  }
  node = this.map.get(key);
  this.list.remove(node);
  this.list.insertAtTail(node);
  return node.value;
};

LRUCache.prototype.put = function(key, value) {
  var node;
  if (this.map.has(key)) {
    node = this.map.get(key);
    node.value = value;
    this.list.remove(node);
    return this.list.insertAtTail(node);
  } else {
    this.size += 1;
    if (this.size > this.capacity) {
      this.map.delete(this.list.head.next.key);
      this.list.removeFromHead();
      this.size -= 1;
    }
    node = ListNode(key, value);
    this.map.set(key, node);
    return this.list.insertAtTail(node);
  }
};

///**
// * Your LRUCache object will be instantiated and called as such:
// * var obj = new LRUCache(capacity)
// * var param_1 = obj.get(key)
// * obj.put(key,value)
// */

//Input
//["LRUCache", "put", "put",   "get", "put", "get", "put", " get", "get", "get"]
//   [[2],     [1, 1], [2, 2], [1],   [3, 3], [2],   [4, 4], [1],   [3],   [4]]
//Output
//   [null,      null,   null,   1,    null,   -1,     null,  -1,    3,     4]
ml = LRUCache(2);

ml.put(1, 1);

ml.put(2, 2);

ml.get(1) === 1;

ml.put(3, 3);

ml.get(2) === -1;

ml.put(4, 4);

ml.get(1) === -1;

ml.get(3) === 3;

ml.get(4) === 4;

//["LRUCache","put",   "get",  "put",   "get",  "get"]
//    [[1],    [2,1],   [2],   [3,2],    [2],    [3]]
//Output
//    [null,    null,    1,      null,    1,       2]
//Expected
//    [null,     null,    1,     null,    -1,      2]
ml2 = LRUCache(1);

ml2.put(2, 1);

ml2.get(2) === 1;

ml2.put(3, 2);

ml2.get(2) === -1;

ml2.get(3) === 2;

//# sourceMappingURL=problem2-lru-cache.js.map
