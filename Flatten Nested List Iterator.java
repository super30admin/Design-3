// Time Complexity : O(1)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach
 // We will do Lazy evaluation here. We will use the stack data structure of Iterators of Nested Integers. We will always use the hasNext function to check if there is an element in the stack before checking the next function. 
 //An iterator on top of the stack should always return an integer. So we first call next of the given nested integer, and store the value that it returns, since we may lose reference to it as the next pointer will go to the next integer/nested integer. 
 //Then, if this value is an integer, we return true and the integer value in the next function of the NestedInteger. If it gives false, then we push that to the stack. Is the next pointer goes out of bound we will pop the element on top of the stack.

 public class NestedIterator implements Iterator<Integer> {

    NestedInteger nextEl;
    Stack <Iterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st=new Stack<>();
        st.push(nestedList.iterator()); //We will start by pushing the given input
    }

    @Override
    public Integer next() 
    {
        return nextEl.getInteger();
        
    }

    @Override
    public boolean hasNext() 
    {
        while(!st.isEmpty())
        {
            //check if there is a next element
            if(!st.peek().hasNext())
            {
                st.pop();
            }
            else if((nextEl=st.peek().next()).isInteger()) return true;

            else
            {
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