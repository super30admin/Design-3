class LRUCache(object):
	# Implemented using a dictionary(hashmap) and a list
	# In the list, just storing the keys
	# In the dictionary, storing the key, value pair
	# Time Complexity : O(n) because we have to traverse through the entire list to delete a node in between
	def __init__(self, capacity):
		"""
		:type capacity: int
		"""
		self.dict = dict()
		self.capacity = capacity
		self.listkeys = []

	def get(self, key):
		"""
		:type key: int
		:rtype: int
		"""
		if key not in self.dict:
			return -1
		self.listkeys.remove(key)
		self.listkeys.append(key)
		return self.dict[key]

	def put(self, key, value):
		"""
		:type key: int
		:type value: int
		:rtype: None
		"""
		if key in self.dict:
			self.listkeys.remove(key)
		elif len(self.dict) == self.capacity:
			v = self.listkeys.pop(0)
			self.dict.pop(v)
		self.dict[key] = value
		self.listkeys.append(key)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

class Node:
	def __init__(self, key, value):
		self.key = key
		self.value = value
		self.next = None
		self.prev = None

class LRUCache(object):
	# Imlpemented using a doubly linked list and a dictionary(hashmap)
	# Time Complexity : O(1) because in doubly linked list
	# In doubly linked list, storing the Nodes
	# In the dictionary, we are storing the key, Node pair
	def __init__(self, capacity):
		"""
		:type capacity: int
		"""
		self.map = dict()
		self.capacity = capacity
		self.head = Node(0,0)
		self.tail = Node(0,0)
		self.head.next = self.tail
		self.tail.prev = self.head
		self.count = 0

	def get(self, key):
		"""
		:type key: int
		:rtype: int
		"""
		if key not in self.map:
			return -1
		self.removeNode(self.map[key])
		self.AddToHead(self.map[key])
		return self.map[key].value
	
	def removeNode(self, node):
		node.prev.next = node.next
		node.next.prev = node.prev
	
	def AddToHead(self, node):
		node.next = self.head.next
		node.prev = self.head
		self.head.next = node
		node.next.prev = node

	def put(self, key, value):
		"""
		:type key: int
		:type value: int
		:rtype: None
		"""
		if key in self.map:
			tempNode = self.map[key]
			tempNode.value = value
			self.removeNode(tempNode)
			self.AddToHead(tempNode)
		else:
			if self.count == self.capacity:
				tailprev = self.tail.prev
				self.removeNode(tailprev)
				self.map.pop(tailprev.key)
				self.count -= 1
			temp = Node(key, value)
			self.map[key] = temp
			self.AddToHead(temp)
			self.count += 1


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)