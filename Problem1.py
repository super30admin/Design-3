# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger(object):
#    def isInteger(self):
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        :rtype bool
#        """
#
#    def getInteger(self):
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        :rtype int
#        """
#
#    def getList(self):
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        :rtype List[NestedInteger]
#        """
from collections import deque
class NestedIterator(object):
	# deque is a doubly linked list 
	# Inserting and deleting from both the ends is O(1) operation
	# Time Complexity : O(n) To create the queue initially
	# Space Complexity : O(n) for the queue to store all the elements
	def __init__(self, nestedList):
		"""
		Initialize your data structure here.
		:type nestedList: List[NestedInteger]
		"""
		self.queue = deque()
		self.fillQueue(nestedList)
		self.nestedList = nestedList
	
	def fillQueue(self, nestList):
		for i in nestList:
			if i.isInteger():
				self.queue.append(i.getInteger())
			else:
				self.fillQueue(i.getList())

	def next(self):
		"""
		:rtype: int
		"""
		return self.queue.popleft()

	def hasNext(self):
		"""
		:rtype: bool
		"""
		return len(self.queue) > 0

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger(object):
#    def isInteger(self):
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        :rtype bool
#        """
#
#    def getInteger(self):
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        :rtype int
#        """
#
#    def getList(self):
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        :rtype List[NestedInteger]
#        """

class NestedIterator(object):
	# Using a List
	# To remove a element from the beginning of the list its takes O(n) time
	# Time Complexity : O(n) To create the queue initially
	# Space Complexity : O(n) for the queue to store all the elements
	def __init__(self, nestedList):
		"""
		Initialize your data structure here.
		:type nestedList: List[NestedInteger]
		"""
		self.queue = []
		self.fillQueue(nestedList)
	
	def fillQueue(self, nestedList):
		for i in nestedList:
			if i.isInteger():
				self.queue.append(i.getInteger())
			else:
				self.fillQueue(i.getList())

	def next(self):
		"""
		:rtype: int
		"""
		return self.queue.pop(0)

	def hasNext(self):
		"""
		:rtype: bool
		"""
		return len(self.queue) > 0

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())