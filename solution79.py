#Time Complexity:O(n)
#Space Complexity:O(n)

class NestedIterator:
    global st
    def __init__(self, nestedList: [NestedInteger]):
        self.st=[iter(nestedList)]                          #declaring stack that holds all the elements of the nested list
        self.nextEl=None                                    #next element is declared null
    
    def next(self) -> int:
        return self.nextEl                                  #return the next element
    
    def hasNext(self) -> bool:
        while(len(self.st)):                                    #while the stack is not empty
            try:
                st_top = next(self.st[-1])                      #if the topmost element is integer instack
                if st_top.isInteger():
                    self.nextEl = st_top.getInteger()           #assign next element to be the current top element
                    return True
                else:
                    self.st.append(iter(st_top.getList()))     #append the topmost list elements to the stack
            except StopIteration:
                self.st.pop()                                  #pop out the stack element
                continue
        return False