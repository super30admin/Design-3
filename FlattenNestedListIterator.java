// Time Complexity :O(1)
// Space Complexity : O(h) no of nested lists
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this : 


// Your code here along with comments explaining your approach

//1. take a stack of Iterator of NestedInteger and a variable of NestedInteger type
//2. push entire nestedList in stack. Note : a local iterator would be  running on each element of stack until iterator return hasnext == false
// 3. now inside the hasNext method // for every top element we will check if iterator is at end that mean we have checked entire list pop it from stack
else check if top element.next is isInteger or not. if yes then return true
else ( it means next element is a list) so push it in stack with a iterator on it.
// keep doing it until stack is not empty.


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
        st = new Stack<>();
        // push entire list in stack
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
       return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty())
        {
            // if list inside stack doesnt has anymore elements
            if(!st.peek().hasNext())
            {  // if iterator has reached at end it means we have covred all element at thih level 
                st.pop();
            }
            else if ((nextElement = st.peek().next()).isInteger() ) // fetch the next element and check if it integer
            {
                return true;
            }
            else // since element is a list we will push it to stack with iterator 
            {
                st.push(nextElement.getList().iterator());
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