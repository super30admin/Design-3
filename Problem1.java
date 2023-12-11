// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :no


public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger next;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return next.getInteger();
    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()){ // the order of below if statements is important
            // when there are no elements left on the iterator on top
            if(!stack.peek().hasNext()){
                stack.pop(); // keep popping 
            }
            else if((next = stack.peek().next()).isInteger()){  // we could not have called next until we know if it has next
                // the next pointer is already pointing to the integer , so when next() method is called , we will return its value
                return true;
            }
            else{ // this means that next item is a list, which should be on top of the stack
            stack.push(next.getList().iterator()); // this will keep on repeated for nested list
            }
        }

        return false;
    }
}
