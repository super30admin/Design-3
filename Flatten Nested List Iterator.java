// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

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
    //create a stack to hold  the nested integer
    Stack<Iterator<NestedInteger>> st;
    //creaetd a nextinteger nextEl
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        //intialzie the stack
        st = new Stack<>();
        //push the nestedList into the stack and make it iterator 
        st.push(nestedList.iterator());
        
    }

    @Override
    public Integer next() {
        //just give the nextEl and get the integer
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        //with this function we are going to see if there is a hasNext and also we will set the nextEl to the next available integer
        while(!st.isEmpty()){
            //if the top of the stack has next
            if(!st.peek().hasNext()){
                //remove it
                st.pop();
            }
            //check if the top of stack is an integer or not
            else if((nextEl = st.peek().next()).isInteger()){
                return true;
            }
            //if not nested integer
            else{
                //get the next el make it a list and then iterator
                st.push(nextEl.getList().iterator());
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


