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
    Stack<NestedInteger> st;
    //In the constructor, we push all the nestedList into the stack from back to front
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        for(int i= nestedList.size()-1; i>=0 ; i--)
            st.push(nestedList.get(i));
    }

    @Override
    public Integer next() {
        return st.pop().getInteger();
    }
    /*so when I pop the stack, it returns the very first element. 
    Second, in the hasNext() function, I would peek the first element in stack currently,
    if it is an Integer, I will return true and pop the element. 
    If it is a list, I will further flatten it. 
    This is iterative version of flatting the nested list. 
    I would be iterating from the back to front of the list.*/
    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            NestedInteger nInteger = st.peek();
            // top element of the stack is integer
            if(nInteger.isInteger()) return true;
            else{
                st.pop();
                for(int i= nInteger.getList().size()-1; i>=0 ; i--)
                    st.push(nInteger.getList().get(i));
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
