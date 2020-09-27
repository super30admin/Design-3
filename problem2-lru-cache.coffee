#// Time Complexity : put, get O(1)
#// Space Complexity : O(capacity) we create a linkedlist and a hashmap of max capacity
#// Did this code successfully run on Leetcode : yes
#// Any problem you faced while coding this :
#
# store both key and value into listnode not just value
# so you can look up later what map entry to delete
#
#// Your code here along with comments explaining your approach
#
# have a doubly linked list with a head and tail
# have a hashmap
# adding to the cache adds an entry to the hashmap and a node in the linkedlist
# hashmap maps directly to the linkedlist node
# updating recently used is just a matter of updating head / tail / current node pointers

ListNode = (key = -1, value = -1, prev = null, next = null) -> Object.assign(
  Object.create(ListNode::),
    prev: prev
    next: next
    key: key
    value: value
  )

List = () ->
  head = ListNode()
  tail = ListNode()
  head.next = tail
  tail.prev = head

  Object.assign(
    Object.create(List::),
    head: head
    tail: tail
  )

List::remove = (node) ->
  before = node.prev
  after = node.next

  before.next = after
  after.prev = before

List::insertAtTail = (node) ->
  after = @tail
  before = @tail.prev
  after.prev = node
  before.next = node
  node.next = after
  node.prev = before

List::removeFromHead = () ->
  before = @head
  after = @head.next.next

  before.next = after
  after.prev = before

List::moveToHead = (node) ->
  @remove(node)
  @insertAtTail(node)

LRUCache = (capacity) -> Object.assign(
  Object.create(LRUCache::),
    capacity: capacity
    size: 0
    map: new Map()
    list: List()
  )

LRUCache::get = (key) ->
  return -1 unless @map.has(key)
  node = @map.get(key)
  @list.remove(node)
  @list.insertAtTail(node)
  node.value

LRUCache::put = (key, value) ->
  if @map.has(key)
    node = @map.get(key)
    node.value = value
    @list.remove(node)
    @list.insertAtTail(node)
  else
    @size += 1
    if @size > @capacity
      @map.delete(@list.head.next.key)
      @list.removeFromHead()
      @size -= 1
    node = ListNode(key, value)
    @map.set(key, node)
    @list.insertAtTail(node)


#/**
# * Your LRUCache object will be instantiated and called as such:
# * var obj = new LRUCache(capacity)
# * var param_1 = obj.get(key)
# * obj.put(key,value)
# */

#Input
#["LRUCache", "put", "put",   "get", "put", "get", "put", " get", "get", "get"]
#   [[2],     [1, 1], [2, 2], [1],   [3, 3], [2],   [4, 4], [1],   [3],   [4]]
#Output
#   [null,      null,   null,   1,    null,   -1,     null,  -1,    3,     4]

ml = LRUCache(2)
ml.put(1,1)
ml.put(2,2)
ml.get(1) is 1
ml.put(3,3)
ml.get(2) is -1
ml.put(4,4)
ml.get(1) is -1
ml.get(3) is 3
ml.get(4) is 4

#["LRUCache","put",   "get",  "put",   "get",  "get"]
#    [[1],    [2,1],   [2],   [3,2],    [2],    [3]]
#Output
#    [null,    null,    1,      null,    1,       2]
#Expected
#    [null,     null,    1,     null,    -1,      2]
ml2 = LRUCache(1)
ml2.put(2,1)
ml2.get(2) is 1
ml2.put(3,2)
ml2.get(2) is -1
ml2.get(3) is 2
