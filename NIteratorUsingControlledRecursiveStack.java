// Time Complexity : O(1) for next and O(N) for hasNext where N is the depth/height/Number of NestedLists We have
// Space Complexity : O(N) where N is the depth/height/number of Levels of max Nesting in the stack i.e.. [2,[3,[4]]], 3 in this case
// Did this code successfully run on Leetcode : Yes

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
public class NIteratorUsingControlledRecursiveStack implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextElement;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        //Introduce a native iterator over nested list and push to stack
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    //using controlled recursive stack technique
    public boolean hasNext() {
        //check if stack is not empty
        while(!stack.isEmpty()) {
            //if next element is not there, pop that element from the stack as it is processed
            if(!stack.peek().hasNext()) {
                stack.pop();
            }
            //stack.peek().next() refers to first element of the top nestedInteger in the stack
            // assign next element to a global variable so that it can be used to return the value of the integer
            else if ((nextElement = stack.peek().next()).isInteger()) {
                return true;
            }
            //if it's not an integer, push the nested list to the stack
            else {
                stack.push(nextElement.getList().iterator());
            }
        }
        //if there are no elements in the stack
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */