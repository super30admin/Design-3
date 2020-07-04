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

//Time Complexity=O(N)
//Space Complexity=O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger Next;// We are making sure that this pointer is always pointing to the integer
    public NestedIterator(List<NestedInteger> nestedList) {
        st=new Stack();
        st.push(nestedList.iterator());//Intially we are adding the entire list.
    }

    @Override 
    public Integer next() {
        return Next.getInteger();
    }
//hasNext() is called before next(), so top of the stack is always pointing to next integer, not the nested list
    @Override
    public boolean hasNext() {
        while(!st.isEmpty())
        {
            if(!st.peek().hasNext()){//Checks if the next element at top of the stack is null then  
                st.pop();            //remove it from the stack
            }
            else if((Next=st.peek().next()).isInteger())
            {
                return true;
                // Moves the pointer to the next element in the List  and checks whether it is integer                    or not if it is the return true.
                
            }
            else
            {
                //If from the above condition if the Next pointer is not integer the it is a list, so we push the next list to the stack.
                st.push(Next.getList().iterator());
            } 
        }
        return false;//When stack is empty it return false
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */