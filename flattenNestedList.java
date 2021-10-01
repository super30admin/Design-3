// Time Complexity : O(1)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


public class flattenNestedList {
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
    List<Integer> result = null;
    int curr;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        result = new ArrayList<>();
        for(NestedInteger val: nestedList) {
            aux(val);
        }
    }

    @Override
    public Integer next() {
        return result.get(curr++);
    }

    @Override
    public boolean hasNext() {
        if(curr < result.size())
            return true;
        return false;
    }
    
    private void aux(NestedInteger val) {
        if(val.isInteger())
            result.add(val.getInteger());
        else {
            for(NestedInteger singleVal : val.getList()) {
                aux(singleVal);
            }
                
        }
    }
    
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
