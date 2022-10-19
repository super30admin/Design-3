#flatten nested list iterator
#time complexity: O(N+L) for the recursive function and O(1) for next, hasNext functions
#space: O(n+d) , n is for integer list and d for recursive stack

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        def flatten(nested_list):
            #iterate through the list and check if each element is integer
            for nested_int in nested_list:
                if nested_int.isInteger():
                    #if integer, append to the integer array
                    self._integers.append(nested_int.getInteger())
                else:
                    #if it is a list, recursively call the function on the lists
                    flatten(nested_int.getList())
        self._integers=[]
        self._position=-1
        flatten(nestedList)
        
    def next(self) -> int:
        #to get the next, first increment the position and get the int for the current pos from integer array
        self._position+=1
        return self._integers[self._position]
    
    def hasNext(self) -> bool:
        #return true if the postion is less than size of integer array
         return self._position+1<len(self._integers)