/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
   NestedInteger nextEl;
Stack<Iterator<NestedInteger>> stack;    //collection of iterator of type nested integer
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());  
    }

    @Override
    public Integer next() { // O(1) time and space
        return nextEl.getInteger();
    }
  
     public boolean hasNext() { // O(n) time and space (mentioned above at the top)
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext()){ // doesnt have next element on stack then pop out of stack
                stack.pop();
            }
            
            else {
                   nextEl = stack.peek().next();
                if(nextEl.isInteger()){ return true;}
                else{
                stack.push(nextEl.getList().iterator()); //getList coming from interface //NestedIterator and iterator function coming from parent Iterator<Integer>
                    }
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

/*
Timecomplexity for next function is O(1),O(n) for accessing the stack space for hasNext function
Space Complexity O(1) for next function, hasNext is recursive stack space n O(n)
*/