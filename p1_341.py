# 341. Flatten Nested List Iterator

# TC :
    # __init__: O(N), where N is the total number of integers in the nested list
    # _flatten: O(N), as we visit each element exactly once
    # next: O(1), constant time operation
    # hasNext: O(1), constant time operation
# SC :
    # O(N) for storing all integers in the flattened queue
    # O(D) for the recursion stack during flattening, where D is the maximum nesting depth
    # Overall: O(N)

# Did this code successfully run on Leetcode : Yes

# Approach :
    # We completely flatten the nested list during initialization
    # We use a queue to store all integers in the order they appear
    # Simple to implement and offers O(1) operations after initialization



class NestedIterator:
    def __init__(self, nestedList):
        # Flatten the nested list using DFS and store in a queue
        self.queue = []
        self._flatten(nestedList)
        
        # Initialize the position index
        self.position = 0
    
    def _flatten(self, nested_list):
        for item in nested_list:
            if item.isInteger():
                # If item is an integer, add it to our queue
                self.queue.append(item.getInteger())
            else:
                # If item is a list, recursively flatten it
                self._flatten(item.getList())
    
    def next(self) -> int:
        # Return the next element and increment position
        result = self.queue[self.position]
        self.position += 1
        return result
    
    def hasNext(self) -> bool:
        # Check if we still have elements to process
        return self.position < len(self.queue)