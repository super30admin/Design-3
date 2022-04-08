// Time Complexity : O(1) for next() and O(D) for HasNext() where D = depth of NestedList
// Space Complexity : O(N + D) - Total no. of elements where D = depth of NestedList
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextElement;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push( nestedList.iterator() );  //Initializing stack  by pushing the given nestedList as iterator
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while( !stack.isEmpty() ){ //Until stack is not empty
            if(!stack.peek().hasNext()){    //Pop the iterator from stack, if that iterator give false on hasNext() 
                stack.pop();
            }else if( (nextElement = stack.peek().next()).isInteger() ){ // Check if the top having NestedInteger is INteger or not
                return true;
            }else{
                stack.push(nextElement.getList().iterator()); // If it is list, then make the NestedList to iterator and push into the stack 
            }
        }
        return false;
    }
}