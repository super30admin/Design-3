// Time Complexity : O(number of opening and closing brackets)
// Space Complexity :O(number of opening and closing brackets)
// Did this code successfully run on Leetcode : Yes
// Three line explanation of solution in plain english
// Take an Iterator stack, store the currnt list to stack by converting it to iterator. Create a next variable to retuirn the top value. in HashNext function, if the st.peek.next is null, pop the value from stack, else if the ( next = st.peek().next()).isInteger() return true, else return st.push(next.toList().iterator()).
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

    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextIn;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextIn.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((nextIn = st.peek().next()).isInteger()){
                return true;
            }else{
                st.push(nextIn.getList().iterator());
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