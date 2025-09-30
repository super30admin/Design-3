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
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

 /*
 - We are given a nester list of integers and we are to implement NestedIterator class which will give the flatten list as a result. To do so, we will be using native iterator datastructure and use next and hasNest methods of the native iterator to implement the NestedIterator. Also, here the nestedList is list of NestedInteger. 
 - Remembering the approach from BST iterator, we will be using stack to store Iterators(used to achieve lazy loading) of type NestedInteger. 
 - for HasNext() method - we have three conditions
    1. next is an integer
        - return true as it is an integer
    2. next is a list of NestedInteger
        - add it to the stack as we will have to flatten the list to get integer
    3. next is out of bounds
        - the iterator has processed the list, so pop it from the stack
- for Next() method - we convert the value stored in a varilable of type NestedList to integer and return

TC: O(1) -> amortized O(1) for hasNext
and perfect O(1) for next
SC:O(d) -> depth of the list stored in the recursion stack at any given time 
  */
public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextEl;
    Stack<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        //push intial iterator in the stack
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // use getInteger to convert NestedInteger to Integer
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        //iterate until the stack is empty
        while(!stack.isEmpty()){
            // case 3
            if(!stack.peek().hasNext())
                stack.pop();
            // case 1
            else if((nextEl = stack.peek().next()).isInteger())
                return true;
            // case 2
            else{
                stack.push(nextEl.getList().iterator());
            }
        }
        // there is nothing to return, no next element present
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */