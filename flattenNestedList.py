# Approach: Use basic recursion approach to flatten the lists
# Not the best way -- it does not actually make use of the iterator concept
# Time : Written in line
# Space - O(N + L)
class NestedIterator:

    def __init__(self, nestedList: [NestedInteger]):
        self.queue = collections.deque()
        self.flattenLists(nestedList)
        
    
    # O(1) Time
    def next(self) -> int:
        #check whether there is a next element
        return self.queue.popleft()
        
    # O(1) Time
    def hasNext(self) -> bool:
        # used to check if the queue is empty or not
        return len(self.queue) > 0

    # O(N + L) since we call this function on lists within nested list times, L being the number of the lists and N number of elements in list
    def flattenLists(self, nestedList):
        
        for element in nestedList:
            if element.isInteger():
                self.queue.append(element.getInteger())

            else:
                self.flattenLists(element.getList())

        return


# Approach - Using an iterator 
# Time - O( L / N) where L the number of lists and N is thr number of integers in the list
class NestedIterator:

    def __init__(self, nestedList: [NestedInteger]):
        self.st = []
        self.st.append(iter(nestedList))

    def next(self) -> int:
        return self.nextElement.getInteger()

    def hasNext(self) -> bool:
        while self.st:
            try:
                self.nextElement = next(self.st[-1])
            except:
                self.st.pop()
                continue

            if self.nextElement.isInteger():
                return True
            else:
                self.st.append(iter(self.nextElement.getList()))
        return False
        
         
     