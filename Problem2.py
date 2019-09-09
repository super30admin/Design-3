class LRUCache(object):

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