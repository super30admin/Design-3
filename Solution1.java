//Time complexity:
//next() - O(1)
//hasNext() - O(n), where n is the maximum depth of nested lists.
//Space complexity: 
//O(n)

import java.util.*;


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
    Stack<Iterator<NestedInteger>> stack; 
    NestedInteger nextElement; 
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>(); 
        //push the whole nested list
        stack.push(nestedList.iterator()); 
    }

    @Override
    public Integer next() {
        return nextElement.getInteger(); 
    }

    @Override
    public boolean hasNext() {
        
        while(!stack.isEmpty()) {
            //case 1 - iterator is out of bounds
            if(!stack.peek().hasNext()){
                stack.pop();
            }
            //case 2 - if it is an integer
            else if((nextElement = stack.peek().next()).isInteger()) {
                return true;
            }
            //case 3 - if it is a nested integer
            else {
                stack.push(nextElement.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */