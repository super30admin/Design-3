// Time Complexity : next() -> O(1) hasNext() -> O(log(k))  where, k - depth of the array
// Space Complexity : O(log(k)) -> k - depth of the array
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Thinking the approach

// Your code here along with comments explaining your approach

public class NestedIterator implements Iterator<Integer> {
    
    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextEl;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext()){
                stack.pop();
            } else if((nextEl = stack.peek().next()).isInteger()){
                return true;
            } else {
                stack.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}