// Time Complexity : Amortized O(1).
// Space Complexity : O(n) - n is the size of the stack.
// Did this code successfully run on Leetcode : Yes
// Approach : We use a stack and store the iterators to keep track of the nested lists. . For hasNext(), while we are not at the end of the iterator,
// we capture the next element. If this next element is integer, we directly return, ekse we add to the stack and continue the process.
// For next(), we directly return the next integer by using the getInteger() method of the interface given.

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
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());//push to stack
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){//end of the iterator
                st.pop();
                nextElement = null;
            }else{
                nextElement = st.peek().next();
                if(nextElement.isInteger()){//if next element is integer
                    return true;
                }else{
                    st.push(nextElement.getList().iterator()); //if next element is not an integer but a List
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