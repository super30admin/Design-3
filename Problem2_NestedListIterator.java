// Time Complexity : o(1)
// Space Complexity : o(n+l) height and recursive stack space
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach 

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
  NestedInteger nextElement;
    public NestedIterator(List<NestedInteger> nestedList) {
       stack = new Stack();
  stack.push(nestedList.iterator()); 
    }

    @Override
    public Integer next() {
       return nextElement.getInteger(); 
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
              //iterator out of bounds
              if(!stack.peek().hasNext()) {
                  stack.pop();
              } else if((nextElement = stack.peek().next()).isInteger()) {
              //is Integer
                  return true;
              } else {
              //is List
                  stack.push(nextElement.getList().iterator());
              }
          }
          return false;  
    }
}
