// https://leetcode.com/problems/flatten-nested-list-iterator/
// Time Complexity : Amortized O(1) for hasNext() and O(1) for next()
// Space Complexity : O(d) where d is the maximum depth of nested lists (stack size).
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

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
class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        this.st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return this.nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!this.st.isEmpty()){
            if(!this.st.peek().hasNext()){
                this.st.pop();
                this.nextEl = null;
            } else {
                this.nextEl = this.st.peek().next();
                if(this.nextEl.isInteger()){
                    return true;
                } else{
                    this.st.push(this.nextEl.getList().iterator());
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