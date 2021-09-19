class NestedIterator:

    def __init__(self, nestedList: [NestedInteger]):

        #   initialize a stack to maintain all iterators of nestedLists in order, also a cursor

        self.stack = []

        if (nestedList != None):                    #   push the main iterator to the stack

            self.stack.append(iter(nestedList))

        self.cursor = None

    def next(self) -> int:

        #   just extract the int inside the cursor and make cursor null

        value = self.cursor

        self.cursor = None

        return value

    def hasNext(self) -> bool:

        #   iterate until the stack is empty and cursor doesn't contain any int

        while (len(self.stack) > 0 and self.cursor == None):

            iterator = self.stack[-1]               #   peek the stack's top iterator

            currentNI = next(iterator, None)        #   replacement for hasNext() and store it in a variable, otherwise might fall

                                                    #   into a trap of calling next() twice which skips one element forward.

            if (currentNI == None):                #   if iter doesn't have next element => pop that iterator

                self.stack.pop()

                continue

            nestedInteger = currentNI              #   else reinitialize to current object (nestedInteger)

            if (nestedInteger.isInteger()):        #    if integer => move cursor to this int and return true

                self.cursor = nestedInteger.getInteger()

                return True

            else:                                  #   else push the iterator of list of nestedInteger

                self.stack.append(iter(nestedInteger.getList()))

        return False 


TC : O(n)
SC : O(1)