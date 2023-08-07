// Time Complexity : next - O(1)
//hasNext - O(1) amortized
// Space Complexity :next and hasnext will be O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :No


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
    //Stack to keep track of iterators
    Stack<Iterator<NestedInteger>> stk;
    
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new Stack<>();
        stk.push(nestedList.iterator());
    }

    //in next function, just return the integer which is in nextEl. 
    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    //hasNext function will run till we find the next integer. We will store our iterators in stack
    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()){
            Iterator<NestedInteger> it = stk.peek();
            
            if(!it.hasNext()) stk.pop();
            else{
                nextEl = it.next();
                if(!nextEl.isInteger()){
                stk.push(nextEl.getList().iterator());
                } else{
                return true;
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