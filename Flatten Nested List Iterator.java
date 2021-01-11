/*
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        self.element = NestedInteger()
        self.it = iter(nestedList)
        self.stack.append(self.it)
    
    def next(self) -> int:
        return self.element.getInteger()
    
    def hasNext(self) -> bool:
        while len(self.stack) > 0:
            self.element = next(self.stack[-1], None)
            
            if self.element == None:
                self.stack.pop()

            elif self.element.isInteger():
                return True

            else:
                self.stack.append(iter(self.element.getList()))
                
        return False
*/

// Time Complexity : O(m+n) where n is numbers in the list and m is number of nested lists
// Space Complexity : O(m) where m is number of nested lists
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach: I made the object iterable and pushed the item to stack is it is
// a list and returned the number otherwise a number


public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger element;

    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return element.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!s.isEmpty()){
            if (!s.peek().hasNext()){
                s.pop();
            }
            else if ((element = s.peek().next()).isInteger()){
                return true;
            }
            else {
                s.push(element.getList().iterator());
            }
        }
        return false;
    }
}
