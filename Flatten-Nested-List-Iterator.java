// Time Complexity : O(n) for recursive calls
// Space Complexity : O(n) as we are using a Stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.*;


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}


public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        
        // adding elements from end of list to stack
        for (int i = nestedList.size()-1; i >= 0; i--) {
            stack.add(nestedList.get(i));
        }
        explore();
    }
    
    //prepare stack for next() call
    private void explore() {
        
        // looping until stack empty or top of stack is integer
        while(!stack.isEmpty() && !stack.peek().isInteger()) {
            
            NestedInteger n = stack.pop();
            List<NestedInteger> nestedList = n.getList();
            for (int i = nestedList.size()-1; i >= 0; i--) {
                stack.add(nestedList.get(i));
            }
        }
    }

    @Override
    public Integer next() {
        NestedInteger n = stack.pop();
        explore();
        return n.getInteger();
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }
        return true;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */