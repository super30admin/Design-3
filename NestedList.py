"""
The approach here is use the fill queue function to flaten the list and make it as a one list, here
getInteger gets the current index value if it's a integer so we call it if isInteger is true, else 
getList is used to get another nested list, so if the current index is not a integer we call the fill
queue function on the get integer method of the list, in this way again we can interate over the nested
list, the next function is used to get the first element in the queue and remove it until the elements are
present, the hasNext will return false we have no elements in the list.

leetcode - Running
Time complexity - O(N)
Space complexity - O(N) - since we using the built in stack for recursion.
"""

class NestedIterator(object):
    def __init__(self, nestedList):
        self.l = self.fillQueue(nestedList)
        
    def fillQueue(self, nestedList):
        temp = []
        for nest in nestedList:
            if nest.isInteger():
                temp.append(nest.getInteger())
            else:
                temp.extend(self.fillQueue(nest.getList()))
        return temp

    def next(self):
        return self.l.pop(0)

    def hasNext(self):
        return len(self.l) != 0