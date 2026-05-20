# LRU Cache
#
# eliminate least recently used
# 2 operations: get, put
#
# get: if key present → return value (and mark it most recent), else return -1
# put: 1) key exists → update value, move to most recent side
#      2) fresh key  → if capacity full, remove LRU item, then insert and mark most recent
#
# to maintain order of recency → use a linked list of nodes (key, value)
#
# hurdle 1: finding a key is O(n) linear search through LL
#   fix: use a dict → O(1) to check if key exists
#
# hurdle 2: dict maps key → value, but we still need to physically find the node in LL to move it
#   fix: map key → node reference (address of node), so we jump to it in O(1)
#
# hurdle 3: once we have node ref, removing it from a singly LL is still O(n)
#   because to remove a node we need its previous node, and we have to traverse to find it
#   fix: use doubly LL → each node has prev and next, so removal is O(1)
#     removal: node.prev.next = node.next  ,  node.next.prev = node.prev
#     (no need to clear node's own prev/next pointers but we do it for cleanliness)
#
# moving to most recent (add to head):
#   1) node.next = head.next
#   2) node.prev = head
#   3) head.next.prev = node   ← old first node points back to node
#   4) head.next = node
#
# use dummy head and tail sentinels so every real node always has valid prev/next
# layout: HEAD <-> [MRU ... LRU] <-> TAIL
