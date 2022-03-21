// Time Complexity : hasNext()/next() -  O(L/N) L is the total no. of lists in nested list, N total no. of integers
// Space Complexity : O(D) D is the deepest integer
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
//Create a stack of Iterator of NestedList
//Add the Iterator to the stack
//In hasNext function check if stack is Empty or not
//Now push the next iterator to stack if its list and if its integer store it.
//Return true if we find integer or we keep it running 
//If stack is empty this will return false
//In next function we will return the integer which we had stored.
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
    int nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            Iterator<NestedInteger> itr = st.peek();
            if(!itr.hasNext()){
                st.pop();
            }
            else{
                NestedInteger ele = itr.next();
                if(ele.isInteger()){
                    nextElement = ele.getInteger();
                    return true;
                }
                else{
                    st.push(ele.getList().iterator());
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